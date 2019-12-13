package com.coolteam.coolky

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.coolteam.coolky.feedpage.FeedFragment
import com.coolteam.coolky.searchpage.RecipesSearchFragment
import com.coolteam.coolky.settingspage.SettingsFragment
import kotlinx.android.synthetic.main.activity_main.*

public class MainActivity : AppCompatActivity() {

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigation.selectedItemId = R.id.recipesSearch

        val index = supportFragmentManager.backStackEntryCount - 1

        if (index < 0) {
            recipesSearchFragment = RecipesSearchFragment()
            FragmentTools.changeFragment(recipesSearchFragment, supportFragmentManager)
        } else {
            settingsFragment = SettingsFragment()
            FragmentTools.changeFragment(settingsFragment, supportFragmentManager)
        }

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
            R.id.feed -> {
                feedFragment = FeedFragment()
                FragmentTools.changeFragment(feedFragment, supportFragmentManager)
                //recommendedFragment = RecommendedFragment()
                //FragmentTools.changeFragment(recommendedFragment, supportFragmentManager)
            }

            R.id.myRecipes -> {
                myRecipesFragment = MyRecipesFragment()
                FragmentTools.changeFragment(myRecipesFragment, supportFragmentManager)
            }

            R.id.recipesSearch -> {
                recipesSearchFragment = RecipesSearchFragment()
                FragmentTools.changeFragment(recipesSearchFragment, supportFragmentManager)
            }

            R.id.favorites -> {
                favoritesFragment = FavoritesFragment()
                FragmentTools.changeFragment(favoritesFragment, supportFragmentManager)
            }

            R.id.settings -> {
                settingsFragment =
                    SettingsFragment()
                FragmentTools.changeFragment(settingsFragment, supportFragmentManager)
            }
        }
    }

    private lateinit var feedFragment: FeedFragment
    private lateinit var myRecipesFragment: MyRecipesFragment
    private lateinit var settingsFragment: SettingsFragment
    private lateinit var recipesSearchFragment: RecipesSearchFragment
    private lateinit var favoritesFragment: FavoritesFragment
}
