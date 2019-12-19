package com.coolteam.coolky

import com.like.LikeButton

interface OnViewHolderIngredientClickListener {
    public fun onItemClick(recipeId: String)
    public fun onStarButtonClickListener(recipeId: String, starButton: LikeButton)
}