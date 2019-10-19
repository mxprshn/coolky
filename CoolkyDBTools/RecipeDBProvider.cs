using System;
using System.Collections.Generic;
using System.IO;
using System.Text;
using System.Threading.Tasks;
using Realms;

namespace CoolkyTools
{
    public static class RecipeDBProvider
    {
        private static RealmConfiguration configuration = new RealmConfiguration($"{Directory.GetCurrentDirectory()}/Recipes.realm");

        // правильно ли сделано асинхронно?
        public static async Task AddRecipe(Recipe recipe)
        { 
            var realm = Realm.GetInstance(configuration);
            //realm.Write(() => realm.Add(recipe));
        }
    }
}
