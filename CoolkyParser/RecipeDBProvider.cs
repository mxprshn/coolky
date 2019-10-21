using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using CoolkyIngredientParser;
using Realms;
using Realms.Exceptions;

namespace CoolkyRecipeParser
{
    public static class RecipeDBProvider
    {
        private static RealmConfiguration tempConfiguration = new RealmConfiguration($"RecipesTemp.realm");
        private static RealmConfiguration configuration = new RealmConfiguration($"Recipes.realm");

        // правильно ли сделано асинхронно?
        public static async Task AddUnstructuredRecipe(UnstructuredRecipe recipe)
        { 
            var realm = Realm.GetInstance(tempConfiguration);

            try
            {
                realm.Write(() => realm.Add(recipe));
            }
            catch (RealmDuplicatePrimaryKeyValueException exception)
            {
                Console.WriteLine($"Duplicate recipe {recipe.Id} was not added.");
            }            
        }

        private static IList<Ingredient> GetTags(IList<string> ingredientTexts)
        {
            var result = new List<Ingredient>();
            var realm = Realm.GetInstance(configuration);

            foreach (var ingredientText in ingredientTexts)
            {
                foreach (var ingredient in realm.All<Ingredient>())
                {
                    if (ingredientText.ToLower().Contains(ingredient.Name))
                    {
                        result.Add(ingredient);
                    }
                }
            }

            return result;
        }

        public static void Structurize(IStructurizer structurizer)
        {
            var source = Realm.GetInstance(tempConfiguration);
            var target = Realm.GetInstance(configuration);

            foreach (var current in source.All<UnstructuredRecipe>())
            {
                var recipe = new Recipe(current.Id, current.DishName, structurizer.StructurizeCookTime(current.CookTime), current.Cuisine,
                            current.Type, structurizer.StructurizePortionAmount(current.PortionAmount), current.PictureUrl,
                            structurizer.StructurizeIngredientText(current.Ingredients), GetTags(current.Ingredients),
                            structurizer.StructurizeSteps(current.Steps), current.WebSite);
                target.Write(() => target.Add(recipe));
            }
        }
    }
}
