using System.Collections.Generic;

namespace CoolkyRecipeParser.HrumkaParser
{
    class HrumkaParserFactory : IParserFactory
    {
        public List<ParsingContext> GetContexts() => new List<ParsingContext>
        {
            new HrumkaTypeContext("desert", "десерт")
        };

        public IParsingLogic GetLogic() => new HrumkaParsingLogic();
        public IStructurizer GetStructurizer() => new HrumkaStructurizer();
    }
}