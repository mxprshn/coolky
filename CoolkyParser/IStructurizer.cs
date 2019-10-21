using System;
using System.Collections.Generic;
using System.Text;

namespace CoolkyRecipeParser
{
    public interface IStructurizer
    {
        int StructurizeCookTime(string source);
        int StructurizePortionAmount(string source);
        IList<string> StructurizeIngredientText(IList<string> source);
        IList<string> StructurizeSteps(IList<string> source);
    }
}
