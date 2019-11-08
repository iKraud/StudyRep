package homeWork02;

import java.util.Random;
import java.util.Arrays;

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
        Random rnd = new Random();
        final int count = 1000;
        Person[] person = new Person[count];
        for (int i=0; i<count; i++) {
            person[i] = new Person(rnd.nextInt(100), rnd.nextInt(26), rnd.nextInt(2));
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
class Person {
    private int age;
    private String name;
    private Sex sex;

    public void setAge (int age) {
        if (age >= 0 && age <=100) {
            this.age = age;
        }
    }

    public void setName (int name) {
        this.name = 
        String.valueOf(Character.forDigit (name+10,36))+
        String.valueOf(Character.forDigit (name+11,36)) +
        String.valueOf(Character.forDigit (name+12,36)) +
        String.valueOf(Character.forDigit (name+8,36)) +
        String.valueOf(Character.forDigit (name+9,36));
    }

    public void setSex (int sex) {
        if (sex == 0) {
            this.sex = Sex.MAN;
        } else {
            this.sex = Sex.WOMAN;
        }
    }

    public int getAge () {
        return age;
    }

    public String getName () {
        return name;
    }

    public Sex getSex () {
        return sex;
    }

    public Person (int age, int name, int sex) {
        setAge(age);
        setName(name);
        setSex(sex);
    }

    public static void Compare (Person[] p, int element) throws SameNameSameAgeException {
        for (int i = 0; i <=element; i++) {
            if (i != element) {
                if ((p[i].getAge()==p[element].getAge()) &
                    (p[i].getName().equals(p[element].getName()))) {

                    throw new SameNameSameAgeException ("У людей "+i+" и "+element+" возраст и имя совпали");
               }
            }
        }
    }

    @Override
    public String toString () {
        return ("age="+age +", name="+name +", sex="+sex);
    }
}

enum Sex {
    MAN, WOMAN;
}

class SameNameSameAgeException extends Exception {
    public SameNameSameAgeException (String message) {
        super (message);
    }
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
