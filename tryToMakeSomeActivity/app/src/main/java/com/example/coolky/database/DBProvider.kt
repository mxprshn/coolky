package com.example.coolky.database

import com.example.coolky.database.models.Ingredient
import com.example.coolky.database.models.Recipe
import io.realm.Realm
import io.realm.RealmResults
import io.realm.kotlin.where

class DBProvider
{
    // это нужно как-то сделать аля статическим
    public fun getIngredients(input: String): RealmResults<Ingredient>
    {
        val database = Realm.getDefaultInstance()
        return database.where<Ingredient>().findAll()
    }

    public fun getRecipes(): RealmResults<Recipe>
    {
        val database = Realm.getDefaultInstance()
        return database.where<Recipe>().findAll()
    }
}