using System;
using System.Collections.Generic;
using System.IO;
using System.Text;
using System.Threading.Tasks;
using Realms;

namespace CoolkyRecipeParser
{
    public static class RecipeDBProvider
    {
        private static RealmConfiguration configuration = new RealmConfiguration($"Recipes.realm");

        // правильно ли сделано асинхронно?
        public static async Task AddRecipe(Recipe recipe)
        { 
            var realm = Realm.GetInstance(configuration);
            realm.Write(() => realm.Add(recipe));
        }
    }
}
