package hw07Task1;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * Дан массив случайных чисел. Написать программу для вычисления факториалов всех элементов массива. Использовать пул потоков для решения задачи.
 *
 * Особенности выполнения:
 * Для данного примера использовать рекурсию - не очень хороший вариант, т.к. происходит большое выделение памяти, очень вероятен StackOverFlow.
 * Лучше перемножать числа в простом цикле при этом создавать объект типа BigInteger
 *
 * По сути, есть несколько способа решения задания:
 * 1) распараллеливать вычисление факториала для одного числа
 * 2) распараллеливать вычисления для разных чисел
 * 3) комбинированный
 *
 * Причём вычислив факториал для одного числа, можно запомнить эти данные и использовать их для вычисления другого, что будет гораздо быстрее
 */

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
/**
 * создание изначального массива случайных чисел
 */
        long startTimer = System.currentTimeMillis();
        List<Integer> mainList = new ArrayList<>();
        Random rnd = new Random();
        int amountOfNumbers = 1000;
        for (int i = 0; i < amountOfNumbers; i++) {
            mainList.add(rnd.nextInt(100)+1); // от 1 до 10 включительно
        }
/**
 * создание потоков, количеством, равным размеру массива случайных чисел
 */
// варинат №1
        ExecutorService service = Executors.newFixedThreadPool(3);
        List<MyFactorial> resultList = new ArrayList<>();
        for (int i = 0; i < amountOfNumbers; i++) {
            MyFactorial mf = new MyFactorial(mainList.get(i));
            CompletableFuture<MyFactorial> cf = CompletableFuture.completedFuture(mf);
            resultList.add(cf.get());
        }
        for (MyFactorial el : resultList) {
            System.out.println(el.call());
        }

// варинат №2
//        for (int i = 0; i < amountOfNumbers; i++) {
//            MyFactorial mf = new MyFactorial(mainList.get(i));
//            FutureTask futureTask = new FutureTask(mf);
//            Thread thread = new Thread(futureTask);
//            thread.start();
//            System.out.println(futureTask.get());
//        }
        System.out.println("----------");
        System.out.println("Отработало за " + (System.currentTimeMillis() - startTimer) + " миллисекунд");
        service.shutdown();
    }
}