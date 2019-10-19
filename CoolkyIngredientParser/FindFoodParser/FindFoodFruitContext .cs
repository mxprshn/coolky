using AngleSharp.Dom;
using System;
using System.Collections.Generic;
using System.Text;

namespace CoolkyIngredientParser.FindFoodParser
{
    class FindFoodFruitContext : FindFoodContext
    {
        public override string UrlTypeName() => "fructi";
        public override string GetType(IParsingLogic logic, IDocument page) => "фрукты";
    }
}
