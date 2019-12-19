using System;
using System.Threading.Tasks;
using System.Collections.Concurrent;
using AngleSharp.Dom;
using System.Linq;
using System.Threading;

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
                var urls = await context.GetPages();
                var counter = 0;

                await urls.ForEachAsync(async (url) =>
                {
                    IDocument page = null;

                    try
                    {
                        page = await HtmlLoader.LoadAsync(url);
                    }
                    catch
                    {
                        Console.WriteLine($"Error downloading page {url}");
                        return;
                    }

                    try
                    {
                        var ingredients = context.GetIngredients(logic, page);
                        var id = context.GetId(logic, page);

                        Interlocked.Increment(ref counter);
                        Console.WriteLine($"Parsing recipe {counter} of {urls.Count()} in {Thread.CurrentThread.ManagedThreadId} thread.");

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
                    }
                });
            }
        }
    }
}
