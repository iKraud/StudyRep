package hw02Task3;

import java.util.Arrays;
import java.util.Collections;

/**
 * Сортировка сравнением
 */
class ComparableSort implements Sort {
    public void sort(Person[] p) {
        Collections.sort(Arrays.asList(p));
//        Arrays.sort(p);
    }
}
