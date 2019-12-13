package hw12Task1;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.File;
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
//-XX:MetaspaceSize=1m -XX:MaxMetaspaceSize=10m
public class Main {
    public static void main(String[] args) {
        option(2); // 1 - java.lang.OutOfMemoryError: Java heap space, всё остальное - java.lang.OutOfMemoryError: Metaspace
    }
    static void option(int x) {
        if (x == 1) {
            List<String> arr = new ArrayList<>();
            String string = "";
            int total = 10;
            Random rnd = new Random();
            for (int i = 0; i < total; i++) {
                total++;
                arr.add(string + i);
                if ((arr.size() % (rnd.nextInt(total) + 1)) == 0) {
                    arr.remove(i);
                    i--;
                }
            }
        } else {
            File sourceFile = new File("C:\\Java\\IdeaProjects\\untitled\\src\\hw12Task1\\MyClass.java"); //компиляция на ходу
//                File sourceFile = new File("R:\\ССиОР\\СРТиС\\03 - Разработка\\Тимохин И.В\\055_IdeaProjects\\src\\hw12Task1\\MyClass.java"); //компиляция на ходу
            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler(); //компиляция на ходу - можно было использовать javac
            compiler.run(null, null, null, sourceFile.getPath()); //компиляция на ходу
        }
    }
}