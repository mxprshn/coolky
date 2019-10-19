using System.Collections.Generic;
using AngleSharp.Dom;

namespace CoolkyIngredientParser
{
    public interface IParsingLogic
    {
        string GetType(IDocument page);
        IList<string> GetNames(IDocument page);
    }
}
