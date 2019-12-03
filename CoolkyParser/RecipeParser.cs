﻿using System;
using System.Threading.Tasks;

namespace CoolkyRecipeParser
{
    public class RecipeParser
    {
        private IParserFactory factory;

        public RecipeParser(IParserFactory factory)
        {
            this.factory = factory;
        }

        public async Task ParseAsync()
        {
            var logic = factory.GetLogic();

            foreach (var context in factory.GetContexts())
            {
                await foreach (var page in context.GetPages())
                {
                    try
                    {
                        var ingredients = context.GetIngredients(logic, page);
                        var id = context.GetId(logic, page);

                        await RecipeDBProvider.AddRecipe(id, context.GetDishName(logic, page), context.GetCookTime(logic, page), context.GetCuisine(logic, page),
                                context.GetType(logic, page), context.GetPortionAmount(logic, page), context.GetPictureUrl(logic, page), context.GetSteps(logic, page), context.GetWebSite());

                        foreach (var ingredient in ingredients)
                        {
                            if (!RecipeDBProvider.IngredientExists(ingredient.name))
                            {
                                await RecipeDBProvider.AddIngredient(ingredient.name);
                            }

                            await RecipeDBProvider.AddRecipeIngredient(id, ingredient.name, ingredient.amount);
                        }
                    }
                    catch (Exception exc)
                    {
                        Console.WriteLine($"Error occured during page parsing: {exc.Message}");
                        continue;
                    }
                }
            }
        }
    }
}
