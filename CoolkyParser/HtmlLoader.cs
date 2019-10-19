﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Net.Http;
using AngleSharp.Dom;
using AngleSharp;

namespace CoolkyRecipeParser
{
    static class HtmlLoader
    {
        public static async Task<IDocument> LoadAsync(string url)
        {
            string htmlSourceCode;

            using (HttpClient client = new HttpClient())
            {
                // обработка ошибок?
                htmlSourceCode = await client.GetStringAsync(url);
            }

            var config = Configuration.Default;
            var context = BrowsingContext.New(config);
            return await context.OpenAsync(req => req.Content(htmlSourceCode));
        }
    }
}
