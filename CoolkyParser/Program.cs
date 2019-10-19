using CoolkyRecipeParser.HrumkaParser;
using System.Threading.Tasks;

namespace CoolkyRecipeParser
{
    class Program
    {
        static async Task Main(string[] args)
        {
            var hrumkaParser = new RecipeParser(new HrumkaParserFactory());
            await hrumkaParser.ParseAsync();
        }
    }
}
