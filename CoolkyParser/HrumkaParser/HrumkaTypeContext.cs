using AngleSharp.Dom;
using System;
using System.Collections.Generic;
using System.Text;
using System.Text.RegularExpressions;
using System.Threading;
using System.Threading.Tasks;
using System.Collections.Concurrent;
using System.Linq;

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

        public override async Task<IEnumerable<string>> GetPages()
        {
            var basePage = await HtmlLoader.LoadAsync($"{baseUrl}/catalog/{urlTypeName}");
            var pageCount = PageCountParser(basePage.QuerySelector(".search-pages [href]:last-child").Text());
            var stringBag = new ConcurrentBag<string>();
            var counter = 1;

            await Enumerable.Range(1, pageCount).ForEachAsync<int>(async (i) =>
            {
                var currentPage = await HtmlLoader.LoadAsync($"{baseUrl}/catalog/{urlTypeName}/{i}");
                var recipeElements = currentPage.QuerySelectorAll(".h5");
                Console.WriteLine($"{recipeElements.Length} recipes found at page {i}.");                

                Parallel.ForEach(recipeElements, (recipeElement) =>
                {
                    Console.WriteLine($"Found {counter} of type {type} in {Thread.CurrentThread.ManagedThreadId} thread.");
                    Interlocked.Increment(ref counter);
                    stringBag.Add($"{baseUrl}{recipeElement.GetAttribute("href")}");
                });
            });

            return stringBag.ToList();
        }

        public override string GetType(IParsingLogic logic, IDocument page) => type;
        public override string GetWebSite() => "1000.menu";
    }
}
