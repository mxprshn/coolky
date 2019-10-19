using AngleSharp.Dom;

namespace CoolkyRecipeParser.HrumkaParser
{
    class HrumkaSaladContext : HrumkaContext
    {
        public override string GetType(IParsingLogic logic, IDocument page) => "салат";
        public override string UrlTypeName() => "salaty";
    }
}
