package com.coolteam.coolky

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.coolteam.coolky.feedpage.FeedFragment
import com.coolteam.coolky.searchpage.RecipesSearchFragment

class MainActivityViewModel : ViewModel() {
    public var isDarkThemeJustToggled : Boolean=false
    public var currentFeedFragment : Fragment=FeedFragment()
    public var currentSearchFragment : Fragment=RecipesSearchFragment()
}