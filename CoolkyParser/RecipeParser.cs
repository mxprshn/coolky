using System.Collections.Generic;
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
                    var recipe = new Recipe(context.GetId(logic, page), context.GetDishName(logic, page), context.GetCookTime(logic, page), context.GetCuisine(logic, page),
                            context.GetType(logic, page), context.GetPortionAmount(logic, page), context.GetIngredients(logic, page), context.GetSteps(logic, page));
                    //recipe.Print();
                    await RecipeDBProvider.AddRecipe(recipe);
                }
            }
        }
    }
}
