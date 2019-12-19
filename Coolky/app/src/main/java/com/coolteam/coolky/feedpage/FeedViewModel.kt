package com.coolteam.coolky.feedpage

import androidx.lifecycle.ViewModel

class FeedViewModel : ViewModel() {
    public var request : String=""
    public lateinit var currentIndexesPermutation : ArrayList<Int>

    public fun initializeIndexes(recipesCount: Long) {
        val indexes = ArrayList<Int>()

        for (i in 0 until recipesCount) {
            indexes.add(i.toInt())
        }

        currentIndexesPermutation = indexes

        shuffleIndexes()
    }

    public fun shuffleIndexes() {
        currentIndexesPermutation.shuffle()
    }
}