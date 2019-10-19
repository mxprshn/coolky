using AngleSharp.Dom;
using CoolkyTools;
using System;
using System.Collections.Generic;
using System.Text;

namespace CoolkyIngredientParser.FindFoodParser
{
    public class FindFoodContext : ParsingContext
    {
        private string urlTypeName;
        private string type;

        private const string baseUrl = "http://findfood.ru";

        public FindFoodContext(string urlTypeName, string type)
        {
            this.urlTypeName = urlTypeName;
            this.type = type;
        }

        public override async IAsyncEnumerable<IDocument> GetPages()
        {
            var basePage = await HtmlLoader.LoadAsync($"{baseUrl}/category/{urlTypeName}");
            var pageCount = int.Parse(basePage.QuerySelector(".yiiPager li:nth-last-child(3)").Text());

            // отдельно первую страницу?
            for (var i = 1; i <= pageCount; ++i)
            {
                yield return await HtmlLoader.LoadAsync($"{baseUrl}/category/view?url={urlTypeName}&Product_page={i}");
            }
        }

        public override string GetType(IParsingLogic logic, IDocument page) => type;
    }
}