using System;
using System.Collections.Generic;
using Realms;
using CoolkyIngredientParser;

namespace CoolkyRecipeParser
{
    public class UnstructuredRecipe : RealmObject
    {
        public string Id { get; set; }
        public string DishName { get; set; }
        public string CookTime { get; set; }
        public string Cuisine { get; set; }
        public string Type { get; set; }
        public string PortionAmount { get; set; }
        public string WebSite { get; set; }
        public IList<string> Ingredients { get; }
        public IList<string> Steps { get; }

        public UnstructuredRecipe() { }

        public UnstructuredRecipe(string id, string dishName, string cookTime, string cuisine, string type, string portionAmount,
                IList<string> ingredients, IList<string> steps, string webSite)
        {
            Id = id;
            DishName = dishName;
            CookTime = cookTime;
            Cuisine = cuisine;
            Type = type;
            Ingredients = ingredients;
            Steps = steps;
            PortionAmount = portionAmount;
            WebSite = webSite;
        }
    }
}
