package com.example.coolky

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.coolky.recipepage.RecipeFragment
import com.example.coolky.recipesearchresultspage.RecipeSearchResultsFragment
import com.example.coolky.searchpage.RecipesSearchFragment
import kotlinx.android.synthetic.main.activity_main.*

public class MainActivity : AppCompatActivity() {

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigation.selectedItemId = R.id.recipesSearch
        recipesSearchFragment = RecipesSearchFragment()
        FragmentTools.changeFragment(recipesSearchFragment, supportFragmentManager)
        
        bottomNavigation.setOnNavigationItemSelectedListener { item ->
            bottomNavigationOnItemSelectedHandler(item)
            true
        }
    }

    // Эта аннотация - костыль, который исправляет библиотечную багу
    // (menuitem.getitemid can only be called from within the same library group), описанную в ссылке:
    // https://stackoverflow.com/questions/41150995/appcompatactivity-oncreate-can-only-be-called-from-within-the-same-library-group
    @SuppressLint("RestrictedApi")
    private fun bottomNavigationOnItemSelectedHandler(item: MenuItem) {
        when(item.itemId) {
            R.id.recommended -> {
                recommendedFragment = RecommendedFragment()
                FragmentTools.changeFragment(recommendedFragment, supportFragmentManager)
            }

            R.id.myRecipes -> {
                myRecipesFragment = MyRecipesFragment()
                FragmentTools.changeFragment(myRecipesFragment, supportFragmentManager)
            }

            R.id.recipesSearch -> {
                recipesSearchFragment =
                    RecipesSearchFragment()
                FragmentTools.changeFragment(recipesSearchFragment, supportFragmentManager)
            }

            R.id.favorites -> {
                favoritesFragment = FavoritesFragment()
                FragmentTools.changeFragment(favoritesFragment, supportFragmentManager)
            }

            R.id.settings -> {
                settingsFragment = SettingsFragment()
                FragmentTools.changeFragment(settingsFragment, supportFragmentManager)
            }
        }
    }

    private lateinit var recommendedFragment: RecommendedFragment
    private lateinit var myRecipesFragment: MyRecipesFragment
    private lateinit var settingsFragment: SettingsFragment
    private lateinit var recipesSearchFragment: RecipesSearchFragment
    private lateinit var favoritesFragment: FavoritesFragment

    private lateinit var recipeFragment: RecipeFragment
}
