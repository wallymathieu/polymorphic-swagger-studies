package se.gewalli.polymorphic_swagger;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class Collections {
    public static <T> Collection<Collection<T>> batchesOf(Collection<T> collection, int count) {
        ArrayList<Collection<T>> batches = new ArrayList<Collection<T>>(count);
        Iterator<T> iterator = collection.iterator();
        while (true) {
            ArrayList<T> list = new ArrayList<T>(count);
            for (int i = 0; i < count && iterator.hasNext(); i++) {
                T t = iterator.next();
                list.add(t);
            }
            if (list.isEmpty()) {
                break;
            }
            batches.add(list);
        }
        return batches;
    }
}
