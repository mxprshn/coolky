package com.example.coolky.searchpage

import com.example.coolky.database.models.Ingredient

class SearchRequest {
    public lateinit var chosenIngredients : Array<String>
    public lateinit var chosenTypesOfDishes : Array<String>
    public lateinit var chosenCuisines : Array<String>
    public var chosenTime : Int = 0
}