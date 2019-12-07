package com.example.coolky.database.models

import io.realm.RealmObject

open class RecipeIngredient : RealmObject()
{
    public var recipe: Recipe? = null
    public var ingredient: Ingredient? = null
    public var amount: String? = null
}