package hw12Task1;

import java.lang.reflect.InvocationTargetException;
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
public class Main {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, ClassNotFoundException, NoSuchFieldException {
//        List<Object[]> arr = new ArrayList<>();
        List<Class> arr = new ArrayList<>();
        int total = 10;
        Random rnd = new Random();
        for (int i = 0; i < total; i++ ) {
            total++;

            ClassLoader classLoader = new MyClassLoader();
            Class<?> cls = Class.forName("hw12Task1.MyClass", true, classLoader);
            arr.add(cls);
            Object instance = cls.getDeclaredConstructor().newInstance();
            cls.getMethod("print").invoke(instance);
//            arr.add(new Object[3]);
//            if ((arr.size() % (rnd.nextInt(total) + 1)) == 0) {
//                arr.remove(i);
//                i--;
//            }
        }
    }
}
