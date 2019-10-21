using System.Collections.Generic;
using AngleSharp.Dom;

namespace CoolkyRecipeParser
{
    public abstract class ParsingContext
    {
        // реализовать через yield return?
        public abstract IAsyncEnumerable<IDocument> GetPages();

        public virtual string GetId(IParsingLogic logic, IDocument page) => logic.GetId(page);
        public virtual string GetDishName(IParsingLogic logic, IDocument page) => logic.GetDishName(page);
        public virtual string GetCookTime(IParsingLogic logic, IDocument page) => logic.GetCookTime(page);
        public virtual string GetType(IParsingLogic logic, IDocument page) => logic.GetType(page);
        public virtual string GetCuisine(IParsingLogic logic, IDocument page) => logic.GetCuisine(page);
        public virtual string GetPortionAmount(IParsingLogic logic, IDocument page) => logic.GetPortionAmount(page);
        public virtual string GetPictureUrl(IParsingLogic logic, IDocument page) => logic.GetPictureUrl(page);
        public virtual IList<string> GetIngredients(IParsingLogic logic, IDocument page) => logic.GetIngredients(page);
        public virtual IList<string> GetSteps(IParsingLogic logic, IDocument page) => logic.GetSteps(page);

        public abstract string GetWebSite();
    }
}
