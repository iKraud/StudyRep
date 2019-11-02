package com.company;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String hw = "Hello, World!";
        Scanner sc = new Scanner(System.in);
        System.out.println("1 для NullPointerException");
        System.out.println("2 для ArrayIndexOutOfBoundsException");
        System.out.println("3 для сюрприза");

        try {
            int script = sc.nextInt();
            switch (script) {
                case 1:
                    throw new NullPointerException("demo");
                    break;
                case 2:
                    throw new ArrayIndexOutOfBoundsException("demo");
                    break;
                case 3:
                    break;
                default:

            }
        }
        catch (NullPointerException e) {
            System.out.println("Пойман NPE!");
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Пойман AIOOBE!");
        }
        finally {
            System.out.println(hw);
        }
    }
}