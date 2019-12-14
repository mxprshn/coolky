package com.coolteam.coolky.searchingredientspage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckedTextView
import androidx.recyclerview.widget.RecyclerView
import com.coolteam.coolky.R
import com.coolteam.coolky.database.models.Ingredient
import io.realm.OrderedRealmCollection
import io.realm.RealmRecyclerViewAdapter

class ShowIngredientsAdapter(collection: OrderedRealmCollection<Ingredient>?, private val clickListener: OnIngredientClickListener) : RealmRecyclerViewAdapter<Ingredient, ShowIngredientsAdapter.IngredientViewHolder>
    (collection, true) {
    public override fun updateData(ingredients: OrderedRealmCollection<Ingredient>?) {
        super.updateData(ingredients)
    }

    override fun onBindViewHolder(holder: IngredientViewHolder, position: Int) {
        val ingredient = getItem(position)
        holder.ingredientCheckedTextView.text = ingredient!!.name.toString()
        holder.bind(ingredient, clickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_ingredient, parent, false)
        return IngredientViewHolder(view)
    }

    class IngredientViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ingredientCheckedTextView: CheckedTextView = itemView.findViewById(R.id.ingredientView)

        public fun bind(ingredient: Ingredient, clickListener: OnIngredientClickListener) {
            itemView.setOnClickListener { clickListener.onItemClick(ingredientCheckedTextView) }
        }
    }
}