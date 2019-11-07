package homeWork02;

/**
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
public class Task3 {
    public static void main(String[] args) {

    }
}
class Person {
    private int age;
    private String name;
    private Sex sex;

    public int setAge (int age) {
        if (age >= 0 && age <=100) {
            this.age = age;
        }
    }
    
    public int setSex (int sex) {
        if (sex == 0) {
            this.sex = Sex.MAN;
        } else {
            this.sex = Sex.WOMAN;
        }
    }
}

enum Sex {
    MAN, WOMAN;
}

// interface Sort {
//     int[] sort();
// }

// class BubbleSort implements Sort {
//     public int[] sort() {
//         for (int i=0; i<10000; i++) {
//             for (int j=0; j<10000; j++) {
//                 if
//             }
//         }
//         swap()
//     }
// }

// class ComparableSort implements Sort {
//     public int[] sort() {
// 		...
//     }
// }
