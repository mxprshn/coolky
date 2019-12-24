using CoolkyRecipeParser.HrumkaParser;
using System;
using System.Diagnostics;
using System.Threading.Tasks;

namespace CoolkyRecipeParser
{
    class Program
    {
        static async Task Main(string[] args)
        {
            var hrumkaParser = new RecipeParser(new WebSiteFactory());
            var timer = Stopwatch.StartNew();
            await hrumkaParser.ParseAsync();
            timer.Stop();
            Console.WriteLine($"{timer.Elapsed.ToString()}");
        }
    }
}