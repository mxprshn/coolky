using System.Collections.Generic;

namespace CoolkyRecipeParser.HrumkaParser
{
    class HrumkaParserFactory : IParserFactory
    {
        public List<ParsingContext> GetContexts() => new List<ParsingContext>
        {
            new HrumkaCuisineContext("koreiskaya-kuxnya", "корейская"),
            new HrumkaCuisineContext("kitaiskaya-kuxnya", "китайская")
        };

        public IParsingLogic GetLogic() => new HrumkaParsingLogic();
    }
}