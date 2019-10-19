using Realms;
using System;
using System.Collections.Generic;
using System.Text;

namespace CoolkyTools
{
    public class Ingredient : RealmObject
    {
        public IList<string> Names { get; }
    }
}