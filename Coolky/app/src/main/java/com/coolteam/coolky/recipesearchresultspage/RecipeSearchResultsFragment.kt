package com.coolteam.coolky.recipesearchresultspage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.coolteam.coolky.FragmentTools
import com.coolteam.coolky.OnItemClickListener
import com.coolteam.coolky.R
import com.coolteam.coolky.database.DBProvider
import com.coolteam.coolky.recipepage.RecipeFragment
import com.coolteam.coolky.searchpage.RecipeSearchViewModel
import kotlinx.android.synthetic.main.fragment_recipe_search_results.*
import java.util.*

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