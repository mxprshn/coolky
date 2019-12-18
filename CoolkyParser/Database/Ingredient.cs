using Realms;

namespace CoolkyRecipeParser
{
    public class Ingredient : RealmObject
    {
        [PrimaryKey]
        [MapTo("name")]
        public string Name { get; set; }
    }
}