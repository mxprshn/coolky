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

        public static void Structurize(IStructurizer structurizer)
        {
            var source = Realm.GetInstance(tempConfiguration);
            var target = Realm.GetInstance(configuration);

            foreach (var recipe in source.All<UnstructuredRecipe>())
            {
                target.Write(() => target.Add(structurizer.Structurize(recipe)));
            }
        }
    }
}
