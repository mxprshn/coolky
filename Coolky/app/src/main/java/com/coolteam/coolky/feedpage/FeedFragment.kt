package com.coolteam.coolky.feedpage

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.coolteam.coolky.FragmentTools
import com.coolteam.coolky.MainActivityViewModel
import com.coolteam.coolky.OnItemClickListener
import com.coolteam.coolky.R
import com.coolteam.coolky.database.DBProvider
import com.coolteam.coolky.database.models.Recipe
import com.coolteam.coolky.recipepage.RecipeFragment
import io.realm.RealmResults
import kotlinx.android.synthetic.main.fragment_feed.*

class FeedFragment : Fragment() {
    private var model : FeedViewModel?=null
    private var mainActivityModel : MainActivityViewModel?=null

    inner class FeedItemClickListener : OnItemClickListener
    {
        override fun onItemClick(recipeId: String) {
            mainActivityModel!!.currentFeedFragment = RecipeFragment.newInstance(recipeId)
            FragmentTools.changeFragment(RecipeFragment.newInstance(recipeId), activity!!.supportFragmentManager)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model = ViewModelProvider(activity!!)[FeedViewModel::class.java]
        mainActivityModel = ViewModelProvider(activity!!)[MainActivityViewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?
    {
        super.onCreateView(inflater, container, savedInstanceState)

        return inflater.inflate(R.layout.fragment_feed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recipeSearchField.editText!!.text.append(model!!.request)

        recipeSearchField.editText!!.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                model!!.request = s.toString()

                if (model!!.request == "") {
                    (feedRecyclerView.adapter!! as FeedAdapter).setDefaultData()
                } else {
                    (feedRecyclerView.adapter!! as FeedAdapter).updateData(DBProvider.findRecipesByName(s.toString()))
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        swipeRefreshLayout.setOnRefreshListener {
            (feedRecyclerView.adapter!! as FeedAdapter).shuffleData()
            swipeRefreshLayout.isRefreshing = false
        }

        val data = DBProvider.findRecipesByName(model!!.request)

        feedRecyclerView.adapter = FeedAdapter(data, FeedItemClickListener(), model!!.currentIndexesPermutation)
    }
}