package com.example.coolky.recipesearchresultspage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.coolky.R
import com.example.coolky.database.DBProvider
import com.example.coolky.database.models.Recipe
import com.example.coolky.database.models.RecipeIngredient
import com.squareup.picasso.Picasso
import io.realm.OrderedRealmCollection
import io.realm.RealmRecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_recipe.*

class SearchResultsListAdapter(collection: OrderedRealmCollection<RecipeIngredient>?) : RealmRecyclerViewAdapter<RecipeIngredient, SearchResultsListAdapter.SearchResultViewHolder>
        (collection, true)
{
    override fun onBindViewHolder(holder: SearchResultViewHolder, position: Int)
    {
        val recipeIngredient = getItem(position)
        holder.dishNameTextView.text = recipeIngredient!!.Recipe!!.DishName
        holder.ingredientsLeftAmountTextView.text = DBProvider.findRecipeIngredientsById(recipeIngredient.Recipe!!.Id!!).count().toString()
        holder.dishTypeTextView.text = recipeIngredient.Recipe!!.Type
        Picasso.get().load(recipeIngredient.Recipe!!.PictureUrl).into(holder.dishImageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultViewHolder
    {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_recipe_search_result, parent, false)
        return SearchResultViewHolder(view)
    }

    class SearchResultViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val dishNameTextView: TextView = itemView.findViewById(R.id.dishNameTextView)
        val dishTypeTextView: TextView = itemView.findViewById(R.id.typeTextView)
        val ingredientsLeftAmountTextView: TextView = itemView.findViewById(R.id.ingredientsLeftAmountTextView)
        val dishImageView: ImageView = itemView.findViewById(R.id.dishImageView)
    }
}