package hw06Task1;

import java.io.*;
/**
 * @author "Timohin Igor"
 *
 * Задание 1. Написать программу, читающую текстовый файл.
 * Программа должна составлять отсортированный по алфавиту список слов, найденных в файле и сохранять его в файл-результат.
 * Найденные слова не должны повторяться, регистр не должен учитываться. Одно слово в разных падежах – это разные слова.
 */

public class Main{
    public static void main(String []args) throws IOException{
        // //нужно работать с потоком, не с nio
        // nio //пример из лекции
        // strList = Files.readAllFiles(Paths.get(note.txt)); //пример из лекции
        // strList.forEach(System.out::println); //пример из лекции
        
        byte[] arr = "Hello МИР!".getBytes();
        InputStream byteArrayInputStream = new ByteArrayInputStream (arr);
        int b;
        while ((b = byteArrayInputStream.read()) != -1) {
            System.out.print((char)b);
        }
    }
}
