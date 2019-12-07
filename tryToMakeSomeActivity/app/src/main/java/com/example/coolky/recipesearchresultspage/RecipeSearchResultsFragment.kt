package com.example.coolky.recipesearchresultspage

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coolky.R
import com.example.coolky.database.DBProvider
import com.example.coolky.database.models.Recipe
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

        chosenCuisines = Array(model.ChosenCuisines.size) {i -> model.ChosenCuisines[i].toLowerCase(Locale.getDefault())}

        chosenTypes = Array(model.ChosenTypesOfDishes.size) {i -> model.ChosenTypesOfDishes[i].toLowerCase(Locale.getDefault())}

        chosenTime = model.ChosenTime

        searchResultsRecyclerView.adapter = SearchResultsListAdapter(DBProvider.getRecipes(arrayOf(), chosenTypes, chosenCuisines, chosenTime))
    }
}