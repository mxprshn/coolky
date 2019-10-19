using System.Collections.Generic;
using AngleSharp.Dom;

namespace CoolkyRecipeParser.HrumkaParser
{
    class HrumkaParsingLogic : IParsingLogic
    {
        public string GetId(IDocument page)
        {
            var idElement = page.QuerySelector("[data-cid]");
            return idElement.GetAttribute("data-cid");
        }

        public string GetDishName(IDocument page)
        {
            var nameElement = page.QuerySelector("h1");
            return nameElement.Text();
        }

        public string GetCookTime(IDocument page)
        {
            var cookTimeElement = page.QuerySelector(".cooktime");
            return cookTimeElement.Text().TrimStart(' ');
        }

        public string GetCuisine(IDocument page)
        {
            return "CUISINE";
        }

        public List<string> GetIngredients(IDocument page)
        {
            var ingredientElements = page.QuerySelectorAll("[itemprop=\"recipeIngredient\"]");
            var result = new List<string>();

            foreach (var ingredientNameElement in ingredientElements)
            {
                result.Add(ingredientNameElement.GetAttribute("content"));
            }

            return result;
        }

        public List<string> GetSteps(IDocument page)
        {
            // ".instruction || .instructions" nullrefexception
            var stepElements = page.QuerySelectorAll(".instruction");

            // может покрасивее это сделать
            if (stepElements.Length == 0)
            {
                // надо как-то разбить по <br>
                stepElements = page.QuerySelectorAll(".instructions");
            }

            var result = new List<string>();

            foreach (var stepElement in stepElements)
            {
                result.Add(stepElement.Text());
            }

            return result;
        }

        public string GetType(IDocument page)
        {
            return "TYPE";
        }

        public string GetPortionAmount(IDocument page)
        {
            var portionAmountElement = page.QuerySelector("[id=\"r_kolvo_porcij\"]");
            return portionAmountElement.Text();
        }
    }
}