package com.coolteam.coolky.recipesearchresultspage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.like.LikeButton

import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.coolteam.coolky.OnItemClickListener
import com.coolteam.coolky.R
import com.coolteam.coolky.database.DBProvider
import com.coolteam.coolky.database.models.Recipe
import com.coolteam.coolky.searchpage.RecipeSearchViewModel
import com.squareup.picasso.Picasso
import io.realm.OrderedRealmCollection
import io.realm.RealmRecyclerViewAdapter
import kotlinx.android.synthetic.main.view_holder_recipe_search_result.view.*
import kotlin.text.StringBuilder

class SearchResultsListAdapter(collection: OrderedRealmCollection<Recipe>?, val clickListener: OnViewHolderIngredientClickListener) : RealmRecyclerViewAdapter<Recipe, SearchResultsListAdapter.SearchResultViewHolder>
        (collection, true)
{
    override fun onBindViewHolder(holder: SearchResultViewHolder, position: Int)
    {
        val recipe = getItem(position)
        holder.bind(recipe!!, clickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultViewHolder
    {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_recipe_search_result, parent, false)
        return SearchResultViewHolder(view)
    }

    class SearchResultViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        public fun bind(recipe: Recipe, clickListener: OnViewHolderIngredientClickListener) {
            itemView.setOnClickListener { clickListener.onItemClick(recipe.id!!) }
            // Вопрос с переиспользованием вьюшек нужно решить снова
            // Если уже в избранном, то тогда нужно звездочку сделать желтенькой
            starButton.setOnClickListener { clickListener.onStarButtonClickListener(recipe.id!!, starButton) }

            dishNameTextView.text = recipe.dishName
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
        }

        private val dishNameTextView: TextView = itemView.findViewById(R.id.dishNameTextView)
        private val dishTypeTextView: TextView = itemView.findViewById(R.id.typeTextView)
        private val ingredientsLeftAmountTextView: TextView = itemView.findViewById(R.id.ingredientsLeftAmountTextView)
        private val dishImageView: ImageView = itemView.findViewById(R.id.dishImageView)
        private val starButton: LikeButton = itemView.findViewById(R.id.starButton)
    }
}