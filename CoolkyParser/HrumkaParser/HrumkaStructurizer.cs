using System;
using System.Collections.Generic;
using System.Text;
using System.Text.RegularExpressions;

namespace CoolkyRecipeParser.HrumkaParser
{
    class HrumkaStructurizer : IStructurizer
    {
        private int ConvertTime(string source)
        {
            var regex = new Regex("\\d+");
            var matches = regex.Matches(source);
            var result = 0;

            for (var i = matches.Count - 1; i >= 0; --i)
            {
                var time = int.Parse(matches[i].Value);

                switch (i)
                {
                    case 0:
                        {
                            result += time;
                            break;
                        }
                    case 1:
                        {
                            result += 60 * time;
                            break;
                        }
                    case 2:
                        {
                            result += 1440 * time;
                            break;
                        }
                }
            }

            return result;
        }

        public Recipe Structurize(UnstructuredRecipe source)
        {
            return new Recipe(source.Id, source.DishName, ConvertTime(source.CookTime), source.Cuisine,
                    source.Type, int.Parse(source.PortionAmount), source.PictureUrl, source.Ingredients, null, source.Steps, source.WebSite);
        }
    }
}
