package com.example.coolky

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import kotlinx.android.synthetic.main.fragment_recipes_search.*

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

        chooseTypeOfDish.setOnClickListener(this::typeOfDishClickHandler)
    }

    private fun typeOfDishClickHandler(typeOfDish: View) {
       if (typeOfDish is Button) {
            val builder = AlertDialog.Builder(this.context)
            builder.setTitle(R.string.chooseTypeOfDishText)
                .setItems(R.array.typesOfDishes, DialogInterface.OnClickListener { dialog, which ->
                    
                        // The 'which' argument contains the index position
                        // of the selected item
                    })
                .create()
                .show()
       }
    }

    private var typeOfDish: String? = null
}
