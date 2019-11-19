package hw06Task1;

import java.io.*;
import java.util.*;

/**
 * @author "Timohin Igor"
 *
 * Задание 1. Написать программу, читающую текстовый файл.
 * Программа должна составлять отсортированный по алфавиту список слов, найденных в файле и сохранять его в файл-результат.
 * Найденные слова не должны повторяться, регистр не должен учитываться. Одно слово в разных падежах – это разные слова.
 */

public class Main{
    public static void main(String []args) throws IOException {
// Words.txt в две строки:
// One two три Три Четыре пЯтЬ
// Шесть сЕмь eight nInE teN
        BufferedReader fis = new BufferedReader(new InputStreamReader(new FileInputStream("Words.txt"),  "UTF8"));
        String s = "";
        while (fis.ready()) {
            s += fis.readLine() + " ";
        }
        fis.close();
        System.out.println("Исходный набор:");
        System.out.println(s);
        s = s.trim().toLowerCase();

        String[] temp;
        temp = s.split(" ");
        Set<String> setOfWords = new TreeSet<>();
        int sl = temp.length;
        for (int i = 0; i < sl; i++) {
            setOfWords.add(temp[i]);
        }
        s="";
        for (String el : setOfWords) {
            s += el + " ";
        }
        s = s.trim();
        FileOutputStream fos = new FileOutputStream("WordsFinal.txt");
        fos.write(s.getBytes());
        fos.close();
        System.out.println("Выходной набор успешно записан в файл:");
        System.out.println(s);
    }
}