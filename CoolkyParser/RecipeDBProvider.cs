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

        public static string FindIngredientIdByName(string name)
        {
            var database = Realm.GetInstance(config);
            return database.All<Ingredient>().Where(i => i.Name == name).First().Id;
        }

        public static bool IngredientExists(string name)
        {
            var database = Realm.GetInstance(config);
            return database.All<Ingredient>().Where(i => i.Name == name).Count() != 0;
        }

        public static async Task AddRecipeIngredient(string recipeId, string ingredientId, string amount)
        {
            var database = Realm.GetInstance(config);

            database.Write(() =>
            {
                database.Add(new RecipeIngredient(recipeId, ingredientId, amount));
            });
        }

        // правильно ли сделано асинхронно?
        public static async Task AddRecipe(string id, string dishName, int cookTime, string cuisine, string type, int portionAmount,
                string pictureUrl, IList<string> steps, string webSite)
        {
            var database = Realm.GetInstance(config);
            database.Write(() =>
            {
                var existingRecipe = database.Find<Recipe>(id);

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
                    database.Add(new Recipe(id, dishName, cookTime, cuisine, type, portionAmount, pictureUrl, webSite, steps));
                }
            });      
        }

        // правильно ли сделано асинхронно?
        public static async Task AddIngredient(string name)
        {
            var database = Realm.GetInstance(config);
            //var type = await CoolkyIngredientParser.IngredientDBProvider.FindType(name);
            database.Write(() =>
            {
                database.Add(new Ingredient { Name = name });
            });
        }
    }
}
