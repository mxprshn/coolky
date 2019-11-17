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
class RecipeStepListAdapter : RecyclerView.Adapter<RecipeStepListAdapter.RecipeStepViewHolder>()
{
    override fun getItemCount() = 10

    override fun onBindViewHolder(holder: RecipeStepViewHolder, position: Int)
    {
        holder.stepText.text =  when
        {
            position % 2 == 0 -> "Сперва займитесь соусом. Традиционно для пиццы используют томатный соус," +
                    " приготовленный из свежих или из консервированных томатов, но так как сезон свежих томатов" +
                    " слишком короткий, а консервированные можно найти не везде, предлагаю приготовить простой, но" +
                    " вкусный вариант соуса для пиццы. Возьмите 1 ст. л. с горкой хорошей (качественной и вкусной!)" +
                    " томатной пасты, добавьте к ней 2-3 ст. л. воды (кипяченой), щепотку сахара, соли и немного" +
                    " сушенных трав."

            else -> "И перемешайте. По консистенции соус должен быть близок к кетчупу, поэтому если нужно," +
                    " то можете добавить немного больше или меньше воды, в зависимости от густоты пасты." +
                    " Вот и всё, соус готов."
        }
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