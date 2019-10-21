using System;
using System.Collections.Generic;
using System.Text;
using System.Text.RegularExpressions;
using CoolkyIngredientParser;

namespace CoolkyRecipeParser.HrumkaParser
{
    class HrumkaStructurizer : IStructurizer
    {
        public int StructurizeCookTime(string source)
        {
            var match = Regex.Match(source, "((?<days>\\d+) д)? *((?<hours>\\d+) ч)? *((?<minutes>\\d+) мин)?");

            var days = 0;
            var hours = 0;
            var minutes = 0;

            int.TryParse(match.Groups["days"].Value, out days);
            int.TryParse(match.Groups["hours"].Value, out hours);
            int.TryParse(match.Groups["minutes"].Value, out minutes);

            return 1440 * days + 60 * hours + minutes;
        }

        public IList<string> StructurizeIngredientText(IList<string> source) => source;

        public int StructurizePortionAmount(string source) => int.Parse(source);

        public IList<string> StructurizeSteps(IList<string> source) => source;
    }
}
