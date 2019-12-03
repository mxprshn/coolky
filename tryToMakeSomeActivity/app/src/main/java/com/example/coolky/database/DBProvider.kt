package com.example.coolky.database

import com.example.coolky.database.models.Ingredient
import com.example.coolky.database.models.Recipe
import com.example.coolky.database.models.RecipeIngredient
import io.realm.Realm
import io.realm.RealmResults
import io.realm.kotlin.where

class DBProvider
{
    companion object
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

        // + асинхронность
        public fun findRecipeById(recipeId: String) : Recipe?
        {
            val database = Realm.getDefaultInstance()
            return database.where<Recipe>().equalTo("Id", recipeId).findFirst()
        }

        public fun findIngredientById(ingredientId: String) : Ingredient?
        {
            val database = Realm.getDefaultInstance()
            return database.where<Ingredient>().equalTo("Id", ingredientId).findFirst()
        }

        public fun findRecipeIngredientsById(recipeId: String) : RealmResults<RecipeIngredient>
        {
            val database = Realm.getDefaultInstance()
            return database.where<RecipeIngredient>().equalTo("RecipeId", recipeId).findAll()
        }
    }
}