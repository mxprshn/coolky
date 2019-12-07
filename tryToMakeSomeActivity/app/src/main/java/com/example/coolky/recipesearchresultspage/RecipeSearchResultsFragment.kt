package com.example.coolky.recipesearchresultspage

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coolky.FragmentTools
import com.example.coolky.OnItemClickListener
import com.example.coolky.R
import com.example.coolky.database.DBProvider
import com.example.coolky.database.models.Recipe
import com.example.coolky.recipepage.RecipeFragment
import com.example.coolky.searchpage.RecipeSearchViewModel
import io.realm.OrderedRealmCollection
import kotlinx.android.synthetic.main.fragment_recipe_ingredient_list.*
import kotlinx.android.synthetic.main.fragment_recipe_search_results.*
import kotlinx.android.synthetic.main.fragment_recipes_search.*
import java.util.*
import kotlin.math.log

class RecipeSearchResultsFragment : Fragment()
{
    private lateinit var chosenIngredients : Array<String>
    private lateinit var chosenTypes : Array<String>
    private lateinit var chosenCuisines : Array<String>
    private var chosenTime : Int = 0

    public inner class SearchResultClickListener : OnItemClickListener
    {
        override fun onItemClick(recipeId: String) {
            // здесь нужно вызвать метод, переключающий фрагменты
            FragmentTools.changeFragment(RecipeFragment.newInstance(recipeId), activity!!.supportFragmentManager)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?
    {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_recipe_search_results, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        searchResultsRecyclerView.layoutManager = LinearLayoutManager(this.context)

        val model = ViewModelProvider(activity!!)[RecipeSearchViewModel::class.java]

        chosenCuisines = Array(model.chosenCuisines.size) {i -> model.chosenCuisines[i].toLowerCase(Locale.getDefault())}

        chosenTypes = Array(model.chosenTypesOfDishes.size) {i -> model.chosenTypesOfDishes[i].toLowerCase(Locale.getDefault())}

        chosenTime = model.chosenTime

        searchResultsRecyclerView.adapter = SearchResultsListAdapter(DBProvider.getRecipes(arrayOf(), chosenTypes, chosenCuisines, chosenTime), SearchResultClickListener())
    }
}