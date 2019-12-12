using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Realms;
using Realms.Exceptions;

namespace CoolkyRecipeParser
{
    public static class RecipeDBProvider
    {
        private static RealmConfiguration config = new RealmConfiguration($"Recipes.realm");

        public static Ingredient FindIngredientByName(string name)
        {
            var realm = Realm.GetInstance(config);
            return realm.Find<Ingredient>(name);
        }

        public static Recipe FindRecipeById(string id)
        {
            var realm = Realm.GetInstance(config);
            return realm.Find<Recipe>(id);
        }

        public static bool IngredientExists(string name)
        {
            var realm = Realm.GetInstance(config);
            return realm.Find<Ingredient>(name) != null;
        }

        public static async Task AddRecipeIngredient(string recipeId, string ingredientName, string amount)
        {
            var realm = Realm.GetInstance(config);

            await realm.WriteAsync(tempRealm =>
            {
                tempRealm.Add(new RecipeIngredient(FindRecipeById(recipeId), FindIngredientByName(ingredientName), amount));
            });
        }

        public static async Task AddRecipe(string id, string dishName, int cookTime, string cuisine, string type, int portionAmount,
                string pictureUrl, IList<string> steps, string webSite)
        {
            var realm = Realm.GetInstance(config);

            await realm.WriteAsync(tempRealm =>
            {
                var existingRecipe = realm.Find<Recipe>(id);

                if (existingRecipe != null)
                {
                    if (cuisine != null)
                    {
                        existingRecipe.Cuisine = cuisine;
                    }

                    if (type != null)
                    {
                        existingRecipe.Type = type;
                    }
                }
                else
                {
                    tempRealm.Add(new Recipe(id, dishName, cookTime, cuisine, type, portionAmount, pictureUrl, webSite, steps));
                }
            });
        }

        public static async Task AddIngredient(string name)
        {
            var realm = Realm.GetInstance(config);

            await realm.WriteAsync(tempRealm =>
            {
                tempRealm.Add(new Ingredient { Name = name });
            });
        }
    }
}
