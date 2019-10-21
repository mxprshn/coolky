using Realms;
using System;
using System.Collections.Generic;
using System.Text;
using System.Collections;
using LingvoNET;
using System.Text.RegularExpressions;

namespace CoolkyIngredientParser
{
    public class Ingredient : RealmObject
    {
        public string Type { get; set; }
        public string Name { get; set; }
        public IList<string> Synonyms { get; }

        public Ingredient() { }

        public Ingredient(string type, string name)
        {
            Type = type;
            Name = name;
            Synonyms = GetForms(name);
        }

        public void Print()
        {
            Console.WriteLine($"{Type} {Name}");
        }

        public static IList<string> GetForms(string source)
        {
            var result = new List<string>();
            var noun = Nouns.FindSimilar(source);

            if (noun != null)
            {
                if (noun[Case.Genitive, Number.Singular] != null)
                {
                    result.Add(noun[Case.Genitive, Number.Singular]);
                }

                if (noun[Case.Genitive, Number.Plural] != null)
                {
                    result.Add(noun[Case.Genitive, Number.Plural]);
                }

                if (noun[Case.Nominative, Number.Plural] != null)
                {
                    result.Add(noun[Case.Nominative, Number.Plural]);
                }
            }

            return result;
        }
    }
}