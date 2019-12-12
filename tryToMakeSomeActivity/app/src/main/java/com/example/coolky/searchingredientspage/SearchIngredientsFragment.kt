package com.example.coolky.searchingredientspage

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.coolky.R
import com.example.coolky.database.DBProvider
import com.example.coolky.database.models.Ingredient
import io.realm.RealmResults
import kotlinx.android.synthetic.main.fragment_search_ingredients.*


/**
 * A simple [Fragment] subclass.
 */
class SearchIngredientsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_ingredients, container, false)
    }

    public override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        showIngredients.adapter = ShowIngredientsAdapter(ingredientsToShow)
        showIngredients.layoutManager = LinearLayoutManager(context)

        showIngredients.addOnItemTouchListener(
            RecyclerItemClickListener(context, showIngredients,
                object : RecyclerItemClickListener.OnItemClickListener {
                    override fun onItemClick(view: View?, position: Int) {
                        searchIngredients.hideKeyboard()
                        // val recyclerViewElementColor = showIngredients[position].background as ColorDrawable
                        //val recyclerViewElementColor = showIngredients[position].background

                        //if (recyclerViewElementColor== null) {
                        //    Log.i("omg", "color is null")
                        //}

                        // FIX IT!!!
                       // if (Color.parseColor(recyclerViewElementColor.toString()) == Color.parseColor("#ADD8E6")) {
                       //     showIngredients[position].setBackgroundColor(Color.parseColor("#FFFFFF"))
                       // }
                       // else {
                       //     showIngredients[position].setBackgroundColor(Color.parseColor("#ADD8E6"))
                       // }
                    }

                    override fun onLongItemClick(view: View?, position: Int) {}
                })
        )



        searchIngredients.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                searchHintsHandler()
            }
        })
    }

    private fun recyclerViewOnTouchHandler(recyclerView: View) {
        if (recyclerView is RecyclerView) {
            searchIngredients.hideKeyboard()
        }
    }

    // Хорошо ли просто делать ресайклер вью невидимым?
    private fun searchHintsHandler() {
        if (searchIngredients.text.isNotEmpty()) {
            ingredientsToShow = DBProvider.getIngredients(searchIngredients.text.toString())

            if (!ingredientsToShow.isNullOrEmpty()) {
                nothingIsFound.visibility = View.GONE
                showIngredients.adapter = ShowIngredientsAdapter(ingredientsToShow)
                showIngredients.visibility = View.VISIBLE
            } else if (ingredientsToShow != null && ingredientsToShow.isNullOrEmpty()) {
                showIngredients.visibility = View.GONE
                nothingIsFound.visibility = View.VISIBLE
            }
        } else {
            nothingIsFound.visibility = View.GONE
            showIngredients.visibility = View.GONE
        }
    }

    private fun EditText.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

    private var ingredientsToShow: RealmResults<Ingredient>? = null
    private var ingredientsToSend: MutableList<Ingredient> = mutableListOf()
}