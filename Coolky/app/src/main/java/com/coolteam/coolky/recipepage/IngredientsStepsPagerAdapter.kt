package com.coolteam.coolky.recipepage

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class IngredientsStepsPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager)
{
    override fun getItem(position: Int): Fragment
    {
        return when (position)
        {
            0 -> RecipeIngredientListFragment()
            else -> RecipeStepListFragment()
        }
    }

    override fun getCount() = 2

    override fun getPageTitle(position: Int): CharSequence?
    {
        return when (position)
        {
            0 -> "Ингредиенты"
            else -> "Приготовление"
        }
    }
}