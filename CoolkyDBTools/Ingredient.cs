using Realms;
using System;
using System.Collections.Generic;
using System.Text;
using System.Collections;

namespace CoolkyTools
{
    public class Ingredient
    {
        public string Type { get; }
        public IList<string> Names { get; }

        public Ingredient() { }

        public Ingredient(string type, IList<string> names)
        {
            Type = type;
            Names = names;
        }

        public void Print()
        {
            Console.WriteLine(Type);

            foreach (var name in Names)
            {
                Console.WriteLine(name);
            }

            Console.WriteLine();
        }
    }
}