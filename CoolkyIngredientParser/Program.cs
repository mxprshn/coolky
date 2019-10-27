using CoolkyIngredientParser.FindFoodParser;
using CoolkyIngredientParser.HrumkaParser;
using System;
using System.Threading;
using System.Threading.Tasks;

namespace CoolkyIngredientParser
{
    class Program
    {
        static async Task Main(string[] args)
        {
            var findFoodParser = new IngredientParser(new FindFoodParserFactory());
            await findFoodParser.ParseAsync();
        }
    }
}
