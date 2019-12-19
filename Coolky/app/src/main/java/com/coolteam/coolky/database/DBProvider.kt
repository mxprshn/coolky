package com.coolteam.coolky.database

import android.annotation.SuppressLint
import com.coolteam.coolky.database.models.Favourite
import com.coolteam.coolky.database.models.Ingredient
import com.coolteam.coolky.database.models.Recipe
import com.coolteam.coolky.database.models.RecipeIngredient
import io.realm.*
import io.realm.kotlin.oneOf
import io.realm.kotlin.where
import java.util.*


class DBProvider
{
    companion object
    {
        private var favouriteRecipesConfig: RealmConfiguration = RealmConfiguration.Builder()
            .name("favourites.realm")
            .build()

        public fun isFavourite(recipeId: String): Boolean {
            return Realm.getInstance(favouriteRecipesConfig).where<Favourite>().equalTo("recipe.id", recipeId).findAll().isNotEmpty()
        }

        public fun setFavourite(recipeId: String, isFavourite: Boolean) {
            val database = Realm.getInstance(favouriteRecipesConfig)
            val isAlreadyFavourite = database.where<Favourite>().equalTo("recipe.id", recipeId).findAll().isNotEmpty()

            if (isFavourite && !isAlreadyFavourite) {
                database.beginTransaction()
                val favourite:Favourite = database.createObject(User::class.java) // Create a new object

                user.setName("John")
                user.setEmail("john@corporation.com")
                realm.commitTransaction()
            }

            if (database.where<Favourite>().equalTo("recipe.id", recipeId).findAll().isNotEmpty()
        }

        public fun getFavourites(): RealmResults<Favourite> {

        }

        // это нужно как-то сделать аля статическим
        public fun getIngredients(input: String): RealmResults<Ingredient> {
            val database = Realm.getDefaultInstance()
            return database.where<Ingredient>().beginsWith("name", input)
                .or().contains("name", " $input").findAll()
        }

        public fun getRecipes(ingredients: Array<String>, types : Array<String>, cuisines: Array<String>
                , time: Int): RealmResults<Recipe>
        {
            val database = Realm.getDefaultInstance()
            var recipeQuery = database.where<Recipe>()


            for (ingredient in ingredients)
            {
                recipeQuery = recipeQuery.equalTo("recipeIngredients.ingredient.name", ingredient)
            }

            if (types.isNotEmpty())
            {
                recipeQuery = recipeQuery.oneOf("type", types)
            }

            if (cuisines.isNotEmpty())
            {
                recipeQuery = recipeQuery.oneOf("cuisine", cuisines)
            }

            return recipeQuery.lessThan("cookTime", time).sort("ingredientAmount", Sort.ASCENDING).findAll()

        }

        public fun getCount(): Long {
            val database = Realm.getDefaultInstance()
            return database.where<Recipe>().count()
        }

        // + асинхронность
        public fun findRecipeById(recipeId: String): Recipe? {
            val database = Realm.getDefaultInstance()
            return database.where<Recipe>().equalTo("id", recipeId).findFirst()
        }

        public fun findIngredientByName(ingredientName: String) : Ingredient? {
            val database = Realm.getDefaultInstance()
            return database.where<Ingredient>().equalTo("name", ingredientName).findFirst()
        }

        public fun findRecipeIngredientsById(recipeId: String) : RealmResults<RecipeIngredient> {
            val database = Realm.getDefaultInstance()
            return database.where<RecipeIngredient>().equalTo("recipe.id", recipeId).findAll()
        }

        @SuppressLint("DefaultLocale")
        public fun findRecipesByName(recipeName : String): OrderedRealmCollection<Recipe> {
            val database = Realm.getDefaultInstance()
            val recipeNameQuery = database.where<Recipe>()
            val name = recipeName.toLowerCase().capitalize()

            recipeNameQuery.contains("dishName", name)
            recipeNameQuery.or()
            recipeNameQuery.contains("dishName", name.toLowerCase(Locale.getDefault()))

            return recipeNameQuery.findAll()
        }
    }
}