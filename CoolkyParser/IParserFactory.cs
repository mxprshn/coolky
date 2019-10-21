using System.Collections.Generic;

namespace CoolkyRecipeParser
{
    public interface IParserFactory
    {
        List<ParsingContext> GetContexts();
        IParsingLogic GetLogic();
        IStructurizer GetStructurizer();
    }
}
