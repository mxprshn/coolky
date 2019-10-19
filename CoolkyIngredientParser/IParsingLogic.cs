using System.Collections.Generic;
using AngleSharp.Dom;

namespace CoolkyIngredientParser
{
    public interface IParsingLogic
    {
        string GetType(IDocument page);
        List<string> GetNames(IDocument page);
    }
}
