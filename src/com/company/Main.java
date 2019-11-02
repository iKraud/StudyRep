package com.company;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String hw = "Hello, World!";
        Scanner sc = new Scanner(System.in);
        System.out.println("1 для NullPointerException");
        System.out.println("2 для ArrayIndexOutOfBoundsException");
        System.out.println("3 для ArithmeticException");

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
                    throw new ArithmeticException("ArithmeticException перехвачено!");
                default:
            }
        }
        catch (NullPointerException e) {
            System.out.println("NullPointerException перевачено!");
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("ArrayIndexOutOfBoundsException перевачено!");
        }
        finally {
            System.out.println(hw);
        }
    }
}