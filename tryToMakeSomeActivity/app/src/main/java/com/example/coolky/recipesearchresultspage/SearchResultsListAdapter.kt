package com.example.coolky.recipesearchresultspage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.coolky.R

//class SearchResultsListAdapter(collection: OrderedRealmCollection<Recipe>?) : RealmRecyclerViewAdapter<Recipe, SearchResultsListAdapter.SearchResultViewHolder>
//    (collection, true)
class SearchResultsListAdapter : RecyclerView.Adapter<SearchResultsListAdapter.SearchResultViewHolder>()
{
    override fun getItemCount() = 20

    override fun onBindViewHolder(holder: SearchResultViewHolder, position: Int)
    {
        holder.dishNameText.text = "olololo"
        holder.ingredientsLeftAmountText.text = "OLOLOLOLO:)"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultViewHolder
    {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_recipe_search_result, parent, false)
        return SearchResultViewHolder(view)
    }

    class SearchResultViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val dishNameText: TextView = itemView.findViewById(R.id.dishNameTextView)
        val ingredientsLeftAmountText: TextView = itemView.findViewById(R.id.ingredientsLeftAmountTextView)
    }
}