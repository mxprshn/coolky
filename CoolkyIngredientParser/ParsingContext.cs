using System.Collections.Generic;
using AngleSharp.Dom;

namespace CoolkyIngredientParser
{
    public abstract class ParsingContext
    {
        public abstract IAsyncEnumerable<IDocument> GetPages();

        public virtual string GetType(IParsingLogic logic, IDocument page) => logic.GetType(page);
        public virtual IList<string> GetNames(IParsingLogic logic, IDocument page) => logic.GetNames(page);
    }
}
