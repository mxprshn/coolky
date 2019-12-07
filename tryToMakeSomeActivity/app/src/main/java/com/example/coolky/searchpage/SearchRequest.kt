package com.example.coolky.searchpage

import com.example.coolky.database.models.Ingredient

class SearchRequest {
    public lateinit var ChosenIngredients : Array<String>
    public lateinit var ChosenTypesOfDishes : Array<String>
    public lateinit var ChosenCuisines : Array<String>
    public var ChosenTime : Int = 0
}