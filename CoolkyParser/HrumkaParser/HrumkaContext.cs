using AngleSharp.Dom;
using System;
using System.Collections.Concurrent;
using System.Collections.Generic;
using System.Linq;
using System.Text.RegularExpressions;
using System.Threading.Tasks;

namespace CoolkyRecipeParser.HrumkaParser
{
    public abstract class HrumkaContext : ParsingContext
    {
        public abstract string SectionName { get; }
        public int MaxPageAmount { get; protected set; } = int.MaxValue;

        private const string baseUrl = "https://1000.menu";

        private int PageCountParser(string pageString)
        {
            var regex = new Regex("\\d+");
            var match = regex.Match(pageString);
            return int.Parse(match.Value);
        }

        public override async Task<IEnumerable<string>> GetPages()
        {
            var pageCount = 1;
            var basePage = await HtmlLoader.LoadAsync($"{baseUrl}/catalog/{SectionName}");
            var pager = basePage.QuerySelector(".search-pages [href]:last-child");

            if (pager != null)
            {
                pageCount = PageCountParser(pager.Text());
            }
            
            var stringBag = new ConcurrentBag<string>();
            //var counter = 1;

            await Enumerable.Range(1, Math.Min(pageCount, MaxPageAmount)).ParallelForEachAsync((i) => ProcessPage(i, stringBag),
                Environment.ProcessorCount);

            return stringBag.ToList();
        }

        public override string GetWebSite() => "1000.menu";

        private async Task ProcessPage(int pageNumber, ConcurrentBag<string> stringBag)
        {
            var currentPage = await HtmlLoader.LoadAsync($"{baseUrl}/catalog/{SectionName}/{pageNumber}");
            var recipeElements = currentPage.QuerySelectorAll(".h5");
            Console.WriteLine($"{recipeElements.Length} recipes found at page {pageNumber}.");

            foreach (var recipeElement in recipeElements)
            {
                //Interlocked.Increment(ref counter);
                var url = $"{baseUrl}{recipeElement.GetAttribute("href")}";
                stringBag.Add(url);
                //Console.WriteLine($"Recipe {Volatile.Read(ref counter)}: {url} added to parsing queue.");
            }
        }
    }
}
