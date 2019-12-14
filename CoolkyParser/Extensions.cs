using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading;
using System.Threading.Tasks;

namespace CoolkyRecipeParser
{
    public static class Extensions
    {
        public static Task ForEachAsync<TSource>(this IEnumerable<TSource> source,
                Func<TSource, Task> taskSelector)
        {
            var oneAtATime = new SemaphoreSlim(21, 21);
            return Task.WhenAll(from item in source select ProcessAsync(item, taskSelector, oneAtATime));
        }

        private static async Task ProcessAsync<TSource>(TSource item,
                Func<TSource, Task> taskSelector, SemaphoreSlim oneAtATime)
        {
            await oneAtATime.WaitAsync();
            try
            {
                await taskSelector(item);
            }
            finally
            {
                oneAtATime.Release();
            }         
        }
    }
}