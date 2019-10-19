using AngleSharp.Dom;
using System;
using System.Collections.Generic;
using System.Text;

namespace CoolkyRecipeParser.HrumkaParser
{
    class HrumkaAppetizerContext : HrumkaContext
    {
        public override string GetType(IParsingLogic logic, IDocument page) => "закуска";
        public override string UrlTypeName() => "zakuski";
    }
}
