using System.Collections.Generic;
using System.Threading.Tasks;
using AngleSharp.Dom;

namespace CoolkyRecipeParser
{
    public abstract class ParsingContext
    {
        public abstract Task<IEnumerable<string>> GetPages();

        public virtual string GetId(IParsingLogic logic, IDocument page) => logic.GetId(page);
        public virtual string GetDishName(IParsingLogic logic, IDocument page) => logic.GetDishName(page);
        public virtual int GetCookTime(IParsingLogic logic, IDocument page) => logic.GetCookTime(page);
        public virtual string GetType(IParsingLogic logic, IDocument page) => logic.GetType(page);
        public virtual string GetCuisine(IParsingLogic logic, IDocument page) => logic.GetCuisine(page);
        public virtual int GetPortionAmount(IParsingLogic logic, IDocument page) => logic.GetPortionAmount(page);
        public virtual string GetPictureUrl(IParsingLogic logic, IDocument page) => logic.GetPictureUrl(page);
        public virtual IList<(string name, string amount)> GetIngredients(IParsingLogic logic, IDocument page) => logic.GetIngredients(page);
        public virtual IList<string> GetSteps(IParsingLogic logic, IDocument page) => logic.GetSteps(page);

        public abstract string GetWebSite();
    }
}
