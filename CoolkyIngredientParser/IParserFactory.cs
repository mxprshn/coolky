using System.Collections.Generic;

namespace CoolkyIngredientParser
{
    public interface IParserFactory
    {
        List<ParsingContext> GetContexts();
        IParsingLogic GetLogic();
    }
}
