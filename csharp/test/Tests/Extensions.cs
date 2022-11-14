using System;
using System.Collections.Generic;

namespace SomeBasicFileStoreApp.Tests;

public static class Extensions
{
    [Obsolete("Use wallymathieu.collections 1.2 once released")]
    public static IEnumerable<(T,T)> Pairwise<T>(
        this IEnumerable<T> collection)
    {
        using var enumerator = collection.GetEnumerator();
        if (!enumerator.MoveNext())
        {
            yield break;
        }

        var last = enumerator.Current;
        for (; enumerator.MoveNext();)
        {
            yield return (last, enumerator.Current);
            last = enumerator.Current;
        }
    }
}