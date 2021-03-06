package com.coolteam.coolky.searchpage

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.coolteam.coolky.FragmentTools
import com.coolteam.coolky.R
import com.coolteam.coolky.recipesearchresultspage.RecipeSearchResultsFragment
import com.coolteam.coolky.searchingredientspage.SearchIngredientsFragment
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import kotlinx.android.synthetic.main.fragment_recipes_search.*
import kotlinx.android.synthetic.main.fragment_recipes_search.view.*

/**
 * A simple [Fragment] subclass.
 *
 * Lets the user choose ingredients, cuisines, types of dishes and cooking time.
 */
public class RecipesSearchFragment : Fragment() {

    private var model: RecipeSearchViewModel?=null
    private lateinit var typesOfDishes: Array<String>
    private lateinit var cuisines: Array<String>
    private lateinit var chosenTypesOfDishes : ArrayList<String>
    private  lateinit var chosenCuisines : ArrayList<String>
    private var root: View? = null

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        typesOfDishes = resources.getStringArray(R.array.typesOfDishes)
        typesOfDishes.sort()

        cuisines = resources.getStringArray(R.array.cuisines)
        cuisines.sort()

        model = ViewModelProvider(activity!!)[RecipeSearchViewModel::class.java]
    }

    public override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //activity!!.supportFragmentManager.beginTransaction().detach(this).attach(this).commit()
        root = inflater.inflate(R.layout.fragment_recipes_search, container, false)
        return root
    }

    public override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        chooseTypeOfDish.setOnClickListener(this::chooseTypeOfDishClickHandler)
        chooseCuisine.setOnClickListener(this::chooseCuisineClickHandler)
        recipesSearchButton.setOnClickListener(this::searchClickHandler)
        chooseIngredient.setOnClickListener(this::chooseIngredientClickHandler)

        model!!.chosenTypesOfDishes.observe(activity!!, Observer {
            types ->
            run {
                updateTagGroup(root!!.tagGroupTypesOfDishes, types)
                typesOfDishes = updateChoices(resources.getStringArray(R.array.typesOfDishes), types)
            }
        })

        model!!.chosenCuisines.observe(activity!!, Observer {
            modelCuisines ->
            run {
                updateTagGroup(root!!.tagGroupCuisines, modelCuisines)
                cuisines = updateChoices(resources.getStringArray(R.array.cuisines), modelCuisines)
            }
        })
    }

    private fun updateChoices(base: Array<String>, chosen: ArrayList<String>?): Array<String> {
        val baseList = base.toCollection(ArrayList())

        baseList.removeAll(chosen!!)
        baseList.sort()

        return Array(baseList.size) { i -> baseList[i]}
    }

    private fun updateTagGroup(tagGroup: ChipGroup, list : ArrayList<String>) {
        val texts = ArrayList<String>()

        val toAdd = ArrayList<String>()

        for (child in tagGroup.children) {
            texts.add((child as Chip).text.toString())
        }

        for (text in list) {
            if (!texts.contains(text)) {
                toAdd.add(text)
            }
        }

        if (toAdd.isEmpty()) {
            return
        }

        addTags(tagGroup, toAdd)
    }

    private fun addTags(tagGroup: ChipGroup, list : ArrayList<String>) {
        val layoutInflater = LayoutInflater.from(root!!.context)

        for (text in list) {
            val tag = layoutInflater.inflate(R.layout.tag_item, null, false)

            (tag as Chip).text = text

            tag.setOnCloseIconClickListener {
                tagGroup.removeView(tag)
                model!!.remove(text)
            }

            tagGroup.addView(tag)
        }
    }

    /**
     * Handles "type of dish click" event.
     */
    private fun chooseTypeOfDishClickHandler(chooseTypeOfDish: View) {
        if (chooseTypeOfDish is Button) {
            val builder = AlertDialog.Builder(root!!.context)
            val typesOfDishesCopy = typesOfDishes.copyOf()
            val tmpChosenTypes = ArrayList<String>()

            builder.setTitle(R.string.chooseTypeOfDishText)
                .setMultiChoiceItems(
                    typesOfDishesCopy, null,
                    DialogInterface.OnMultiChoiceClickListener { dialog, which, isChecked ->
                        if (isChecked) {
                            tmpChosenTypes.add(typesOfDishesCopy[which])

                        } else {
                            tmpChosenTypes.remove(typesOfDishesCopy[which])
                        }
                    })
                .setPositiveButton(
                    R.string.ok
                ) { dialog, id ->
                    model!!.addTypes(tmpChosenTypes)
                }
                .create()
                .show()
        }
    }

    private fun chooseIngredientClickHandler(chooseIngredient: View) {
        if (chooseIngredient is Button) {
            val searchIngredientsFragment = SearchIngredientsFragment()
            FragmentTools.changeFragment(searchIngredientsFragment, activity!!.supportFragmentManager)
        }
    }

    private fun chooseCuisineClickHandler(chooseTypeOfCuisine: View)  {
        if (chooseTypeOfCuisine is Button) {
            val builder = AlertDialog.Builder(root!!.context)
            val cuisinesCopy = cuisines.copyOf()
            val tmpChosenCuisines = ArrayList<String>()

            builder.setTitle(R.string.chooseCuisineText)
                .setMultiChoiceItems(
                    cuisinesCopy, null,
                    DialogInterface.OnMultiChoiceClickListener { dialog, which, isChecked ->
                        if (isChecked) {
                            tmpChosenCuisines.add(cuisinesCopy[which])
                        } else {
                            tmpChosenCuisines.remove(cuisinesCopy[which])
                        }
                    })
                .setPositiveButton(
                    R.string.ok
                ) { dialog, id ->
                    model!!.addCuisines(tmpChosenCuisines)
                }
                .create()
                .show()
        }
    }

    private fun searchClickHandler(search: View) {
        if (search is Button) {
            val ingredients = ArrayList<String>()

            val timeText = cookingTimeMinutes.text.toString()

            val time = if (TextUtils.isEmpty(timeText)) {
                Int.MAX_VALUE
            } else {
                timeText.toInt()
            }

            model!!.chosenIngredients.value = ingredients
            model!!.chosenTime.postValue(time)

            val searchResultsFragment = RecipeSearchResultsFragment()

            FragmentTools.changeFragment(searchResultsFragment, activity!!.supportFragmentManager)
        }
    }
}
