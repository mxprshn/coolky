using System.Collections.Generic;

namespace CoolkyIngredientParser.FindFoodParser
{
    class FindFoodParserFactory : IParserFactory
    {
        public List<ParsingContext> GetContexts() => new List<ParsingContext>
        {
            new FindFoodContext("ovoshi", "овощи"),
            new FindFoodContext("fructi", "фрукты"),
            new FindFoodContext("gribi", "грибы"),
            new FindFoodContext("molochnie", "яйца и молочные продукты"),
            new FindFoodContext("myasnie", "мясо"),
            new FindFoodContext("riba", "рыба и морепродукты"),
            new FindFoodContext("orehi-suhofructi", "орехи и сухофрукты"),
            new FindFoodContext("muka", "мука и мучные изделия"),
            new FindFoodContext("krupa-i-kacha", "крупы"),
            new FindFoodContext("konditerskie-izdeliya", "кондитерские изделия"),
            new FindFoodContext("zelen", "зелень"),
            new FindFoodContext("specii", "специи"),
            new FindFoodContext("dobavki", "добавки"),
            new FindFoodContext("bezalkogolnyie-napitki", "безалкогольные напитки"),
            new FindFoodContext("alkogolnyie-napitki", "алкогольные напитки")
        };

        public IParsingLogic GetLogic() => new FindFoodParsingLogic();
    }
}
