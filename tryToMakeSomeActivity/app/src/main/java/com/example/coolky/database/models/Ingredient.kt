package com.example.coolky.database.models

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Ingredient : RealmObject()
{
    @PrimaryKey
    public var Name: String? = null
}