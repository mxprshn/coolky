package com.example.coolky.searchpage

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.coolky.FragmentTools
import com.example.coolky.R
import com.example.coolky.recipesearchresultspage.RecipeSearchResultsFragment
import com.example.coolky.searchingredientspage.SearchIngredientsFragment
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
    private var chosenTypes = ArrayList<String>()
    private lateinit var cuisines: Array<String>
    private var chosenCuisines = ArrayList<String>()

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

    public override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        chooseTypeOfDish.setOnClickListener(this::chooseTypeOfDishClickHandler)
        chooseCuisine.setOnClickListener(this::chooseCuisineClickHandler)
        recipesSearchButton.setOnClickListener(this::searchClickHandler)
        chooseIngredient.setOnClickListener(this::chooseIngredientClickHandler)
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

                            if (!chosenTypes.contains(text))
                            {
                                tmpChosenTypes.add(text)
                                chosenTypes.add(text)
                            }

                        } else if (chosenTypes.contains(typesOfDishesCopy[which])) {

                            val text = typesOfDishesCopy[which]

                            chosenTypes.remove(text)
                            tmpChosenTypes.remove(text)
                        }
                    })
                .setPositiveButton(
                    R.string.ok,
                    DialogInterface.OnClickListener { dialog, id ->

                        val layoutInflater = LayoutInflater.from(context)
                        typesOfDishes = updateAfterAdding(typesOfDishes, tmpChosenTypes)

                        for (type in tmpChosenTypes) {
                            val tag = layoutInflater.inflate(R.layout.tag_item, null, false)

                            (tag as Chip).text = type

                            tag.setOnCloseIconClickListener {
                                tagGroupTypesOfDishes.removeView(tag)
                                chosenTypes.remove(type)
                                typesOfDishes = updateAfterRemoving(typesOfDishes, type)
                            }

                            tagGroupTypesOfDishes.addView(tag)
                        }
                    })
                .create()
                .show()
        }
    }

    private fun chooseIngredientClickHandler(chooseIngredient: View) {
        if (chooseIngredient is Button) {
            var searchIngredientsFragment = SearchIngredientsFragment()
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

                            if (!chosenCuisines.contains(text))
                            {
                                tmpChosenCuisines.add(text)
                                chosenCuisines.add(text)
                            }
                        } else if (chosenCuisines.contains(cuisines[which])) {
                            val text = cuisinesCopy[which]

                            chosenCuisines.remove(text)
                            tmpChosenCuisines.remove(text)
                        }
                    })
                .setPositiveButton(
                    R.string.ok,
                    DialogInterface.OnClickListener { dialog, id ->
                        val layoutInflater = LayoutInflater.from(context)
                        cuisines = updateAfterAdding(cuisines, tmpChosenCuisines)

                        for (cuisine in tmpChosenCuisines) {
                            val tag = layoutInflater.inflate(R.layout.tag_item, null, false)

                            (tag as Chip).text = cuisine

                            tag.setOnCloseIconClickListener {
                                tagGroupCuisines.removeView(tag)
                                chosenCuisines.remove(cuisine)
                                cuisines = updateAfterRemoving(cuisines, cuisine)
                            }

                            tagGroupCuisines.addView(tag)
                        }
                    })
                .create()
                .show()
        }
    }

    private fun searchClickHandler(search: View) {
        if (search is Button) {
            val ingredients = ArrayList<String>()
            val typesOfDishes = chosenTypes
            val cuisines = chosenCuisines
            val time : Int

            val timeText = cookingTimeMinutes.text.toString()

            if (TextUtils.isEmpty(timeText)) {
                time = Int.MAX_VALUE
            }
            else {
                time = timeText.toInt()
            }

            model!!.ChosenIngredients = ingredients
            model!!.ChosenTypesOfDishes = typesOfDishes
            model!!.ChosenCuisines = cuisines
            model!!.ChosenTime = time

            val searchResultsFragment = RecipeSearchResultsFragment()

            FragmentTools.changeFragment(searchResultsFragment, activity!!.supportFragmentManager)
        }
    }
}
