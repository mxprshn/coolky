using System;
using System.Collections.Generic;
using System.IO;
using System.Text;
using System.Threading.Tasks;
using Realms;

namespace CoolkyIngredientParser
{
    public static class IngredientDBProvider
    {
        private static RealmConfiguration configuration = new RealmConfiguration($"Recipes.realm");

        // правильно ли сделано асинхронно?
        public static async Task AddIngredient(Ingredient ingredient)
        { 
            var realm = Realm.GetInstance(configuration);
            realm.Write(() => realm.Add(ingredient));
        }
    }
}
