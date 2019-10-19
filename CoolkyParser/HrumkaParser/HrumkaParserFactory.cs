using System.Collections.Generic;

namespace CoolkyRecipeParser.HrumkaParser
{
    class HrumkaParserFactory : IParserFactory
    {
        public List<ParsingContext> GetContexts() => new List<ParsingContext> { new HrumkaDessertContext() };
        public IParsingLogic GetLogic() => new HrumkaParsingLogic();
    }
}
