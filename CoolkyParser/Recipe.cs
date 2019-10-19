using System;
using System.Collections.Generic;

namespace CoolkyRecipeParser
{
    public class Recipe
    {
        public string Id { get; private set; }
        public string DishName { get; private set; }
        public string CookTime { get; private set; }
        public string Cuisine { get; private set; }
        public string Type { get; private set; }
        public string PortionAmount { get; private set; }
        public List<string> Ingredients { get; private set; }
        public List<string> Steps { get; private set; }

        public Recipe(string id, string dishName, string cookTime, string cuisine, string type, string portionAmount,
                List<string> ingredients, List<string> steps)
        {
            Id = id;
            DishName = dishName;
            CookTime = cookTime;
            Cuisine = cuisine;
            Type = type;
            Ingredients = ingredients;
            Steps = steps;
            PortionAmount = portionAmount;
        }

        public void Print()
        {
            Console.WriteLine(Id);
            Console.WriteLine(DishName);
            Console.WriteLine(Type);
            Console.WriteLine(Cuisine);
            Console.WriteLine(CookTime);
            Console.WriteLine(PortionAmount);

            foreach (var ingredient in Ingredients)
            {
                Console.WriteLine(ingredient);
            }

            foreach (var step in Steps)
            {
                Console.WriteLine(step);
            }

            Console.WriteLine();
        }
    }
}
