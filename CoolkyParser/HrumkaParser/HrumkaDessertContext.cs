using System;
using System.Collections.Generic;
using System.Threading;
using AngleSharp.Dom;
using CoolkyTools;

namespace CoolkyRecipeParser.HrumkaParser
{
    class HrumkaDessertContext : HrumkaContext
    {
        public override string GetType(IParsingLogic logic, IDocument page) => "десерт";
        public override string UrlTypeName() => "desert";
    }
}