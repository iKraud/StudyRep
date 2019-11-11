package hw02Task1;

import java.util.Scanner;

/**
 * @author Timohin Igor
 *  Задание 1.
 * Написать программу ”Hello, World!”. В ходе выполнения программы она должна выбросить исключение и завершиться с ошибкой.
 *
 * Смоделировав ошибку «NullPointerException»
 * Смоделировав ошибку «ArrayIndexOutOfBoundsException»
 * Вызвав свой вариант ошибки через оператор throw
 */
public class Task1 {
    public static void main(String[] args) throws SomeException {
        String hw = "Hello, World!";
        Scanner sc = new Scanner(System.in);
        System.out.println("1 для NullPointerException");
        System.out.println("2 для ArrayIndexOutOfBoundsException");
        System.out.println("3 для SomeException");

        try {
            int script = sc.nextInt();
            switch (script) {
                case 1:
                    String nullString = null;
                    nullString.length();
                    break;
                case 2:
                    int [] arr = new int [5];
                    System.out.println(arr[6]);
                    break;
                case 3:
                    throw new SomeException("SomeException перехвачено!");
                default:
            }
        }
        catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
            throw e;
        }
        finally {
            System.out.println(hw);
        }
    }
}

class SomeException extends Exception {
    public SomeException (String message) {
        super (message);
    }
}