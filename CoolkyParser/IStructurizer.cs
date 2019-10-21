using System;
using System.Collections.Generic;
using System.Text;

namespace CoolkyRecipeParser
{
    public interface IStructurizer
    {
        Recipe Structurize(UnstructuredRecipe source);
    }
}
