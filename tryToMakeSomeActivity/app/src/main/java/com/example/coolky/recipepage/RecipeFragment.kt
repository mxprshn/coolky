package com.example.coolky.recipepage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import com.example.coolky.R
import kotlinx.android.synthetic.main.fragment_recipe.*

class RecipeFragment : Fragment()
{
    private lateinit var ingredientsStepsPagerAdapter: IngredientsStepsPagerAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View?
    {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recipe, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)
        ingredientsStepsViewPager.adapter = IngredientsStepsPagerAdapter(childFragmentManager)
        ingredientsStepsTabLayout.setupWithViewPager(ingredientsStepsViewPager)
    }
}