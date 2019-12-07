package com.example.coolky.searchingredientspage

import android.app.Activity
import android.content.Context
import android.inputmethodservice.Keyboard
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
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

        showIngredients.adapter = ShowIngredientsAdapter(ingredients)
        showIngredients.layoutManager = LinearLayoutManager(context)

        showIngredients.addOnItemTouchListener(RecyclerItemClickListener(context, showIngredients,
            object : RecyclerItemClickListener.OnItemClickListener {
                override fun onItemClick(view: View?, position: Int) {
                    Log.wtf("wtf", "okay")
                    searchIngredients.hideKeyboard()
                    /*if (true) {
                        Toast.makeText(context, "${view.text.toString()}", Toast.LENGTH_LONG)
                    }*/
                }

                override fun onLongItemClick(view: View?, position: Int) {}
            }))

        searchIngredients.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                searchHintsHandler()
            }
        })
    }

    fun Context.hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    // Хорошо ли просто делать ресайклер вью невидимым?
    private fun searchHintsHandler() {
        if (searchIngredients.text.isNotEmpty()) {
            ingredients = DBProvider.getIngredients(searchIngredients.text.toString())

            if (!ingredients.isNullOrEmpty()) {
                nothingIsFound.visibility = View.GONE
                showIngredients.adapter = ShowIngredientsAdapter(ingredients)
                showIngredients.visibility = View.VISIBLE
            }
            else if (ingredients != null && ingredients.isNullOrEmpty()) {
                showIngredients.visibility = View.GONE
                nothingIsFound.visibility = View.VISIBLE
            }
        }
        else
        {
            nothingIsFound.visibility = View.GONE
            showIngredients.visibility = View.GONE
        }
    }

    fun EditText.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

    private var ingredients: RealmResults<Ingredient>? = null
}