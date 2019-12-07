package com.example.coolky.recipepage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.coolky.R
import com.example.coolky.database.models.RecipeIngredient
import io.realm.OrderedRealmCollection
import io.realm.RealmRecyclerViewAdapter

class RecipeIngredientListAdapter(collection: OrderedRealmCollection<RecipeIngredient>?) : RealmRecyclerViewAdapter<RecipeIngredient, RecipeIngredientListAdapter.RecipeIngredientViewHolder>
        (collection, true)
{
    override fun onBindViewHolder(holder: RecipeIngredientViewHolder, position: Int)
    {
        val recipeIngredient = getItem(position)
        holder.ingredientName.text = recipeIngredient!!.ingredient!!.name
        holder.ingredientAmount.text = recipeIngredient.amount
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeIngredientViewHolder
    {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_recipe_ingredient, parent, false)
        return RecipeIngredientViewHolder(view)
    }

    class RecipeIngredientViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val ingredientName: TextView = itemView.findViewById(R.id.ingredientNameTextView)
        val ingredientAmount: TextView = itemView.findViewById(R.id.ingredientsLeftAmountTextView)

    }
}