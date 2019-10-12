using CoolkyParser.HrumkaParser;
using System.Threading.Tasks;

namespace CoolkyParser
{
    class Program
    {
        static async Task Main(string[] args)
        {
            var hrumkaParser = new RecipeParser(new HrumkaParserFactory());
            var recipes = await hrumkaParser.ParseAsync();
        }
    }
}
