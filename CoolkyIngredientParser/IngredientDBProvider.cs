using System;
using System.Collections.Generic;
using System.IO;
using System.Text;
using System.Threading.Tasks;
using Realms;
using System.Linq;

namespace CoolkyIngredientParser
{
    public static class IngredientDBProvider
    {
        private static RealmConfiguration configuration = new RealmConfiguration($"Ingredients.realm");

        // правильно ли сделано асинхронно?
        public static async Task AddIngredient(RawIngredient ingredient)
        { 
            var realm = Realm.GetInstance(configuration);
            realm.Write(() => realm.Add(ingredient));
        }

        public static async Task<string> FindType(string name)
        {
            var database = Realm.GetInstance(configuration);
            var hash = new Dictionary<string, int>()
            {
                { "овощи", 0 },
                { "фрукты", 0 },
                { "грибы", 0 },
                { "яйца и молочные продукты", 0 },
                { "мясо", 0 },
                { "рыба и морепродукты", 0 },
                { "орехи и сухофрукты", 0 },
                { "мука и мучные изделия", 0 },
                { "крупы", 0 },
                { "кондитерские изделия", 0 },
                { "зелень", 0 },
                { "специи", 0 },
                { "добавки", 0 },
                { "безалкогольные напитки", 0 },
                { "алкогольные напитки", 0 },
            };

            foreach (var word in name.Split(' '))
            {
                foreach (var ingredient in database.All<RawIngredient>())
                {
                    if (ingredient.Name.Split(' ').Contains(word))
                    {
                        ++hash[ingredient.Type];
                    }

                    foreach (var synonym in ingredient.Synonyms)
                    {
                        if (synonym.Split(' ').Contains(word))
                        {
                            ++hash[ingredient.Type];
                        }
                    }
                }
            }

            return hash.FirstOrDefault(x => x.Value == hash.Values.Max() && x.Value != 0).Key;
        }
    }
}
