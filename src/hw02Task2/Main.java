package hw02Task2;

import java.util.Random;
import java.util.Scanner;

/**
 * @author Timohin Igor
 * Задание 2.
 * Составить программу, генерирующую N случайных чисел. Для каждого числа k вычислить квадратный корень q.
 * Если квадрат целой части q числа равен k, то вывести это число на экран.
 * Предусмотреть что первоначальные числа могут быть отрицательные, в этом случае генерировать исключение.
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rnd = new Random();
        System.out.print("Сколько чисел: ");
        int n = sc.nextInt();
        System.out.println();

        for (int i=1;i<=n;i++) {
            int k=rnd.nextInt(30)*3-50;
            if (k<0) {
                try {
                    throw new IllegalArgumentException();
                }
                catch (IllegalArgumentException e) {
                    k=-k;
                }
            }
            double q = Math.sqrt(k);
            if (Math.pow((int)q,2)==k){
                System.out.println(k);
            }
        }
    }
}