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
import kotlin.math.log

class RecipeSearchResultsFragment : Fragment()
{
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?
    {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_recipe_search_results, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        val model = ViewModelProvider(activity!!)[RecipeSearchViewModel::class.java]

        model.ChosenTime.observe(viewLifecycleOwner, Observer {
                t -> Log.wtf("RES", t.toString())
        })

        model.ChosenCuisines.observe(viewLifecycleOwner, Observer {
            cuisines ->
            run { for (e in cuisines) Log.wtf("RES", e) }
        })

        searchResultsRecyclerView.layoutManager = LinearLayoutManager(this.context)
        searchResultsRecyclerView.adapter = SearchResultsListAdapter(DBProvider.getRecipes(arrayOf(), arrayOf(), arrayOf(), 800))
    }
}