using AngleSharp.Dom;
using System;
using System.Collections.Generic;
using System.Text;
using System.Text.RegularExpressions;
using System.Threading;

namespace CoolkyRecipeParser.HrumkaParser
{
    public class HrumkaTypeContext : ParsingContext
    {
        private const string baseUrl = "https://1000.menu";

        private string urlTypeName;
        private string type;

        public HrumkaTypeContext(string urlTypeName, string type)
        {
            this.urlTypeName = urlTypeName;
            this.type = type;
        }

        private int PageCountParser(string pageString)
        {
            var regex = new Regex("\\d+");
            var match = regex.Match(pageString);
            return int.Parse(match.Value);
        }

        public override async IAsyncEnumerable<IDocument> GetPages()
        {
            var basePage = await HtmlLoader.LoadAsync($"{baseUrl}/catalog/{urlTypeName}");
            var pageCount = PageCountParser(basePage.QuerySelector(".search-pages [href]:last-child").Text());

            // отдельно первую страницу?
            for (var i = 1; i <= pageCount; ++i)
            {
                var currentPage = await HtmlLoader.LoadAsync($"{baseUrl}/catalog/{urlTypeName}/{i}");
                var recipeElements = currentPage.QuerySelectorAll(".h5");
                Console.WriteLine($"{recipeElements.Length} recipes found at page {i}.");

                var counter = 1;

                foreach (var recipeElement in recipeElements)
                {
                    Console.WriteLine($"Parsing recipe {counter} in {Thread.CurrentThread.ManagedThreadId} thread.");
                    ++counter;
                    var recipeLink = recipeElement.GetAttribute("href");
                    IDocument page = null;

                    try
                    {
                        page = await HtmlLoader.LoadAsync(baseUrl + recipeLink);
                    }
                    catch (Exception exc)
                    {
                        Console.WriteLine($"Error occured during downloading {recipeLink}.");
                        continue;
                    }

                    yield return page;
                }
            }
        }

        public override string GetType(IParsingLogic logic, IDocument page) => type;
        public override string GetWebSite() => "1000.menu";
    }
}
