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
        public string Id { get; set; } = Guid.NewGuid().ToString();

        public string Name { get; set; }

        public Ingredient() { }
    }
}