using AngleSharp.Dom;
using CoolkyTools;
using System;
using System.Collections.Generic;
using System.Text;

namespace CoolkyIngredientParser.FindFoodParser
{
    public abstract class FindFoodContext : ParsingContext
    {
        private const string baseUrl = "http://findfood.ru";

        public override async IAsyncEnumerable<IDocument> GetPages()
        {
            var basePage = await HtmlLoader.LoadAsync($"{baseUrl}/category/{UrlTypeName()}");
            var pageCount = int.Parse(basePage.QuerySelector(".yiiPager li:nth-last-child(3)").Text());

            // отдельно первую страницу?
            for (var i = 1; i <= pageCount; ++i)
            {
                yield return await HtmlLoader.LoadAsync($"{baseUrl}/category/view?url={UrlTypeName()}&Product_page={i}");
            }
        }

        public abstract string UrlTypeName();
    }
}