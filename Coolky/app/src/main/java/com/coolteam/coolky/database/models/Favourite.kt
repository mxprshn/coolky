package com.coolteam.coolky.database.models

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Favourite : RealmObject()
{
    public var recipeId: String? = null
}