package com.coolteam.coolky

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import com.coolteam.coolky.database.models.Recipe
import com.coolteam.coolky.feedpage.FeedFragment
import com.coolteam.coolky.searchpage.RecipesSearchFragment
import com.coolteam.coolky.settingspage.SettingsFragment
import kotlinx.android.synthetic.main.activity_main.*

public class MainActivity : AppCompatActivity() {

    var model : MainActivityViewModel?=null

    public override fun onCreate(savedInstanceState: Bundle?) {
        val mode = resources?.configuration?.uiMode?.and(Configuration.UI_MODE_NIGHT_MASK)

        if (mode == Configuration.UI_MODE_NIGHT_YES) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }

        model = ViewModelProvider(this)[MainActivityViewModel::class.java]
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigation.selectedItemId = R.id.recipesSearch

        if (model!!.isDarkThemeJustToggled) {
            settingsFragment = SettingsFragment()
            FragmentTools.changeFragment(settingsFragment, supportFragmentManager)
            model!!.isDarkThemeJustToggled = false
        } else {
            FragmentTools.changeFragment(model!!.currentSearchFragment, supportFragmentManager)
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
                if (item.isChecked) {
                    feedFragment = FeedFragment()
                    FragmentTools.changeFragment(feedFragment, supportFragmentManager)
                    model!!.currentFeedFragment = FeedFragment()
                } else {
                    FragmentTools.changeFragment(model!!.currentFeedFragment, supportFragmentManager)
                }
            }

            R.id.myRecipes -> {
                myRecipesFragment = MyRecipesFragment()
                FragmentTools.changeFragment(myRecipesFragment, supportFragmentManager)
            }

            R.id.recipesSearch -> {
                if (item.isChecked) {
                    recipesSearchFragment = RecipesSearchFragment()
                    FragmentTools.changeFragment(recipesSearchFragment, supportFragmentManager)
                    model!!.currentSearchFragment = RecipesSearchFragment()
                } else {
                    FragmentTools.changeFragment(model!!.currentSearchFragment, supportFragmentManager)
                }
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
