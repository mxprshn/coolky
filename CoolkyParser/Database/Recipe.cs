using System;
using System.Collections.Generic;
using System.Linq;
using Realms;

namespace CoolkyRecipeParser
{
    public class Recipe : RealmObject
    {
        [PrimaryKey]
        [MapTo("id")]
        public string Id { get; set; }

        [MapTo("dishName")]
        public string DishName { get; set; }

        [MapTo("cookTime")]
        public int CookTime { get; set; }

        [MapTo("cuisine")]
        public string Cuisine { get; set; }

        [MapTo("type")]
        public string Type { get; set; }

        [MapTo("portionAmount")]
        public int PortionAmount { get; set; }

        [MapTo("pictureUrl")]
        public string PictureUrl { get; set; }

        [MapTo("webSite")]
        public string WebSite { get; set; }

        [MapTo("ingredientAmount")]
        public int IngredientAmount { get; set; }

        [MapTo("steps")]
        public IList<string> Steps { get; }

        public Recipe() { }

        public Recipe(string id, string dishName, int cookTime, string cuisine, string type, int portionAmount,
                string pictureUrl, string webSite, int ingredientAmount, IList<string> steps)
        {
            Id = id;
            DishName = dishName;
            CookTime = cookTime;
            Cuisine = cuisine;
            Type = type;
            Steps = steps;
            PortionAmount = portionAmount;
            PictureUrl = pictureUrl;
            WebSite = webSite;
            IngredientAmount = ingredientAmount;
        }
    }
}
