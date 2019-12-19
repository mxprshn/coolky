package com.coolteam.coolky.recipepage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.coolteam.coolky.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_recipe.*


class RecipeFragment : Fragment()
{
    private lateinit var model: RecipeInfoViewModel

    companion object {

        @JvmStatic
        fun newInstance(recipeId: String) : RecipeFragment {
            val newFragment = RecipeFragment()
            val args = Bundle()
            args.putString("recipeId", recipeId)
            newFragment.arguments = args
            return newFragment
        }
    }

    override fun getDefaultViewModelProviderFactory(): ViewModelProvider.Factory
    {
        return RecipeInfoViewModel.Factory(arguments!!.getString("recipeId")!!)
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        model = ViewModelProvider(this)[RecipeInfoViewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?): View?
    {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_recipe, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            roundedRectangleImageView.setImageResource(R.drawable.shadow_rounded_rectangle_night)
        } else {
            roundedRectangleImageView.setImageResource(R.drawable.shadow_rounded_rectangle)
        }

        super.onViewCreated(view, savedInstanceState)
        ingredientsStepsViewPager.adapter = IngredientsStepsPagerAdapter(childFragmentManager)
        ingredientsStepsTabLayout.setupWithViewPager(ingredientsStepsViewPager)

        Picasso.get().isLoggingEnabled = true
        dishNameTextView.text = model.name
        portionsAmountTextView.text = model.portionAmount.toString()
        timeAmountTextView.text = model.cookTime.toString()

        if (model.portionAmount < 10 || model.portionAmount > 20) {
            when (model.portionAmount % 10) {
                1 -> portionsTextView.text = "Порция"
                2, 3, 4 -> portionsTextView.text = "Порции"
            }
        }

        if (model.pictureUrl != "")
        {
            Picasso.get().load(model.pictureUrl).into(dishImageView)
        }
        else
        {
            dishImageView.setImageResource(R.drawable.not_found)
        }
    }
}