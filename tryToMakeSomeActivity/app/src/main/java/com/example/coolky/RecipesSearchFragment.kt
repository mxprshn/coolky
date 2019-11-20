package com.example.coolky

import android.app.AlertDialog
import android.content.ClipData
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import kotlinx.android.synthetic.main.fragment_recipes_search.*
import kotlinx.android.synthetic.main.tag_item.*

/**
 * A simple [Fragment] subclass.
 */
public class RecipesSearchFragment : Fragment() {
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

    private fun chooseTypeOfDishClickHandler(chooseTypeOfDish: View) {
       if (chooseTypeOfDish is Button) {
            val typesOfDishes = resources.getStringArray(R.array.typesOfDishes)
            val builder = AlertDialog.Builder(this.context)

            builder.setTitle(R.string.chooseTypeOfDishText)
                .setMultiChoiceItems(R.array.typesOfDishes, null,
                    DialogInterface.OnMultiChoiceClickListener { dialog, which, isChecked ->
                        if (isChecked) {
                            selectedTypesOfDishes.add(typesOfDishes[which])

                            val tagItem = Chip(tagGroup.context)
                            // И ТУТ КАК БЫ ВОЗНИКАЕТ ИСКЛЮЧЕНИЕ Invocation target exception, в следующей строчке

                            if (tagItem == null)
                            {
                                Log.i("lala", "NPE")
                            }

                            tagItem.setText(typesOfDishes[which]) // = typesOfDishes[which] // падает тут :(
                            tagGroup.addView(tagItem)
                        } else if (selectedTypesOfDishes.contains(typesOfDishes[which])) {
                            selectedTypesOfDishes.remove(typesOfDishes[which])
                        }
                    })
                .setPositiveButton(R.string.ok,
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
                .setMultiChoiceItems(R.array.cuisines, null,
                    DialogInterface.OnMultiChoiceClickListener { dialog, which, isChecked ->
                        if (isChecked) {
                            selectedCuisines.add(cuisines[which])
                        } else if (selectedCuisines.contains(cuisines[which])) {
                            selectedCuisines.remove(cuisines[which])
                        }
                    })
                .setPositiveButton(R.string.ok,
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
