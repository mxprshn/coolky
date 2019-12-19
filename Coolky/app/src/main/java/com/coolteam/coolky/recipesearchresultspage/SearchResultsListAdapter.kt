package com.coolteam.coolky.recipesearchresultspage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
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
import kotlin.text.StringBuilder

class SearchResultsListAdapter(collection: OrderedRealmCollection<Recipe>?, val clickListener: OnItemClickListener) : RealmRecyclerViewAdapter<Recipe, SearchResultsListAdapter.SearchResultViewHolder>
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
        public fun bind(recipe: Recipe, clickListener: OnItemClickListener) {
            itemView.setOnClickListener { clickListener.onItemClick(recipe.id!!) }
            dishNameTextView.text = recipe.dishName
            val stringBuilder = StringBuilder()
            stringBuilder.append("Нужно ещё ингредиентов: ")
            val neededIngredients = DBProvider.findRecipeIngredientsById(recipe.id!!).count()
            stringBuilder.append(neededIngredients)
            ingredientsLeftAmountTextView.text = stringBuilder
            dishTypeTextView.text = recipe.type

            if (recipe.pictureUrl != "")
            {
                Picasso.get().load(recipe.pictureUrl).placeholder(R.drawable.not_found).into(dishImageView)
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
    }
}