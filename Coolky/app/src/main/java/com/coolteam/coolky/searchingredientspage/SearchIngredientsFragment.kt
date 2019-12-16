package com.coolteam.coolky.searchingredientspage

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.CheckedTextView
import android.widget.EditText
import android.widget.LinearLayout
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.coolteam.coolky.FragmentTools
import com.coolteam.coolky.R
import com.coolteam.coolky.database.DBProvider
import com.coolteam.coolky.database.models.Ingredient
import com.coolteam.coolky.searchpage.RecipeSearchViewModel
import com.coolteam.coolky.searchpage.RecipesSearchFragment
import io.realm.RealmResults
import kotlinx.android.synthetic.main.fragment_search_ingredients.*

/**
 * A simple [Fragment] subclass.
 */
class SearchIngredientsFragment : Fragment() {

    public inner class IngredientClickListener : OnIngredientClickListener {
        override fun onItemClick(ingredient: CheckedTextView) {
            ingredientSearchField.editText!!.hideKeyboard()
            val textOfIngredient = ingredient.text.toString()

            if (ingredient.isChecked) {
                ingredient.isChecked = false
                ReuseCheckedTextViewModel.chosenIngredients.remove(textOfIngredient)
                ingredientsToSend.remove(textOfIngredient)
            }
            else {
                ingredient.isChecked = true
                ReuseCheckedTextViewModel.chosenIngredients.add(textOfIngredient)
                ingredientsToSend.add(ingredient.text.toString())
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        model = ViewModelProvider(activity!!)[RecipeSearchViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_ingredients, container, false)
    }

    public override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        ingredientsAreChosen.setOnClickListener(this::ingredientsAreChosenClickListener)
        showIngredients.adapter = ShowIngredientsAdapter(ingredientsToShow, IngredientClickListener())
        showIngredients.layoutManager = LinearLayoutManager(context)

        ingredientSearchField.editText!!.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                searchHintsHandler()
            }
        })
    }

    // Хорошо ли просто делать ресайклер вью невидимым?
    private fun searchHintsHandler() {
        if (ingredientSearchField.editText!!.text!!.isNotEmpty()) {
            ingredientsToShow = DBProvider.getIngredients(ingredientSearchField.editText!!.text.toString())

            if (!ingredientsToShow.isNullOrEmpty()) {
                nothingIsFound.visibility = View.GONE
                (showIngredients.adapter as ShowIngredientsAdapter).updateData(ingredientsToShow)
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

    private fun ingredientsAreChosenClickListener(ingredientsChosen: View) {
        if (ingredientsChosen is Button) {
            model!!.addIngredients(ingredientsToSend)
            val recipesSearchFragment = RecipesSearchFragment()
            FragmentTools.changeFragment(recipesSearchFragment, activity!!.supportFragmentManager)
        }
    }

    private var ingredientsToShow: RealmResults<Ingredient>? = null
    private var model: RecipeSearchViewModel?=null
    private var ingredientsToSend: ArrayList<String> = arrayListOf()
}