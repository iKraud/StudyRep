package hw09Task1;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.*;
import java.lang.reflect.InvocationTargetException;

/**
 * Дан интерфейс
 *
 * public interface Worker {
 *     void doWork();
 * }
 *
 * Необходимо написать программу, выполняющую следующее:
 *
 * Программа с консоли построчно считывает код метода doWork. Код не должен требовать импорта дополнительных классов.
 * После ввода пустой строки считывание прекращается и считанные строки добавляются в тело метода public void doWork() в файле SomeClass.java.
 * Файл SomeClass.java компилируется программой (в рантайме) в файл SomeClass.class.
 * Полученный файл подгружается в программу с помощью кастомного загрузчика
 * Метод, введенный с консоли, исполняется в рантайме (вызывается у экземпляра объекта подгруженного класса)
 */
public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        String s = "";
        Helpers rt = new Helpers();
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        while (!s.endsWith("\n\n")) {
            s += in.readLine() + "\n";
        }
        in.close();
        s = s.substring(0, s.length() - ("\n\n").length());

        rt.setStartTime();
        FileOutputStream fos = new FileOutputStream("C:\\Java\\IdeaProjects\\untitled\\src\\hw09Task1\\SomeClass.java");
//        FileOutputStream fos = new FileOutputStream("R:\\ССиОР\\СРТиС\\03 - Разработка\\Тимохин И.В\\055_IdeaProjects\\src\\hw09Task1\\SomeClass.java");
        fos.write(s.getBytes());
        fos.close();
        System.out.println("Класс создан за " + rt.getRunTime() + " миллисекунд");

        File sourceFile = new File("C:\\Java\\IdeaProjects\\untitled\\src\\hw09Task1\\SomeClass.java"); //компиляция на ходу
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler(); //компиляция на ходу
        compiler.run(null, null, null, sourceFile.getPath()); //компиляция на ходу

        ClassLoader classLoader = new MyClassLoader();

        Class<?> cls = Class.forName("hw09Task1.SomeClass", true, classLoader); //вопрос для понимания: зачем тут дженерик?
        Object instance = cls.getDeclaredConstructor().newInstance();
        instance.getClass().getDeclaredMethod("doWork").invoke(instance); //оставил несколько вариантов вызова для себя
        cls.getMethod("doWork").invoke(cls.getDeclaredConstructor().newInstance()); //оставил несколько вариантов вызова для себя
    }
}