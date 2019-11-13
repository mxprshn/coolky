package com.example.coolky.recipepage

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class IngredientsStepsPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager)
{
    private val ingredientsFragment = RecipeIngredientListFragment()

    override fun getItem(position: Int): Fragment
    {
        return ingredientsFragment
    }

    override fun getCount() = 1

    override fun getPageTitle(position: Int): CharSequence?
    {
        return "Ингредиенты"
    }
}