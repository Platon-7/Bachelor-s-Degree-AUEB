package me.projects.Bayes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class Sort {
        public Set<Map.Entry<String, Integer>> sort(HashMap<String, Integer> a) {
        Set<Map.Entry<String, Integer>> entries = a.entrySet();

        Comparator<Map.Entry<String, Integer>> valueComparator = (Map.Entry<String, Integer> e1, Map.Entry<String, Integer> e2) -> {
            int v1 = e1.getValue();
            int v2 = e2.getValue();
            return v2 - v1;
        };
        List<Map.Entry<String, Integer>> listOfEntries = new ArrayList<>(entries);
        Collections.sort(listOfEntries, valueComparator);
        LinkedHashMap<String, Integer> sortedByValue = new LinkedHashMap<>(listOfEntries.size());
        listOfEntries.forEach((entry) -> {
            sortedByValue.put(entry.getKey(), entry.getValue());
        });
        Set<Map.Entry<String, Integer>> entrySetSortedByValue = sortedByValue.entrySet();

        entrySetSortedByValue.forEach((mapping) -> {
        });
        return entrySetSortedByValue;
    }
}
