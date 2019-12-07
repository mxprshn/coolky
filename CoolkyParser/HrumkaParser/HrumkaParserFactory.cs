using System.Collections.Generic;

namespace CoolkyRecipeParser.HrumkaParser
{
    class HrumkaParserFactory : IParserFactory
    {
        public List<ParsingContext> GetContexts() => new List<ParsingContext>
        {
            new HrumkaTypeContext("vtoroe-bludo", "второе")
        };

        public IParsingLogic GetLogic() => new HrumkaParsingLogic();
    }
}