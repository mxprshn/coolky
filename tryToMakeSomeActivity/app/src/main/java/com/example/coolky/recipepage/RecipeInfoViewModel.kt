package com.example.coolky.recipepage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.coolky.database.DBProvider
import com.example.coolky.database.models.Recipe
import com.example.coolky.database.models.RecipeIngredient
import io.realm.OrderedRealmCollection

class RecipeInfoViewModel(private val id: String) : ViewModel()  {
    private val recipe: Recipe? = DBProvider.findRecipeById(id)

    public var Name: String? = recipe!!.DishName
    public var CookTime: Int = recipe!!.CookTime
    public var Cuisine: String? = recipe!!.Cuisine
    public var PortionAmount: Int = recipe!!.PortionAmount
    public var PictureUrl: String? = recipe!!.PictureUrl
    public var WebSite: String? = recipe!!.WebSite
    public var Steps: List<String>? = recipe!!.Steps
    public var Ingredients: OrderedRealmCollection<RecipeIngredient>? = DBProvider.findRecipeIngredientsById(id)

    class Factory(private val id: String) : ViewModelProvider.NewInstanceFactory()
    {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T
        {
            // Добавить проверку
            return RecipeInfoViewModel(id) as T
        }
    }
}