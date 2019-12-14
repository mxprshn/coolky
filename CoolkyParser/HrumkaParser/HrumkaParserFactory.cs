using System.Collections.Generic;

namespace CoolkyRecipeParser.HrumkaParser
{
    class HrumkaParserFactory : IParserFactory
    {
        private const int maxPageAmount = 3;

        public List<ParsingContext> GetContexts() => new List<ParsingContext>
        {
            new HrumkaTypeContext("vtoroe-bludo", "второе", maxPageAmount),
            new HrumkaTypeContext("supj", "первое", maxPageAmount),
            new HrumkaTypeContext("salaty", "салат", maxPageAmount),
            new HrumkaTypeContext("zakuski", "закуска", maxPageAmount),
            new HrumkaTypeContext("vjpechka", "выпечка", maxPageAmount),
            new HrumkaTypeContext("desert", "десерт", maxPageAmount),
            new HrumkaTypeContext("napitki", "напиток", maxPageAmount),
            new HrumkaTypeContext("sousj", "соус", maxPageAmount),
            new HrumkaTypeContext("domashnie-zagotovki", "консервирование", maxPageAmount),
            new HrumkaCuisineContext("frantsuzskaya-kuxnya", "французская", maxPageAmount),
            new HrumkaCuisineContext("russkaya-kuxnya", "русская", maxPageAmount),
            new HrumkaCuisineContext("italyanskaya-kuxnya", "итальянская", maxPageAmount),
            new HrumkaCuisineContext("yaponskaya-kuxnya", "японская", maxPageAmount),
            new HrumkaCuisineContext("koreiskaya-kuxnya", "корейская", maxPageAmount)
        };

        public IParsingLogic GetLogic() => new HrumkaParsingLogic();
    }
}