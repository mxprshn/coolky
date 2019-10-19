using System.Collections.Generic;

namespace CoolkyIngredientParser.FindFoodParser
{
    class FindFoodParserFactory : IParserFactory
    {
        public List<ParsingContext> GetContexts() => new List<ParsingContext> { new FindFoodFruitContext() };
        public IParsingLogic GetLogic() => new FindFoodParsingLogic();
    }
}
