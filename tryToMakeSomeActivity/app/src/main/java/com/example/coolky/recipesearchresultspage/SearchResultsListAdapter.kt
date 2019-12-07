package com.example.coolky.recipesearchresultspage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.coolky.OnItemClickListener
import com.example.coolky.R
import com.example.coolky.database.DBProvider
import com.example.coolky.database.models.Recipe
import com.squareup.picasso.Picasso
import io.realm.OrderedRealmCollection
import io.realm.RealmRecyclerViewAdapter

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
            ingredientsLeftAmountTextView.text = DBProvider.findRecipeIngredientsById(recipe.id!!).count().toString()
            dishTypeTextView.text = recipe.type
            Picasso.get().load(recipe.pictureUrl).into(dishImageView)
        }

        private val dishNameTextView: TextView = itemView.findViewById(R.id.dishNameTextView)
        private val dishTypeTextView: TextView = itemView.findViewById(R.id.typeTextView)
        private val ingredientsLeftAmountTextView: TextView = itemView.findViewById(R.id.ingredientsLeftAmountTextView)
        private val dishImageView: ImageView = itemView.findViewById(R.id.dishImageView)
    }
}