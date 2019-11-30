package com.example.coolky.searchpage

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import com.example.coolky.R
import com.google.android.material.chip.Chip
import kotlinx.android.synthetic.main.fragment_recipes_search.*
import kotlinx.android.synthetic.main.fragment_settings.view.*

/**
 * A simple [Fragment] subclass.
 *
 * Lets the user choose ingredients, cuisines, types of dishes and cooking time.
 */
public class RecipesSearchFragment : Fragment() {

    private lateinit var model: RecipeSearchViewModel
    private lateinit var typesOfDishes: Array<String>
    private lateinit var allTypesOfDishes: Array<String>
    private var chosenTypes = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        typesOfDishes = resources.getStringArray(R.array.typesOfDishes)
        allTypesOfDishes = typesOfDishes.copyOf()

        model = ViewModelProvider(this)[RecipeSearchViewModel::class.java]
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
    }


    private fun UpdateAfterAdding(copy: Array<String>, tagText : String) {
        val index = typesOfDishes.indexOf(tagText)
        val tmp = Array(typesOfDishes.size - 1){""}

        for (i in 0..tmp.size) {
            if (i == index) {
                continue
            }

            if (i < index) {
                tmp[i] = typesOfDishes[i]
            }
            else {
                tmp[i - 1] = typesOfDishes[i]
            }
        }

        typesOfDishes = tmp
    }

    private fun UpdateAfterRemoving(copy: Array<String>, tagText : String) {
        chosenTypes.remove(tagText)

        val tmp = Array(typesOfDishes.size + 1){""}

        for (i in 0..tmp.size - 2) {
            tmp[i] = typesOfDishes[i]
        }

        tmp[tmp.size - 1] = tagText

        typesOfDishes = tmp
    }

    /**
     * Handles "type of dish click" event.
     */
    private fun chooseTypeOfDishClickHandler(chooseTypeOfDish: View) {
       if (chooseTypeOfDish is Button) {

            val builder = AlertDialog.Builder(this.context)
            var tags = ArrayList<View>()
            var typesOfDishesCopy = typesOfDishes.copyOf()


            builder.setTitle(R.string.chooseTypeOfDishText)
                .setMultiChoiceItems(
                    typesOfDishesCopy, null,
                    DialogInterface.OnMultiChoiceClickListener { dialog, which, isChecked ->
                        if (isChecked) {
                            val layoutInflater = LayoutInflater.from(context)

                            val tag = layoutInflater.inflate(R.layout.tag_item, null, false)
                            (tag as Chip).text = typesOfDishesCopy[which]

                            tag.setOnCloseIconClickListener {
                                tagGroupTypesOfDishes.removeView(tag)

                                val tagText = tag.text.toString()

                                UpdateAfterRemoving(typesOfDishesCopy, tagText)
                            }

                            if (!chosenTypes.contains(tag.text))
                            {
                                val tagText = tag.text.toString()
                                chosenTypes.add(tagText)
                                tags.add(tag)

                                UpdateAfterAdding(typesOfDishesCopy, tagText)
                            }

                        } else if (chosenTypes.contains(typesOfDishesCopy[which])) {
                            chosenTypes.remove(typesOfDishesCopy[which])
                            for (e in tags) {
                                if ((e as Chip).text == typesOfDishesCopy[which]) {
                                    tags.remove(e)
                                }
                            }
                        }
                    })
                .setPositiveButton(
                    R.string.ok,
                    DialogInterface.OnClickListener { dialog, id ->
                        for (e in tags) {
                            tagGroupTypesOfDishes.addView(e)
                        }
                    })
                .create()
                .show()
       }
    }

    private fun chooseCuisineClickHandler(chooseTypeOfCuisine: View)
    {
        if (chooseTypeOfCuisine is Button) {
            val cuisines = resources.getStringArray(R.array.cuisines)
            val builder = AlertDialog.Builder(this.context)

            builder.setTitle(R.string.chooseCuisineText)
                .setMultiChoiceItems(
                    R.array.cuisines, null,
                    DialogInterface.OnMultiChoiceClickListener { dialog, which, isChecked ->
                        if (isChecked) {
                            selectedCuisines.add(cuisines[which])
                        } else if (selectedCuisines.contains(cuisines[which])) {
                            selectedCuisines.remove(cuisines[which])
                        }
                    })
                .setPositiveButton(
                    R.string.ok,
                    DialogInterface.OnClickListener { dialog, id ->
                    })
                .create()
                .show()
        }
    }

    // Where we track the selected types of dishes
    //public val selectedTypesOfDishes: MutableList<String> = mutableListOf()
    // Where we track the selected cuisines
    public val selectedCuisines: MutableList<String> = mutableListOf()
}
