package com.coolteam.coolky.searchpage

class SearchRequest {
    public lateinit var chosenIngredients : Array<String>
    public lateinit var chosenTypesOfDishes : Array<String>
    public lateinit var chosenCuisines : Array<String>
    public var chosenTime : Int = 0
}