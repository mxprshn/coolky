package com.example.coolky.searchpage

import androidx.lifecycle.ViewModel

class RecipeSearchViewModel : ViewModel() {
    private lateinit var ChosenIngredients : ArrayList<String>
    private lateinit var ChosenTypesOfDishes : ArrayList<String>
    private lateinit var ChosenCuisines : ArrayList<String>
    private var ChosenTime : Int = 0

    public fun SetIngredients(ingredients: ArrayList<String>) {
        ChosenIngredients = ingredients
    }

    public fun GetIngredients(): ArrayList<String> {
        return ChosenIngredients
    }

    public fun SetTypesOfDishes(typesOfDishes: ArrayList<String>) {
        ChosenTypesOfDishes = typesOfDishes
    }

    public fun GetTypesOfDishes(): ArrayList<String> {
        return ChosenTypesOfDishes
    }

    public fun SetCuisines(cuisines: ArrayList<String>) {
        ChosenCuisines = cuisines
    }

    public fun GetCuisines(): ArrayList<String> {
        return ChosenCuisines
    }

    public fun SetTime(time: Int) {
        ChosenTime = time
    }

    public fun GetTime(): Int {
        return ChosenTime
    }
}