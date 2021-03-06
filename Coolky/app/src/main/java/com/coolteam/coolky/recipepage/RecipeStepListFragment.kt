package com.coolteam.coolky.recipepage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.coolteam.coolky.R
import kotlinx.android.synthetic.main.fragment_recipe_ingredient_list.*

class RecipeStepListFragment : Fragment()
{
    private lateinit var model: RecipeInfoViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?
    {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recipe_step_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)
        model = ViewModelProvider(parentFragment!!)[RecipeInfoViewModel::class.java]
        ingredientListView.layoutManager = LinearLayoutManager(this.context)
        ingredientListView.adapter = RecipeStepListAdapter(model.steps)
    }
}