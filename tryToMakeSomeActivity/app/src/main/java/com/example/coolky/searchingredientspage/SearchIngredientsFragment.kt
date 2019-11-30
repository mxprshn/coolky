package com.example.coolky.searchingredientspage

import android.content.Context
import android.net.Uri

import com.example.coolky.R
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isInvisible
import androidx.core.widget.addTextChangedListener
import com.example.coolky.database.DBProvider
import com.example.coolky.database.models.Ingredient
import com.example.coolky.recipepage.ShowIngredientsAdapter
import io.realm.OrderedRealmCollection
import io.realm.RealmResults
import kotlinx.android.synthetic.main.fragment_recipes_search.*
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
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    public override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        searchIngredients.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                ingredients = DBProvider.getIngredients(searchIngredients.text.toString())

                if (searchIngredients.text.isNotEmpty())
                {
                    ingredients = DBProvider.getIngredients(searchIngredients.text.toString())
                    if (!ingredients.isNullOrEmpty())
                    {
                        nothingIsFound.visibility = View.GONE
                        ingredientsRecycleView.adapter = ShowIngredientsAdapter(ingredients)
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
