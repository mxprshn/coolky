using System;
using System.Collections.Generic;
using System.Threading;
using AngleSharp.Dom;
using CoolkyTools;

namespace CoolkyRecipeParser.HrumkaParser
{
    class HrumkaDessertContext : ParsingContext
    {
        private const string baseUrl = "https://1000.menu";

        // может вынести это в общий метод
        public override async IAsyncEnumerable<IDocument> GetPages()
        {
            var basePage = await HtmlLoader.LoadAsync(baseUrl + "/catalog/desert");
            var pageCount = int.Parse(basePage.QuerySelector(".search-pages [href]:last-child").Text());
            
            // отдельно первую страницу?
            for (var i = 1; i <= pageCount; ++i)
            {
                var currentPage = await HtmlLoader.LoadAsync(baseUrl + $"/catalog/desert/{i}");
                var recipeElements = currentPage.QuerySelectorAll(".h5");
                Console.WriteLine($"{recipeElements.Length} recipes found at page {i}.");

                var counter = 1;

                foreach (var recipeElement in recipeElements)
                {
                    Console.WriteLine($"Parsing recipe {counter} in {Thread.CurrentThread.ManagedThreadId} thread.");
                    ++counter;
                    var recipeLink = recipeElement.GetAttribute("href");
                    yield return await HtmlLoader.LoadAsync(baseUrl + recipeLink);
                }
            }
        }

        public override string GetType(IParsingLogic logic, IDocument page) => "десерт";
    }
}