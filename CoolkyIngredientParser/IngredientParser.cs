using System;
using System.Collections.Generic;
using System.Text;
using System.Threading.Tasks;

namespace CoolkyIngredientParser
{
    public class IngredientParser
    {
        private IParserFactory factory;

        public IngredientParser(IParserFactory factory)
        {
            this.factory = factory;
        }

        public async Task ParseAsync()
        {
            var logic = factory.GetLogic();
            var list = new List<Ingredient>();

            foreach (var context in factory.GetContexts())
            {
                await foreach (var page in context.GetPages())
                {
                    foreach (var name in context.GetNames(logic, page))
                    {
                        var ingredient = new Ingredient(context.GetType(logic, page), name);
                        ingredient.Print();
                        await IngredientDBProvider.AddIngredient(ingredient);
                    }                    
                }
            }
        }
    }
}
