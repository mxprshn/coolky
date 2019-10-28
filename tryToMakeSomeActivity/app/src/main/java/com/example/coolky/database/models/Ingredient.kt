package com.example.coolky.database.models

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Ingredient : RealmObject()
{
    @PrimaryKey
    public var Id: String? = null

    public var Name: String? = null
    public var Type: String? = null

}