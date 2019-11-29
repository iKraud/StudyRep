package hw09Task1;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

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
    public static void main(String[] args) throws IOException {
        String s = "";
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        while (!s.endsWith("\n\n")) {
            s += in.readLine() + "\n";
        }
        in.close();
        s = s.substring(0, s.length()-("\n\n").length());

//        FileOutputStream fos = new FileOutputStream("C:\\Java\\IdeaProjects\\untitled\\src\\hw09Task1\SomeClass.java");
        FileOutputStream fos = new FileOutputStream("R:\\ССиОР\\СРТиС\\03 - Разработка\\Тимохин И.В\\055_IdeaProjects\\src\\hw09Task1\\SomeClass.java");
        fos.write(s.getBytes());
        fos.close();
        System.out.println("Файл класса успешно создан!");
    }
}

package hw09Task1;

public interface Worker {
    void doWork();
}
