using System;
using System.Threading.Tasks;
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

        private async Task ProcessRecipe(string url, ParsingContext context, IParsingLogic logic)
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

                if (ingredients.Select(i => i.name).Distinct().Count() != ingredients.Count())
                {
                    return;
                }

                if (RecipeDBProvider.RecipeExists(id))
                {
                    RecipeDBProvider.UpdateRecipe(id, context.GetDishName(logic, page), context.GetCookTime(logic, page), context.GetCuisine(logic, page),
                            context.GetType(logic, page), context.GetPortionAmount(logic, page), context.GetPictureUrl(logic, page), context.GetSteps(logic, page), context.GetWebSite());
                }
                else
                {
                    RecipeDBProvider.AddRecipe(id, context.GetDishName(logic, page), context.GetCookTime(logic, page), context.GetCuisine(logic, page),
                            context.GetType(logic, page), context.GetPortionAmount(logic, page), context.GetPictureUrl(logic, page), context.GetSteps(logic, page), context.GetWebSite(), ingredients.Count());

                    foreach (var ingredient in ingredients)
                    {
                        if (!RecipeDBProvider.IngredientExists(ingredient.name))
                        {
                            RecipeDBProvider.AddIngredient(ingredient.name);
                        }

                        RecipeDBProvider.AddRecipeIngredient(id, ingredient.name, ingredient.amount);
                    }
                }
            }
            catch (Exception exc)
            {
                Console.WriteLine($"Error occured during page parsing: {exc.Message}");
            }
        }

        public async Task ParseAsync()
        {
            var logic = factory.GetLogic();

            foreach (var context in factory.GetContexts())
            {
                var urls = await context.GetPages();
                var counter = 0;

                await urls.ParallelForEachAsync((url) => 
                {
                    Interlocked.Increment(ref counter);
                    Console.WriteLine($"Parsing recipe {counter} of {urls.Count()} in {Thread.CurrentThread.ManagedThreadId} thread.");
                    return ProcessRecipe(url, context, logic);
                });
            }
        }
    }
}