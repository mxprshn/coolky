package com.coolteam.coolky

import android.app.Application
import io.realm.Realm
import com.coolteam.coolky.database.BundledRealmModule
import io.realm.RealmConfiguration


class Coolky : Application()
{
    public override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        val config = RealmConfiguration.Builder().assetFile("recipes.realm").readOnly()
            .modules(BundledRealmModule()).build()
        Realm.setDefaultConfiguration(config)

//        val testProvider = DBProvider()
//        val ingredients = testProvider.getIngredients("hello")
//        val recipes = testProvider.getRecipes()
//
//        Log.d("INGREDIENT", ingredients!!.first()!!.Name)
//        Log.d("INGREDIENT", ingredients.count().toString())
//        Log.d("INGREDIENT", ingredients!!.last()!!.Name)

//        Log.d("INGREDIENT", recipes!!.first()!!.DishName)
//        Log.d("INGREDIENT", recipes.count().toString())
//        Log.d("INGREDIENT", recipes!!.last()!!.DishName)
    }
}