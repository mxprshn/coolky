using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;

namespace CoolkyRecipeParser
{
    public static class Extensions
    {
        public static Task ForEachAsync<TSource>(this IEnumerable<TSource> source,
                Func<TSource, Task> taskSelector)
        {
            var oneAtATime = new SemaphoreSlim(10, 10);
            return Task.WhenAll(
                from item in source
                select ProcessAsync(item, taskSelector, oneAtATime));
        }

        private static async Task ProcessAsync<TSource>(TSource item,
            Func<TSource, Task> taskSelector, SemaphoreSlim oneAtATime)
        {
            await oneAtATime.WaitAsync();
            await taskSelector(item);
            oneAtATime.Release();
        }

        public static Task ForEachAsync<TSource, TResult>(this IEnumerable<TSource> source,
            Func<TSource, Task<TResult>> taskSelector, Action<TSource, TResult> resultProcessor)
        {
            var oneAtATime = new SemaphoreSlim(15, 15);
            return Task.WhenAll(
                from item in source
                select ProcessAsync(item, taskSelector, resultProcessor, oneAtATime));
        }

        private static async Task ProcessAsync<TSource, TResult>(
            TSource item,
            Func<TSource, Task<TResult>> taskSelector, Action<TSource, TResult> resultProcessor,
            SemaphoreSlim oneAtATime)
        {
            TResult result = await taskSelector(item);
            await oneAtATime.WaitAsync();
            try
            {
                resultProcessor(item, result);
            }
            finally
            {
                oneAtATime.Release();
            }
        }
    }
}
