package com.example.coolky.searchpage

import androidx.lifecycle.ViewModel

class RecipeSearchViewModel : ViewModel() {
    public lateinit var ChosenIngredients : Array<String>
    public lateinit var ChosenTypesOfDishes : Array<String>
    public lateinit var ChosenCuisines : Array<String>
    public var Time : Int = 0
}