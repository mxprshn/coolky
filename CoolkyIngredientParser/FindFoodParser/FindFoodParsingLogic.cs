using System.Collections.Generic;
using AngleSharp.Dom;

namespace CoolkyIngredientParser.FindFoodParser
{
    class FindFoodParsingLogic : IParsingLogic
    {
        public IList<string> GetNames(IDocument page)
        {
            var nameElements = page.QuerySelectorAll(".grid_4.view [title][href]");
            var result = new List<string>();

            foreach (var ingredientNameElement in nameElements)
            {
                result.Add(ingredientNameElement.GetAttribute("title"));
            }

            return result;
        }

        public string GetType(IDocument page)
        {
            return null;
        }
    }
}