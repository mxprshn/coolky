using System.Collections.Generic;
using System.Text.RegularExpressions;
using AngleSharp.Dom;

namespace CoolkyRecipeParser.HrumkaParser
{
    class HrumkaParsingLogic : IParsingLogic
    {
        private int ConvertTime(string source)
        {
            var match = Regex.Match(source, "((?<days>\\d+) д)? *((?<hours>\\d+) ч)? *((?<minutes>\\d+) мин)?");

            var days = 0;
            var hours = 0;
            var minutes = 0;

            int.TryParse(match.Groups["days"].Value, out days);
            int.TryParse(match.Groups["hours"].Value, out hours);
            int.TryParse(match.Groups["minutes"].Value, out minutes);

            return 1440 * days + 60 * hours + minutes;
        }

        private (string, string) SplitIngredient(string source)
        {
            var match = Regex.Match(source, "(?<name>.+) - (?<amount>.+)");
            return (match.Groups["name"].Value.ToLower(), match.Groups["amount"].Value.TrimStart());
        }

        public string GetId(IDocument page)
        {
            var idElement = page.QuerySelector("[data-cid]");
            return $"0{idElement.GetAttribute("data-cid")}";
        }

        public string GetDishName(IDocument page)
        {
            var nameElement = page.QuerySelector("h1");
            return nameElement.Text();
        }

        public int GetCookTime(IDocument page)
        {
            var cookTimeElement = page.QuerySelector(".cooktime");
            return ConvertTime(cookTimeElement.Text().TrimStart(' '));
        }

        public string GetCuisine(IDocument page) => null;

        public IList<(string name, string amount)> GetIngredients(IDocument page)
        {
            var ingredientElements = page.QuerySelectorAll("[itemprop=\"recipeIngredient\"]");

            var result = new List<(string name, string amount)>();

            foreach (var ingredientNameElement in ingredientElements)
            {
                result.Add(SplitIngredient(ingredientNameElement.GetAttribute("content")));
            }

            return result;
        }

        public IList<string> GetSteps(IDocument page)
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

        public string GetType(IDocument page) => null;

        public int GetPortionAmount(IDocument page)
        {
            var portionAmountElement = page.QuerySelector("[id=\"r_kolvo_porcij\"]");
            return int.Parse(portionAmountElement.Text());
        }

        public string GetPictureUrl(IDocument page)
        {
            var cookTimeElement = page.QuerySelector(".main-photo img ");
            var pictureUrl = $"https:{cookTimeElement.GetAttribute("src")}";
            return pictureUrl == "https://static.1000.menu/style/images/cooking-completion.jpg" ? pictureUrl : "";
        }
    }
}