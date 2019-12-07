package com.coolteam.coolky.searchpage

import androidx.lifecycle.ViewModel

class RecipeSearchViewModel : ViewModel() {
    public var chosenIngredients = ArrayList<String>()
    public var chosenTypesOfDishes = ArrayList<String>()
    public var chosenCuisines = ArrayList<String>()
    public var chosenTime : Int = 0


    public fun Add(text : String, choice : Int) {
        when(choice) {
            0 -> AddIngredient(text)
            1 -> AddType(text)
            2 -> AddCuisine(text)
        }
    }

    public fun Remove(text: String, choice: Int) {
        when(choice) {
            0 -> RemoveIngredient(text)
            1 -> RemoveType(text)
            2 -> RemoveCuisine(text)
        }
    }

    private fun AddIngredient(ingredient : String) {
        chosenIngredients.add(ingredient)
        chosenIngredients.sort()
    }

    private fun RemoveIngredient(ingredient: String) {
        chosenIngredients.remove(ingredient)
        chosenIngredients.sort()
    }

    private fun AddType(type : String) {
        chosenTypesOfDishes.add(type)
        chosenTypesOfDishes.sort()
    }

    private fun RemoveType(type : String) {
        chosenTypesOfDishes.remove(type)
        chosenTypesOfDishes.sort()
    }

    private fun AddCuisine(cuisine: String) {
        chosenCuisines.add(cuisine)
        chosenCuisines.sort()
    }

    private fun RemoveCuisine(cuisine: String) {
        chosenCuisines.remove(cuisine)
        chosenCuisines.sort()
    }
}