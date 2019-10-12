using System.Collections.Generic;

namespace CoolkyParser
{
    public interface IParserFactory
    {
        List<ParsingContext> GetContexts();
        IParsingLogic GetLogic();
    }
}
