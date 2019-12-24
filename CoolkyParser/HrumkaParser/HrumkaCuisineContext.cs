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
    public class HrumkaCuisineContext : WebSiteContext
    {
        public override string SectionName => urlCuisineName;

        private string cuisine;
        private string urlCuisineName;

        public HrumkaCuisineContext(string urlCuisineName, string cuisine)
        {
            this.urlCuisineName = urlCuisineName;
            this.cuisine = cuisine;
        }

        public HrumkaCuisineContext(string urlCuisineName, string cuisine, int maxPageAmount) :
                this(urlCuisineName, cuisine) => MaxPageAmount = maxPageAmount;

        public override string GetCuisine(IParsingLogic logic, IDocument page) => cuisine;
    }
}
