package com.coolteam.coolky.searchpage

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.coolteam.coolky.FragmentTools
import com.coolteam.coolky.R
import com.coolteam.coolky.recipesearchresultspage.RecipeSearchResultsFragment
import com.coolteam.coolky.searchingredientspage.SearchIngredientsFragment
import com.google.android.material.chip.Chip
import kotlinx.android.synthetic.main.fragment_recipes_search.*

/**
 * A simple [Fragment] subclass.
 *
 * Lets the user choose ingredients, cuisines, types of dishes and cooking time.
 */
public class RecipesSearchFragment : Fragment() {

    private var model: RecipeSearchViewModel?=null
    private lateinit var typesOfDishes: Array<String>
    private lateinit var cuisines: Array<String>

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
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_recipes_search, container, false)
    }

    public override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        FillViews()
    }

    public fun FillViews() {
        for (ingrediemt in model!!.chosenIngredients) {

        }

        val types = ArrayList<String>()

        for (type in model!!.chosenTypesOfDishes) {
            types.add(type)
        }

        addTags(types, 1)

        val cuisines = ArrayList<String>()

        for (cuisine in model!!.chosenCuisines) {
            cuisines.add(cuisine)
        }

        addTags(cuisines, 2)

        if (model!!.chosenTime != 0 && model!!.chosenTime != Int.MAX_VALUE) {
            cookingTimeMinutes.text.append(model!!.chosenTime.toString())
        }
    }

    public override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        chooseTypeOfDish.setOnClickListener(this::chooseTypeOfDishClickHandler)
        chooseCuisine.setOnClickListener(this::chooseCuisineClickHandler)
        recipesSearchButton.setOnClickListener(this::searchClickHandler)
        chooseIngredient.setOnClickListener(this::chooseIngredientClickHandler)
    }

    private fun addTags(chosen : ArrayList<String>, choice : Int) {

        if (chosen.size == 0) {
            return
        }

        val layoutInflater = LayoutInflater.from(context)

        if (choice == 1) {
            typesOfDishes = updateAfterAdding(typesOfDishes, chosen)

            for (type in chosen) {
                val tag = layoutInflater.inflate(R.layout.tag_item, null, false)

                (tag as Chip).text = type

                tag.setOnCloseIconClickListener {
                    tagGroupTypesOfDishes.removeView(tag)
                    model!!.Remove(type, choice)
                    typesOfDishes = updateAfterRemoving(typesOfDishes, type)
                }

                tagGroupTypesOfDishes.addView(tag)
            }
        }
        else if (choice == 2) {
            cuisines = updateAfterAdding(cuisines, chosen)

            for (cuisine in chosen) {
                val tag = layoutInflater.inflate(R.layout.tag_item, null, false)

                (tag as Chip).text = cuisine

                tag.setOnCloseIconClickListener {
                    tagGroupCuisines.removeView(tag)
                    model!!.Remove(cuisine, choice)
                    cuisines = updateAfterRemoving(cuisines, cuisine)
                }

                tagGroupCuisines.addView(tag)
            }
        }
    }

    private fun updateAfterAdding(base: Array<String>, toRemove : ArrayList<String>): Array<String> {

        val indexes = ArrayList<Int>()

        for (e in toRemove) {
            indexes.add(base.indexOf(e))
        }

        val tmp = Array(base.size - indexes.size){""}

        var j = 0

        for (i in 0..base.size - 1) {
            if (indexes.contains(i)) {
                continue
            }

            tmp[j] = base[i]
            ++j
        }

        tmp.sort()
        return tmp
    }

    private fun updateAfterRemoving(base: Array<String>, tagText : String): Array<String> {
        val tmp = Array(base.size + 1){""}

        for (i in 0..tmp.size - 2) {
            tmp[i] = base[i]
        }

        tmp[tmp.size - 1] = tagText

        tmp.sort()

        return tmp
    }

    /**
     * Handles "type of dish click" event.
     */
    private fun chooseTypeOfDishClickHandler(chooseTypeOfDish: View) {
        if (chooseTypeOfDish is Button) {
            val builder = AlertDialog.Builder(this.context)
            val typesOfDishesCopy = typesOfDishes.copyOf()
            val tmpChosenTypes = ArrayList<String>()

            builder.setTitle(R.string.chooseTypeOfDishText)
                .setMultiChoiceItems(
                    typesOfDishesCopy, null,
                    DialogInterface.OnMultiChoiceClickListener { dialog, which, isChecked ->
                        if (isChecked) {
                            val text = typesOfDishesCopy[which]

                            if (!model!!.chosenTypesOfDishes.contains(text))
                            {
                                tmpChosenTypes.add(text)
                                model!!.Add(text, 1)
                            }

                        } else if (model!!.chosenTypesOfDishes.contains(typesOfDishesCopy[which])) {

                            val text = typesOfDishesCopy[which]

                            model!!.Remove(text, 1)
                            tmpChosenTypes.remove(text)
                        }
                    })
                .setPositiveButton(
                    R.string.ok,
                    DialogInterface.OnClickListener { dialog, id ->

                        addTags(tmpChosenTypes, 1)
                    })
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
            val builder = AlertDialog.Builder(this.context)
            val cuisinesCopy = cuisines.copyOf()
            val tmpChosenCuisines = ArrayList<String>()

            builder.setTitle(R.string.chooseCuisineText)
                .setMultiChoiceItems(
                    cuisinesCopy, null,
                    DialogInterface.OnMultiChoiceClickListener { dialog, which, isChecked ->
                        if (isChecked) {
                            val text = cuisinesCopy[which]

                            if (!model!!.chosenCuisines.contains(text))
                            {
                                tmpChosenCuisines.add(text)
                                model!!.Add(text, 2)
                            }
                        } else if (model!!.chosenCuisines.contains(cuisines[which])) {
                            val text = cuisinesCopy[which]

                            model!!.Remove(text, 2)
                            tmpChosenCuisines.remove(text)
                        }
                    })
                .setPositiveButton(
                    R.string.ok,
                    DialogInterface.OnClickListener { dialog, id ->
                        addTags(tmpChosenCuisines, 2)
                    })
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

            model!!.chosenIngredients = ingredients
            model!!.chosenTime = time

            val searchResultsFragment = RecipeSearchResultsFragment()

            FragmentTools.changeFragment(searchResultsFragment, activity!!.supportFragmentManager)
        }
    }
}
