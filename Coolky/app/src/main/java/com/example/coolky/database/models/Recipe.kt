package com.example.coolky.database.models

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Recipe : RealmObject()
{
    @PrimaryKey
    public var Id: String? = null

    public var DishName: String? = null
    public var CookTime: Int = 0
    public var Cuisine: String? = null
    public var Type: String? = null
    public var PortionAmount: Int = 0
    public var PictureUrl: String? = null
    public var WebSite: String? = null
    public var Steps: RealmList<String> = RealmList()
}