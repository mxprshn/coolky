package com.example.coolky.recipepage

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter

class IngredientsStepsPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager)
{
    override fun getItem(position: Int): Fragment
    {
        return RecipeIngredientListFragment()
    }

    override fun getCount() = 1

    override fun getPageTitle(position: Int): CharSequence?
    {
        return "Ингредиенты"
    }
}