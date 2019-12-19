package com.coolteam.coolky.database.models

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Favourite : RealmObject()
{
    @PrimaryKey
    public var recipe: Recipe? = null
}