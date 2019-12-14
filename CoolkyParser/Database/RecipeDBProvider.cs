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
            using (var realm = Realm.GetInstance(config))
            {
                return realm.Find<Ingredient>(name);
            }            
        }

        public static Recipe FindRecipeById(string id)
        {
            using (var realm = Realm.GetInstance(config))
            {
                return realm.Find<Recipe>(id);
            }            
        }

        public static bool IngredientExists(string name) => FindIngredientByName(name) != null;

        public static bool RecipeExists(string id) => FindRecipeById(id) != null;

        public static void AddRecipeIngredient(string recipeId, string ingredientName, string amount)
        {
            using (var realm = Realm.GetInstance(config))
            {
                realm.Write(() =>
                {
                    realm.Add(new RecipeIngredient(realm.Find<Recipe>(recipeId), realm.Find<Ingredient>(ingredientName), amount));
                });
            }
        }

        public static void AddRecipe(string id, string dishName, int cookTime, string cuisine, string type, int portionAmount,
                string pictureUrl, IList<string> steps, string webSite, int ingredientAmount)
        {
            using (var realm = Realm.GetInstance(config))
            {
                realm.Write(() =>
                {
                    realm.Add(new Recipe(id, dishName, cookTime, cuisine, type, portionAmount, pictureUrl, webSite, ingredientAmount, steps));
                });
            }
        }

        public static void UpdateRecipe(string id, string dishName, int cookTime, string cuisine, string type, int portionAmount,
                string pictureUrl, IList<string> steps, string webSite)
        {
            using (var realm = Realm.GetInstance(config))
            {
                var existingRecipe = realm.Find<Recipe>(id);

                realm.Write(() =>
                {
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
                });
            }
        }

        public static void AddIngredient(string name)
        {
            using (var realm = Realm.GetInstance(config))
            {
                realm.Write(() =>
                {
                    realm.Add(new Ingredient { Name = name });
                });
            }
        }
    }
}
