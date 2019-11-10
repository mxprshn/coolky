using System.Collections.Generic;

namespace CoolkyRecipeParser.HrumkaParser
{
    class HrumkaParserFactory : IParserFactory
    {
        public List<ParsingContext> GetContexts() => new List<ParsingContext>
        {
            new HrumkaTypeContext("zakuski", "закуска")
        };

        public IParsingLogic GetLogic() => new HrumkaParsingLogic();
    }
}