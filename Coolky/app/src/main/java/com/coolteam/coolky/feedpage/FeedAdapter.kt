package com.coolteam.coolky.feedpage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.coolteam.coolky.OnItemClickListener
import com.coolteam.coolky.R
import com.coolteam.coolky.database.DBProvider
import com.coolteam.coolky.database.models.Recipe
import com.squareup.picasso.Picasso
import io.realm.OrderedRealmCollection
import io.realm.RealmRecyclerViewAdapter
import kotlin.collections.ArrayList

class FeedAdapter(collection : OrderedRealmCollection<Recipe>, val clickListener: OnItemClickListener, initIndexes : ArrayList<Int>) : RealmRecyclerViewAdapter<Recipe, FeedAdapter.FeedItemViewHolder>
    (collection, true)
{
    private var defaultIndexes: ArrayList<Int> = ArrayList()
    private var indexes: ArrayList<Int>
    private val defaultData: OrderedRealmCollection<Recipe>?

    init {
        indexes = ArrayList()
        defaultIndexes.addAll(initIndexes)
        indexes.addAll(initIndexes)
        defaultData = DBProvider.findRecipesByName("")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_feed_recipe, parent, false)
        return FeedItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: FeedItemViewHolder, position: Int) {
        val recipe = getItem(indexes[position])
        holder.bind(recipe!!, clickListener)
    }

    public override fun updateData(data: OrderedRealmCollection<Recipe>?) {
        indexes.clear()
        for (i in 0 until data!!.size) {
            indexes.add(i)
        }

        super.updateData(data)
    }

    public fun setDefaultData() {
        indexes.clear()
        indexes.addAll(defaultIndexes)
        super.updateData(defaultData)
    }

    public fun shuffleData() {
        indexes.shuffle()
        super.updateData(data)
    }

    class FeedItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        public fun bind(recipe: Recipe, clickListener: OnItemClickListener) {
            itemView.setOnClickListener { clickListener.onItemClick(recipe.id!!)}
            dishNameTextView.text = recipe.dishName
            Picasso.get().load(recipe.pictureUrl).into(dishImageView)
        }

        private val dishNameTextView: TextView = itemView.findViewById(R.id.dishNameTextView)
        private val dishImageView: ImageView = itemView.findViewById(R.id.dishImageView)
    }
}