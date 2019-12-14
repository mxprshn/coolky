using AngleSharp.Dom;
using System;
using System.Collections.Generic;
using System.Text;
using System.Text.RegularExpressions;
using System.Threading;
using System.Threading.Tasks;
using System.Collections.Concurrent;
using System.Linq;

namespace CoolkyRecipeParser.HrumkaParser
{
    public class HrumkaTypeContext : HrumkaContext
    {
        public override string SectionName => urlTypeName;

        private string type;
        private string urlTypeName;

        public HrumkaTypeContext(string urlTypeName, string type)
        {
            this.urlTypeName = urlTypeName;
            this.type = type;
        }

        public HrumkaTypeContext(string urlTypeName, string type, int maxPageAmount) :
                this(urlTypeName, type) => MaxPageAmount = maxPageAmount;


        public override string GetType(IParsingLogic logic, IDocument page) => type;
    }
}
