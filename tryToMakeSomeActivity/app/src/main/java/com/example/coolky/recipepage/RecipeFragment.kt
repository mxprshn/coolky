package com.example.coolky.recipepage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import com.example.coolky.R
import kotlinx.android.synthetic.main.fragment_recipe.*

class RecipeFragment : Fragment()
{
    private lateinit var ingredientsStepsPagerAdapter: IngredientsStepsPagerAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View?
    {

        ingredientsStepsPagerAdapter = IngredientsStepsPagerAdapter(childFragmentManager)
        ingredientsStepsViewPager.adapter = ingredientsStepsPagerAdapter
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recipe, container, false)
    }
}