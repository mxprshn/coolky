package com.example.coolky.database

import com.example.coolky.database.models.Ingredient
import com.example.coolky.database.models.Recipe
import com.example.coolky.database.models.RecipeIngredient
import io.realm.Realm
import io.realm.RealmResults
import io.realm.kotlin.oneOf
import io.realm.kotlin.where

class DBProvider
{
    companion object
    {
        // это нужно как-то сделать аля статическим
        public fun getIngredients(input: String): RealmResults<Ingredient>
        {
            val database = Realm.getDefaultInstance()
            return database.where<Ingredient>().contains("Name", " $input").or()
                .beginsWith("Name", input).findAll()
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

        public fun findIngredientByName(ingredientName: String) : Ingredient?
        {
            val database = Realm.getDefaultInstance()
            return database.where<Ingredient>().equalTo("Name", ingredientName).findFirst()
        }

        public fun findRecipeIngredientsById(recipeId: String) : RealmResults<RecipeIngredient>
        {
            val database = Realm.getDefaultInstance()
            return database.where<RecipeIngredient>().equalTo("Recipe.Id", recipeId).findAll()
        }

//        public fun findRecipeIngredientsByIngredients(ingredients: Array<String>) : RealmResults<RecipeIngredient>
//        {
//            val database = Realm.getDefaultInstance()
//            return database.where<RecipeIngredient>().oneOf("Ingredient.Name", ingredients).distinct("Recipe.Id").findAll()
//        }
    }
}