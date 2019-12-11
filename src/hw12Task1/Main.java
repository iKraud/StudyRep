package hw12Task1;

import hw09Task1.MyClassLoader;

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
//-XX:MetaspaceSize=1m -XX:MaxMetaspaceSize=10m -XX:MinMetaspaceFreeRatio=50 -XX:MaxMetaspaceFreeRatio=80
public class Main {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        List<MyClass> arr = new ArrayList<>();
//        List<String> arr = new ArrayList<>();
//        String string = "";
        int total = 10;
        Random rnd = new Random();

//        File sourceFile = new File("C:\\Java\\IdeaProjects\\untitled\\src\\hw12Task1\\MyClass.java"); //компиляция на ходу
//        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler(); //компиляция на ходу - можно было использовать javac
//        compiler.run(null, null, null, sourceFile.getPath()); //компиляция на ходу

        for (int i = 0; i < 10; i++) {
            MyThread myThread= new MyThread();
            myThread.start();
        }

//        for (int i = 0; i < total; i++) {
//            total++;
////---------------------------------------
//            //#0
////            MyClass cls = new MyClass();
//            //#1
//            MyClass cls = (MyClass) ClassLoader.getSystemClassLoader().loadClass("hw12Task1.MyClass").newInstance();
//            //#2
////            MyClass cls = (MyClass) Class.forName("hw12Task1.MyClass").newInstance();
//            //#3
////            ClassLoader classLoader = new MyClassLoader();
////            Class<?> cls = Class.forName("hw12Task1.MyClass", true, classLoader);
////---------------------------------------
////            arr.add(string + i);
//            arr.add(cls);
//
//            if ((arr.size() % (rnd.nextInt(total) + 1)) == 0) {
//                arr.remove(i);
//                i--;
//            }
//
//        }
    }
}