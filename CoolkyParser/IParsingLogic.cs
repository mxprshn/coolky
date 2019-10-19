using System.Collections.Generic;
using AngleSharp.Dom;

namespace CoolkyRecipeParser
{
    public interface IParsingLogic
    {
        string GetId(IDocument page);
        string GetDishName(IDocument page);
        string GetCookTime(IDocument page);
        string GetType(IDocument page);
        string GetCuisine(IDocument page);
        string GetPortionAmount(IDocument page);
        List<string> GetSteps(IDocument page);
        List<string> GetIngredients(IDocument page);
    }
}
