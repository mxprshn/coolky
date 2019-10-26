package com.example.coolky.database

import io.realm.RealmList
import io.realm.RealmModel
import io.realm.annotations.PrimaryKey


open class Recipe : RealmModel {
    @PrimaryKey
    var id: String? = null

    var dishName: String? = null
    var cookTime: Int = 0
    var cuisine: String? = null
    var type: String? = null
    var portionAmount: Int = 0
    var pictureUrl: String? = null
    var webSite: String? = null
    var ingredients: RealmList<Ingredient> = RealmList()
    var steps: RealmList<String> = RealmList()
}