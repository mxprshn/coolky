package com.example.coolky.recipepage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.coolky.R
import com.example.coolky.database.DBProvider
import com.example.coolky.database.models.Ingredient
import com.example.coolky.database.models.RecipeIngredient
import io.realm.OrderedRealmCollection
import io.realm.RealmRecyclerViewAdapter
import io.realm.RealmResults
import kotlinx.android.synthetic.main.view_holder_recipe_ingredient.view.*

class ShowIngredientsAdapter(collection: OrderedRealmCollection<Ingredient>?) : RealmRecyclerViewAdapter<Ingredient, ShowIngredientsAdapter.IngredientViewHolder>
    (collection, true)
{
    override fun onBindViewHolder(holder: IngredientViewHolder, position: Int)
    {
        val ingredient = getItem(position)
        holder.ingredientName.text = ingredient.toString()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientViewHolder
    {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_ingredient, parent, false)
        return IngredientViewHolder(view)
    }

    class IngredientViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val ingredientName: TextView = itemView.findViewById(R.id.ingredientView)
    }
}