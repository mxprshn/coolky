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
import kotlinx.android.synthetic.main.fragment_recipes_search.*

/**
 * A simple [Fragment] subclass.
 *
 * Lets the user choose ingredients, cuisines, types of dishes and cooking time.
 */
public class RecipesSearchFragment : Fragment() {

    private lateinit var model: RecipeSearchViewModel

    public override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        model = ViewModelProvider(this)[RecipeSearchViewModel::class.java]
        return inflater.inflate(R.layout.fragment_recipes_search, container, false)
    }

    public override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        chooseTypeOfDish.setOnClickListener(this::chooseTypeOfDishClickHandler)
        chooseCuisine.setOnClickListener(this::chooseCuisineClickHandler)
    }

    /**
     * Handles "type of dish click" event.
     */
    private fun chooseTypeOfDishClickHandler(chooseTypeOfDish: View) {
       if (chooseTypeOfDish is Button) {
            val typesOfDishes = resources.getStringArray(R.array.typesOfDishes)
            val builder = AlertDialog.Builder(this.context)

            builder.setTitle(R.string.chooseTypeOfDishText)
                .setMultiChoiceItems(
                    R.array.typesOfDishes, null,
                    DialogInterface.OnMultiChoiceClickListener { dialog, which, isChecked ->
                        if (isChecked) {
                            selectedTypesOfDishes.add(typesOfDishes[which])

                            val layoutInflater = LayoutInflater.from(context)

                            for (i in 1..100)
                            {
                                val tag = layoutInflater.inflate(R.layout.tag_item, null, false)
                                tagGroupTypesOfDishes.addView(tag)
                            }


                        } else if (selectedTypesOfDishes.contains(typesOfDishes[which])) {
                            selectedTypesOfDishes.remove(typesOfDishes[which])
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
    public val selectedTypesOfDishes: MutableList<String> = mutableListOf()
    // Where we track the selected cuisines
    public val selectedCuisines: MutableList<String> = mutableListOf()
}
