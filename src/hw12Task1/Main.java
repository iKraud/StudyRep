package hw12Task1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Timohin Igor
 * Задание 1. Необходимо создать программу, которая продемонстрирует утечку памяти в Java.
 * При этом объекты должны не только создаваться, но и периодически частично удаляться, чтобы GC имел возможность очищать часть памяти.
 * Через некоторое время программа должна завершиться с ошибкой OutOfMemoryError c пометкой Java Heap Space.
 *
 * Задание 2. Доработать программу так, чтобы ошибка OutOfMemoryError возникала в Metaspace /Permanent Generation
 */
//-XX:MetaspaceSize=1m -XX:MaxMetaspaceSize=10m -XX:MinMetaspaceFreeRatio=50 -XX:MaxMetaspaceFreeRatio=80
public class Main {
    public static void main(String[] args) {
        List<String> arrString = new ArrayList<>();
        int total = 10;
        String string = "";
        int j = 0;
        Random rnd = new Random();
        for (int i = 0; i < total; i++) {
            total++;
            string = new String(string + i);
            arrString.add(string);
//            System.out.println(++j + " " + total + " " + string);

            if ((arrString.size() % (rnd.nextInt(total) + 1)) == 0) {
                arrString.remove(i);
                i--;
            }
        }
    }
}