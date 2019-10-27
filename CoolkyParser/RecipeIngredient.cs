using Realms;
using System;
using System.Collections.Generic;
using System.Text;

namespace CoolkyRecipeParser
{
    class RecipeIngredient : RealmObject
    {
        public string RecipeId { get; set; }
        public string IngredientId { get; set; }
        public string Amount { get; set; }

        public RecipeIngredient() {}

        public RecipeIngredient(string recipeId, string ingredientId, string amount)
        {
            RecipeId = recipeId;
            IngredientId = ingredientId;
            Amount = amount;
        }
    }
}