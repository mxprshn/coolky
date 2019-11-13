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
import io.realm.RealmResults
import kotlinx.android.synthetic.main.view_holder_recipe_ingredient.view.*

//class RecipeIngredientListAdapter(collection: OrderedRealmCollection<RecipeIngredient>) : RealmRecyclerViewAdapter<RecipeIngredient, RecipeIngredientListAdapter.RecipeIngredientViewHolder>
//        (collection, true)
class RecipeIngredientListAdapter : RecyclerView.Adapter<RecipeIngredientListAdapter.RecipeIngredientViewHolder>()
{
    override fun getItemCount() = 15

    override fun onBindViewHolder(holder: RecipeIngredientViewHolder, position: Int)
    {
        holder.ingredientName.text = "Салями ${position.toString()}"
        holder.ingredientName.text = "${position.toString()}00 гр"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeIngredientViewHolder
    {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_recipe_ingredient_list, parent, false)
        return RecipeIngredientViewHolder(view)
    }

    class RecipeIngredientViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val ingredientName: TextView = itemView.findViewById(R.id.ingredientNameTextView)
        val ingredientAmount: TextView = itemView.findViewById(R.id.ingredientAmountTextView)

    }
}