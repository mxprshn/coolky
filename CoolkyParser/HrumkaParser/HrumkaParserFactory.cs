using System.Collections.Generic;

namespace CoolkyParser.HrumkaParser
{
    class HrumkaParserFactory : IParserFactory
    {
        public List<ParsingContext> GetContexts() => new List<ParsingContext> { new HrumkaDessertContext() };
        public IParsingLogic GetLogic() => new HrumkaParsingLogic();
    }
}
