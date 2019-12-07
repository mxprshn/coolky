package com.example.coolky.searchpage

import androidx.lifecycle.ViewModel

class RecipeSearchViewModel : ViewModel() {
    public var ChosenIngredients = ArrayList<String>()
    public var ChosenTypesOfDishes = ArrayList<String>()
    public var ChosenCuisines = ArrayList<String>()
    public var ChosenTime : Int = 0
}