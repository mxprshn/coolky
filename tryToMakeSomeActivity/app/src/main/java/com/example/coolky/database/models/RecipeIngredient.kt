package com.example.coolky.database.models

import io.realm.RealmObject

open class RecipeIngredient : RealmObject()
{
    public var RecipeId: String? = null
    public var IngredientId: String? = null
    public var Amount: String? = null
}