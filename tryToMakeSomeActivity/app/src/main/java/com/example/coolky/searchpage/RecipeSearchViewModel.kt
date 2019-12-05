package com.example.coolky.searchpage

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RecipeSearchViewModel : ViewModel() {
    public var ChosenIngredients = MutableLiveData<ArrayList<String>>()
    public var ChosenTypesOfDishes = MutableLiveData<ArrayList<String>>()
    public var ChosenCuisines = MutableLiveData<ArrayList<String>>()
    public var ChosenTime = MutableLiveData<Int>()
}