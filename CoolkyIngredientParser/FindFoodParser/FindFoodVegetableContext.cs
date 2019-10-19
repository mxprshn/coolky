using AngleSharp.Dom;
using System;
using System.Collections.Generic;
using System.Text;

namespace CoolkyIngredientParser.FindFoodParser
{
    class FindFoodVegetableContext : FindFoodContext
    {
        public override string UrlTypeName() => "ovoshi";
        public override string GetType(IParsingLogic logic, IDocument page) => "овощи";
    }
}
