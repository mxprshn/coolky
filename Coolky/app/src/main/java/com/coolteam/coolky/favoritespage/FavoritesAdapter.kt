package com.coolteam.coolky.recipesearchresultspage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.like.LikeButton

import androidx.recyclerview.widget.RecyclerView
import com.coolteam.coolky.OnViewHolderIngredientClickListener
import com.coolteam.coolky.R
import com.coolteam.coolky.database.DBProvider
import com.coolteam.coolky.database.models.Favourite
import com.coolteam.coolky.database.models.Recipe
import com.squareup.picasso.Picasso
import io.realm.OrderedRealmCollection
import io.realm.RealmRecyclerViewAdapter
import kotlin.text.StringBuilder

class FavoritesAdapter(collection: OrderedRealmCollection<Favourite>?, val clickListener: OnViewHolderIngredientClickListener) : RealmRecyclerViewAdapter<Favourite, FavoritesAdapter.FavoriteViewHolder>
    (collection, true)
{
    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int)
    {
        val recipe = getItem(position)
        holder.bind(recipe!!, clickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder
    {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_recipe_search_result, parent, false)
        return FavoriteViewHolder(view)
    }

    class FavoriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        public fun bind(favorite: Favourite, clickListener: OnViewHolderIngredientClickListener) {
            val recipe = DBProvider.findRecipeById(favorite.recipeId!!)
            itemView.setOnClickListener { clickListener.onItemClick(recipe!!.id!!) }
            // Вопрос с переиспользованием вьюшек нужно решить снова
            // Если уже в избранном, то тогда нужно звездочку сделать желтенькой
            starButton.setOnClickListener { clickListener.onStarButtonClickListener(recipe!!.id!!, starButton) }

            dishNameTextView.text = recipe!!.dishName
            val stringBuilder = StringBuilder()
            stringBuilder.append("Нужно ингредиентов: ")
            val neededIngredients = DBProvider.findRecipeIngredientsById(recipe.id!!).count()
            stringBuilder.append(neededIngredients)

            ingredientsLeftAmountTextView.text = stringBuilder
            dishTypeTextView.text = recipe.type

            if (recipe.pictureUrl != "")
            {
                Picasso.get().load(recipe.pictureUrl).into(dishImageView)
            }
            else
            {
                dishImageView.setImageResource(R.drawable.not_found)
            }

            if (DBProvider.isFavourite(recipe.id!!))
            {
                starButton.isLiked = true
            }
            else
            {
                starButton.isLiked = false
            }
        }

        private val dishNameTextView: TextView = itemView.findViewById(R.id.dishNameTextView)
        private val dishTypeTextView: TextView = itemView.findViewById(R.id.typeTextView)
        private val ingredientsLeftAmountTextView: TextView = itemView.findViewById(R.id.ingredientsLeftAmountTextView)
        private val dishImageView: ImageView = itemView.findViewById(R.id.dishImageView)
        private val starButton: LikeButton = itemView.findViewById(R.id.starButton)
    }
}