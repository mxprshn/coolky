using System;
using System.Collections.Concurrent;
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

        public static Task ParallelForEachAsync<T>(this IEnumerable<T> source, Func<T, Task> funcBody, int maxDoP = 4)
        {
            async Task AwaitPartition(IEnumerator<T> partition)
            {
                using (partition)
                {
                    while (partition.MoveNext())
                    { await funcBody(partition.Current); }
                }
            }

            return Task.WhenAll(
                Partitioner
                    .Create(source)
                    .GetPartitions(maxDoP)
                    .AsParallel()
                    .Select(p => AwaitPartition(p)));
        }
    }
}