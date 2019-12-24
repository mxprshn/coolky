package com.coolteam.coolky.recipepage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.coolteam.coolky.database.DBProvider
import com.coolteam.coolky.database.models.Recipe
import com.coolteam.coolky.database.models.RecipeIngredient
import io.realm.OrderedRealmCollection

class RecipeInfoViewModel(private val id: String) : ViewModel()  {
    private val recipe: Recipe? = DBProvider.findRecipeById(id)

    public var idRecipe: String = id
    public var isFavorite: Boolean = DBProvider.isFavourite(id)
    public var name: String? = recipe!!.dishName
    public var cookTime: Int = recipe!!.cookTime
    public var cuisine: String? = recipe!!.cuisine
    public var portionAmount: Int = recipe!!.portionAmount
    public var pictureUrl: String? = recipe!!.pictureUrl
    public var webSite: String? = recipe!!.webSite
    public var steps: List<String>? = recipe!!.steps
    public var ingredients: OrderedRealmCollection<RecipeIngredient>? = DBProvider.findRecipeIngredientsById(id)

    class Factory(private val id: String) : ViewModelProvider.NewInstanceFactory()
    {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T
        {
            // Добавить проверку
            return RecipeInfoViewModel(id) as T
        }
    }
}