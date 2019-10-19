﻿using System;
using System.Collections.Generic;
using Realms;

namespace CoolkyTools
{
    public class Recipe
    {
        public string Id { get; set; }
        public string DishName { get; set; }
        public string CookTime { get; set; }
        public string Cuisine { get; set; }
        public string Type { get;  }
        public string PortionAmount { get;  }
        public IList<string> Ingredients { get; }
        public IList<string> Steps { get; }

        public Recipe() { }

        public Recipe(string id, string dishName, string cookTime, string cuisine, string type, string portionAmount,
                IList<string> ingredients, IList<string> steps)
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
