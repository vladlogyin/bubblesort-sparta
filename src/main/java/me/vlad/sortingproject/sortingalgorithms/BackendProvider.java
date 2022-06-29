package me.vlad.sortingproject.sortingalgorithms;

import org.reflections.Reflections;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class BackendProvider {
    public static Map<String, Class<? extends Sorter>> listBackends() {
        Reflections reflect = new Reflections("me.vlad.sortingproject.sortingalgorithms");
        Set<Class<? extends Sorter>> subTypes = reflect.getSubTypesOf(Sorter.class);
        Map<String, Class<? extends Sorter>> retMap = new HashMap(subTypes.size());
        for (Class<? extends Sorter> c : subTypes) {
            retMap.put(c.getSimpleName(), c);
        }
        return retMap;

    }
}
