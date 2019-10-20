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
            Synonyms = GetSynonyms(name);
        }

        public void Print()
        {
            Console.WriteLine($"{Type} {Name} {Synonyms}");
        }

        // асинхронно?
        public static IList<string> GetSynonyms(string source)
        {
            if (source.Contains(" ") && !source.Contains("("))
            {
                return null;
            }

            var result = new List<string>();

            if (source.Contains("("))
            {
                var regex = new Regex("\\((.*?)\\)");
                var synonym = regex.Match(source).Value.Replace("(", "").Replace(")", "");
                result.Add(synonym);
                if (GetForms(synonym) != null)
                {
                    result.AddRange(GetForms(synonym));
                }
                return result;
            }

            return GetForms(source);
        }

        public static IList<string> GetForms(string source)
        {
            var result = new List<string>();
            var noun = Nouns.FindSimilar(source);

            if (noun != null)
            {
                result.Add(noun[Case.Genitive, Number.Singular]);
                result.Add(noun[Case.Genitive, Number.Plural]);
                result.Add(noun[Case.Nominative, Number.Plural]);
                return result;
            }

            return null;
        }
    }
}