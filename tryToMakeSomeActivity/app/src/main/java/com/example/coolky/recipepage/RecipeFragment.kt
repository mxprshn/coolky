package com.example.coolky.recipepage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.coolky.R
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
        super.onViewCreated(view, savedInstanceState)
        ingredientsStepsViewPager.adapter = IngredientsStepsPagerAdapter(childFragmentManager)
        ingredientsStepsTabLayout.setupWithViewPager(ingredientsStepsViewPager)

        Picasso.get().isLoggingEnabled = true
        dishNameTextView.text = model.Name
        portionsAmountTextView.text = model.PortionAmount.toString()
        timeAmountTextView.text = model.CookTime.toString()
        Picasso.get().load(model.PictureUrl).into(dishImageView)
    }
}