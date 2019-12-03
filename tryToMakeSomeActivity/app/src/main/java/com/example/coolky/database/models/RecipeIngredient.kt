package com.example.coolky.database.models

import io.realm.RealmObject

open class RecipeIngredient : RealmObject()
{
    public var Recipe: Recipe? = null
    public var Ingredient: Ingredient? = null
    public var Amount: String? = null
}