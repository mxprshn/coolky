package com.coolteam.coolky.database

import com.coolteam.coolky.database.models.Favourite
import com.coolteam.coolky.database.models.Ingredient
import com.coolteam.coolky.database.models.Recipe
import com.coolteam.coolky.database.models.RecipeIngredient
import io.realm.annotations.RealmModule

@RealmModule(classes = [Ingredient::class, Recipe::class, RecipeIngredient::class])
class BundledRealmModule