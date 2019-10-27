using System;
using System.Collections.Generic;
using System.Linq;
using Realms;

namespace CoolkyRecipeParser
{
    public class Recipe : RealmObject
    {
        [PrimaryKey]
        public string Id { get; set; }

        public string DishName { get; set; }
        public int CookTime { get; set; }
        public string Cuisine { get; set; }
        public string Type { get; set; }
        public int PortionAmount { get; set; }
        public string PictureUrl { get; set; }
        public string WebSite { get; set; }
        public IList<string> Steps { get; }

        public Recipe() { }

        public Recipe(string id, string dishName, int cookTime, string cuisine, string type, int portionAmount,
                string pictureUrl, string webSite, IList<string> steps)
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
        }
    }
}
