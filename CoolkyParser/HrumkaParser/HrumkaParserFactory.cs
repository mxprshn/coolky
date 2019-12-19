using System.Collections.Generic;

namespace CoolkyRecipeParser.HrumkaParser
{
    class HrumkaParserFactory : IParserFactory
    {
        private const int maxPageAmount = 3;

        public List<ParsingContext> GetContexts() => new List<ParsingContext>
        {
            //new HrumkaTypeContext("vtoroe-bludo", "второе"),
            //new HrumkaTypeContext("supj", "первое"),
            //new HrumkaTypeContext("salaty", "салат"),
            //new HrumkaTypeContext("zakuski", "закуска"),
            //new HrumkaTypeContext("vjpechka", "выпечка"),
            //new HrumkaTypeContext("desert", "десерт"),
            //new HrumkaTypeContext("napitki", "напиток"),
            //new HrumkaTypeContext("sousj", "соус"),
            //new HrumkaTypeContext("domashnie-zagotovki", "консервирование"),
            new HrumkaCuisineContext("frantsuzskaya-kuxnya", "французская"),
            new HrumkaCuisineContext("russkaya-kuxnya", "русская"),
            new HrumkaCuisineContext("italyanskaya-kuxnya", "итальянская"),
            new HrumkaCuisineContext("yaponskaya-kuxnya", "японская"),
            new HrumkaCuisineContext("koreiskaya-kuxnya", "корейская")
        };

        public IParsingLogic GetLogic() => new HrumkaParsingLogic();
    }
}