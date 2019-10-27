using AngleSharp.Dom;
using System;
using System.Collections.Generic;
using System.Text;

namespace CoolkyIngredientParser.HrumkaParser
{
    public class HrumkaContext : ParsingContext
    {
        private string urlTypeName;
        private string type;

        private const string baseUrl = "https://1000.menu";

        public HrumkaContext(string urlTypeName, string type)
        {
            this.urlTypeName = urlTypeName;
            this.type = type;
        }

        public override async IAsyncEnumerable<IDocument> GetPages()
        {
            yield return await HtmlLoader.LoadAsync($"{baseUrl}/kalorijnost-produkta/{urlTypeName}");
        }

        public override string GetType(IParsingLogic logic, IDocument page) => type;
    }
}