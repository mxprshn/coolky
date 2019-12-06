using Realms;
using System;
using System.Collections.Generic;
using System.Text;

namespace CoolkyRecipeParser
{
    class RecipeIngredient : RealmObject
    {
        //public string RecipeId { get; set; }
        //public string IngredientId { get; set; }

        [MapTo("recipe")]
        public Recipe Recipe { get; set; }

        [MapTo("ingredient")]
        public Ingredient Ingredient { get; set; }

        [MapTo("amount")]
        public string Amount { get; set; }

        public RecipeIngredient() {}

        public RecipeIngredient(Recipe recipe, Ingredient ingredient, string amount)
        {
            Recipe = recipe;
            Ingredient = ingredient;
            Amount = amount;
        }
    }
}