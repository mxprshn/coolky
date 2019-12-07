using System.Collections.Generic;

namespace CoolkyRecipeParser.HrumkaParser
{
    class HrumkaParserFactory : IParserFactory
    {
        public List<ParsingContext> GetContexts() => new List<ParsingContext>
        {
            new HrumkaCuisineContext("gruzinskaya-kuxnya", "грузинская")
        };

        public IParsingLogic GetLogic() => new HrumkaParsingLogic();
    }
}