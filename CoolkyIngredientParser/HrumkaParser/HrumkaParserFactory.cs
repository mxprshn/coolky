using System.Collections.Generic;

namespace CoolkyIngredientParser.HrumkaParser
{
    class HrumkaParserFactory : IParserFactory
    {
        public List<ParsingContext> GetContexts() => new List<ParsingContext>
        {
            new HrumkaContext("ovoshchi", "овощи"),
            new HrumkaContext("bobovje", "овощи"),
            new HrumkaContext("fruktj-yagodj", "фрукты"),
            new HrumkaContext("jagodi", "фрукты"),
            new HrumkaContext("gribj", "грибы"),
            new HrumkaContext("yaitsa", "яйца и молочные продукты"),
            new HrumkaContext("molochnje-produktj", "яйца и молочные продукты"),
            new HrumkaContext("myaso-i-myasoproduktj", "мясо"),
            new HrumkaContext("rjba-i-moreproduktj", "рыба и морепродукты"),
            new HrumkaContext("moreproduktj", "рыба и морепродукты"),
            new HrumkaContext("iz-suxofruktov", "орехи и сухофрукты"),
            new HrumkaContext("orexi", "орехи и сухофрукты"),
            new HrumkaContext("xlebobulochnje-izdeliya", "мука и мучные изделия"),
            new HrumkaContext("makaronnje-izdeliya", "мука и мучные изделия"),
            new HrumkaContext("krupa-i-krupyanje-izdeliya", "крупы"),
            new HrumkaContext("sladkoe", "кондитерские изделия"),
            new HrumkaContext("listya-tsvetki-korenya", "зелень"),
            new HrumkaContext("alkogol", "алкогольные напитки")
        };

        public IParsingLogic GetLogic() => new HrumkaParsingLogic();
    }
}
