package com.coolteam.coolky.database.models

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.RealmResults
import io.realm.annotations.LinkingObjects
import io.realm.annotations.PrimaryKey

open class Recipe : RealmObject()
{
    @PrimaryKey
    public var id: String? = null

    public var dishName: String? = null
    public var cookTime: Int = 0
    public var cuisine: String? = null
    public var type: String? = null
    public var portionAmount: Int = 0
    public var pictureUrl: String? = null
    public var webSite: String? = null
    public var steps: RealmList<String> = RealmList()
    public var ingredientAmount: Int = 0

    @LinkingObjects("recipe")
    public final val recipeIngredients: RealmResults<RecipeIngredient>? = null
}