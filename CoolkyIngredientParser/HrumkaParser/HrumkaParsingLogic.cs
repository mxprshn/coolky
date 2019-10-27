using System.Collections.Generic;
using AngleSharp.Dom;

namespace CoolkyIngredientParser.HrumkaParser
{
    class HrumkaParsingLogic : IParsingLogic
    {
        public IList<string> GetNames(IDocument page)
        {
            var nameElements = page.QuerySelectorAll("[id=\"food-table\"] tr td:first-child");
            var result = new List<string>();

            foreach (var ingredientNameElement in nameElements)
            {
                result.Add(ingredientNameElement.Text().ToLower());
            }

            return result;
        }

        public string GetType(IDocument page)
        {
            return null;
        }
    }
}