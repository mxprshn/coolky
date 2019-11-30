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

        recipeSearchResultsFragment = RecipeSearchResultsFragment()
        changeFragment(recipeSearchResultsFragment)
        recipesSearchFragment = RecipesSearchFragment()
          //recipeFragment = RecipeFragment()
          //changeFragment(recipeFragment)

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
                changeFragment(recommendedFragment)
            }

            R.id.myRecipes -> {
                myRecipesFragment = MyRecipesFragment()
                changeFragment(myRecipesFragment)
            }

            R.id.recipesSearch -> {
                recipesSearchFragment =
                    RecipesSearchFragment()
                changeFragment(recipesSearchFragment)
            }

            R.id.favorites -> {
                favoritesFragment = FavoritesFragment()
                changeFragment(favoritesFragment)
            }

            R.id.settings -> {
                settingsFragment = SettingsFragment()
                changeFragment(settingsFragment)
            }
        }
    }

    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frameLayout , fragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()
    }

    private lateinit var recommendedFragment: RecommendedFragment
    private lateinit var myRecipesFragment: MyRecipesFragment
    private lateinit var settingsFragment: SettingsFragment
    private lateinit var recipeSearchResultsFragment: RecipeSearchResultsFragment
    private lateinit var recipesSearchFragment: RecipesSearchFragment
    private lateinit var favoritesFragment: FavoritesFragment
}
