using Realms;
using System;
using System.Collections.Generic;
using System.Text;
using System.Collections;
using System.Text.RegularExpressions;
using System.Linq;

namespace CoolkyRecipeParser
{
    public class Ingredient : RealmObject
    {
        [PrimaryKey]
        public string Name { get; set; }
    }
}