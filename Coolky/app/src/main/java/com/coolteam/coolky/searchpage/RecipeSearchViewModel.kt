package com.coolteam.coolky.searchpage

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*
import kotlin.collections.ArrayList

class RecipeSearchViewModel : ViewModel() {
    public var chosenIngredients = MutableLiveData<ArrayList<String>>()
    public var chosenTypesOfDishes = MutableLiveData<ArrayList<String>>()
    public var chosenCuisines = MutableLiveData<ArrayList<String>>()
    public var chosenTime = MutableLiveData<Int>()

    init {
        chosenIngredients.value = ArrayList()
        chosenTypesOfDishes.value = ArrayList()
        chosenCuisines.value = ArrayList()
    }

    public fun remove(text : String) {
        if (chosenIngredients.value!!.contains(text)) {
            removeIngredient(text)
        }
        else if (chosenTypesOfDishes.value!!.contains(text)) {
            removeType(text)
        }
        else if (chosenCuisines.value!!.contains(text)) {
            removeCuisine(text)
        }
    }

    @SuppressLint("DefaultLocale")
    public fun addIngredients(ingredients: ArrayList<String>) {
        if (ingredients.isEmpty()) {
            return
        }
        val tmp = chosenIngredients.value!!

        for (e in ingredients) {
            if (!tmp.contains(e)) {
                tmp.add(e.capitalize())
            }
        }
        tmp.sort()
        chosenIngredients.postValue(tmp)
    }

    public fun addIngredient(ingredient: String) {
        val tmp = chosenIngredients.value!!
        if (!tmp.contains(ingredient)) {
            tmp.add(ingredient)
        }
        tmp.sort()
        chosenIngredients.postValue(tmp)
    }

    public fun removeIngredient(ingredient: String) {
        val tmp = chosenIngredients.value!!
        tmp.remove(ingredient)
        tmp.sort()
        chosenIngredients.postValue(tmp)
    }

    public fun addTypes(types : ArrayList<String>) {
        if (types.isEmpty()) {
            return
        }

        val tmp = chosenTypesOfDishes.value!!
        tmp.addAll(types)
        tmp.sort()
        chosenTypesOfDishes.postValue(tmp)
    }

    public fun removeType(type : String) {
        val tmp = chosenTypesOfDishes.value!!
        tmp.remove(type)
        tmp.sort()
        chosenTypesOfDishes.postValue(tmp)
    }

    public fun addCuisines(cuisines : ArrayList<String>) {
        if (cuisines.isEmpty()) {
            return
        }

        val tmp = chosenCuisines.value!!
        tmp.addAll(cuisines)
        tmp.sort()
        chosenCuisines.postValue(tmp)
    }

    public fun removeCuisine(cuisine: String) {
        val tmp = chosenCuisines.value!!
        tmp.remove(cuisine)
        tmp.sort()
        chosenCuisines.postValue(tmp)
    }
}