package com.example.coolky

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.example.coolky.searchpage.RecipesSearchFragment
import kotlinx.android.synthetic.main.activity_main.*

public class MainActivity : AppCompatActivity() {

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigation.selectedItemId = R.id.recipesSearch

        recipesSearchFragment = RecipesSearchFragment()
        changeFragment(recipesSearchFragment, supportFragmentManager)

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
                changeFragment(recommendedFragment, supportFragmentManager)
            }

            R.id.myRecipes -> {
                myRecipesFragment = MyRecipesFragment()
                changeFragment(myRecipesFragment, supportFragmentManager)
            }

            R.id.recipesSearch -> {
                recipesSearchFragment =
                    RecipesSearchFragment()
                changeFragment(recipesSearchFragment, supportFragmentManager)
            }

            R.id.favorites -> {
                favoritesFragment = FavoritesFragment()
                changeFragment(favoritesFragment, supportFragmentManager)
            }

            R.id.settings -> {
                settingsFragment = SettingsFragment()
                changeFragment(settingsFragment, supportFragmentManager)
            }
        }
    }

    private lateinit var recommendedFragment: RecommendedFragment
    private lateinit var myRecipesFragment: MyRecipesFragment
    private lateinit var settingsFragment: SettingsFragment
    private lateinit var recipesSearchFragment: RecipesSearchFragment
    private lateinit var favoritesFragment: FavoritesFragment
}
