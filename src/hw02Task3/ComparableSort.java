package hw02Task3;

import java.util.*;

/**
 * Сортировка сравнением
 */
class ComparableSort implements Sort {
    public void sort(Person[] p) {
//        List testList = new ArrayList(Arrays.asList(p));
//
//        testList.sort(Comparator.comparing(Arrays.asList(p)::age));

        Collections.sort(Arrays.asList(p));
//        Arrays.sort(p);
    }
}