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

class RecipeStepListAdapter(private val steps: List<String>?) : RecyclerView.Adapter<RecipeStepListAdapter.RecipeStepViewHolder>()
{
    override fun getItemCount() = steps!!.size

    override fun onBindViewHolder(holder: RecipeStepViewHolder, position: Int)
    {
        holder.stepText.text = steps!![position]
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeStepViewHolder
    {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_recipe_step, parent, false)
        return RecipeStepViewHolder(view)
    }

    class RecipeStepViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val stepText: TextView = itemView.findViewById(R.id.stepTextView)

    }
}