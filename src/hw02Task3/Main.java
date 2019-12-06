package hw02Task3;

import java.util.Random;

/**
 * @author "Timohin Igor"
 * Задание 3. Дан массив объектов Person. Класс Person характеризуется полями age (возраст, целое число 0-100), sex (пол – объект класса Sex со строковыми константами внутри MAN, WOMAN), name (имя - строка).
 * Создать два класса, методы которых будут реализовывать сортировку объектов. Предусмотреть единый интерфейс для классов сортировки. Реализовать два различных метода сортировки этого массива по правилам:
 * первые идут мужчины
 * выше в списке тот, кто более старший
 * имена сортируются по алфавиту
 *
 * Программа должна вывести на экран отсортированный список и время работы каждого алгоритма сортировки.
 * Предусмотреть генерацию исходного массива (10000 элементов и более).
 * Если имена людей и возраст совпадают, выбрасывать в программе пользовательское исключение.
 */
public class Main {
    final static int count = 5000;
    public static void main(String[] args) {
        long bubbleStartTime, bubbleTimeSpent, compStartTime, compTimeSpent;
        Random rnd = new Random();
        Person[] person = new Person[count];
/**
 * Наполнение массива объектами, рандомно
 */
        for (int i=0; i<count; i++) {
            person[i] = new Person(rnd.nextInt(100), rnd.nextInt(22), rnd.nextInt(2));
        }
/**
 * Сортировка пузырьком
 */
//        bubbleStartTime = System.currentTimeMillis();
//        Sort bubbleSort = new BubbleSort();
//        bubbleSort.sort(person);
//        bubbleTimeSpent = System.currentTimeMillis() - bubbleStartTime;
//
//        sameNameSameAge(person);
/**
 * Сортировка сравнением
 */
        compStartTime = System.currentTimeMillis();
        Sort comparableSort = new ComparableSort();
        comparableSort.sort(person);
        compTimeSpent = System.currentTimeMillis() - compStartTime;

        sameNameSameAge(person);

//        System.out.println("Сортировка пузырьком " + bubbleTimeSpent + " миллисекунд");
        System.out.println("Сортировка сравнением " + compTimeSpent + " миллисекунд");
    }
/**
 * Вывод тёсок-погодок
 */
    public static void sameNameSameAge (Person[] person) {
        for (int i=0; i<count; i++) {
            System.out.println(person[i]);
            try {
                Person.Compare(person,i);
            }
            catch (SameNameSameAgeException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}