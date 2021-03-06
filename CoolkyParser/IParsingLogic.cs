﻿using System.Collections.Generic;
using AngleSharp.Dom;

namespace CoolkyRecipeParser
{
    public interface IParsingLogic
    {
        string GetId(IDocument page);
        string GetDishName(IDocument page);
        int GetCookTime(IDocument page);
        string GetType(IDocument page);
        string GetCuisine(IDocument page);
        int GetPortionAmount(IDocument page);
        string GetPictureUrl(IDocument page);
        IList<string> GetSteps(IDocument page);
        IList<(string name, string amount)> GetIngredients(IDocument page);
    }
}