package com.coolteam.coolky.recipesearchresultspage

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.coolteam.coolky.FragmentTools
import com.coolteam.coolky.MainActivityViewModel
import com.coolteam.coolky.OnItemClickListener
import com.coolteam.coolky.R
import com.coolteam.coolky.database.DBProvider
import com.coolteam.coolky.recipepage.RecipeFragment
import com.coolteam.coolky.searchpage.RecipeSearchViewModel
import kotlinx.android.synthetic.main.fragment_recipe_search_results.*
import java.util.*

class RecipeSearchResultsFragment : Fragment()
{
    private var model: RecipeSearchViewModel?=null
    private var mainActivityModel: MainActivityViewModel?=null
    private lateinit var chosenIngredients : Array<String>
    private lateinit var chosenTypes : Array<String>
    private lateinit var chosenCuisines : Array<String>
    private var chosenTime : Int = 0

    public inner class SearchResultClickListener : OnItemClickListener
    {
        override fun onItemClick(recipeId: String) {
            // здесь нужно вызвать метод, переключающий фрагменты
            mainActivityModel!!.currentSearchFragment = RecipeFragment.newInstance(recipeId)
            FragmentTools.changeFragment(RecipeFragment.newInstance(recipeId), activity!!.supportFragmentManager)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        model = ViewModelProvider(activity!!)[RecipeSearchViewModel::class.java]
        mainActivityModel = ViewModelProvider(activity!!)[MainActivityViewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?
    {
        super.onCreateView(inflater, container, savedInstanceState)

        return inflater.inflate(R.layout.fragment_recipe_search_results, container, false)
    }

    @SuppressLint("DefaultLocale")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        searchResultsRecyclerView.layoutManager = LinearLayoutManager(this.context)

        model!!.chosenTypesOfDishes.observe(activity!!, Observer {
            types ->
            run {
                chosenTypes = Array(types.size) {i -> types[i].toLowerCase()}
            }
        })

        model!!.chosenCuisines.observe(activity!!, Observer {
            cuisines ->
            run {
                chosenCuisines = Array(cuisines.size) { i -> cuisines[i].toLowerCase() }
            }
        })

        model!!.chosenTime.observe(activity!!, Observer {
                t -> chosenTime = t
        })

        searchResultsRecyclerView.adapter = SearchResultsListAdapter(DBProvider.getRecipes(arrayOf(), chosenTypes, chosenCuisines, chosenTime), SearchResultClickListener())
    }
}