package com.coolteam.coolky.searchingredientspage

import com.coolteam.coolky.R
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.coolteam.coolky.database.DBProvider
import com.coolteam.coolky.database.models.Ingredient
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

        showIngredients.adapter = ShowIngredientsAdapter(ingredients)
        showIngredients.layoutManager = LinearLayoutManager(context)


        searchIngredients.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (searchIngredients.text.isNotEmpty()) {
                    ingredients = DBProvider.getIngredients(searchIngredients.text.toString())

                    if (!ingredients.isNullOrEmpty()) {
                        nothingIsFound.visibility = View.GONE
                        showIngredients.adapter = ShowIngredientsAdapter(ingredients)
                    }
                    else if (ingredients != null && ingredients.isNullOrEmpty()) {
                        nothingIsFound.visibility = View.VISIBLE
                    }
                }
            }
        })
    }

    var ingredients: RealmResults<Ingredient>? = null
}
