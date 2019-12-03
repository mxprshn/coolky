package com.example.coolky.database

import com.example.coolky.database.models.Ingredient
import com.example.coolky.database.models.Recipe
import com.example.coolky.database.models.RecipeIngredient
import io.realm.annotations.RealmModule

@RealmModule(classes = [Ingredient::class, Recipe::class, RecipeIngredient::class])
class BundledRealmModule