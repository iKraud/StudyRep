package homeWork02;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

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
    final static int count = 10000;
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
        bubbleStartTime = System.currentTimeMillis();
        Sort bubbleSort = new BubbleSort();
        bubbleSort.sort(person);
        bubbleTimeSpent = System.currentTimeMillis() - bubbleStartTime;
/**
 * Вывод тёсок-погодок
 */
        for (int i=0; i<count; i++) {
            System.out.println(person[i]);
            try {
                Person.Compare(person,i);
            }
            catch (SameNameSameAgeException e) {
                System.out.println(e.getMessage());
            }
        }
/**
 * Сортировка сравнением
 */
        compStartTime = System.currentTimeMillis();
        Sort comparableSort = new ComparableSort();
        comparableSort.sort(person);
        compTimeSpent = System.currentTimeMillis() - compStartTime;
/**
 * Вывод тёсок-погодок
 */
        for (int i=0; i<count; i++) {
            System.out.println(person[i]);
            try {
                Person.Compare(person,i);
            }
            catch (SameNameSameAgeException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("Сортировка пузырьком " + bubbleTimeSpent + " миллисекунд");
        System.out.println("Сортировка сравнением " + compTimeSpent + " миллисекунд");
    }
}
class Person implements Comparable {
    private int age;
    private String name;
    private Sex sex;

    public void setAge (int age) {
        if (age >= 0 && age <=100) {
            this.age = age;
        }
    }

    public int getAge () {
        return age;
    }

    public void setName (int name) {
        this.name =
        String.valueOf(Character.forDigit (name+10,36)) +
        String.valueOf(Character.forDigit (name+11,36)) +
        String.valueOf(Character.forDigit (name+12,36)) +
        String.valueOf(Character.forDigit (name+13,36)) +
        String.valueOf(Character.forDigit (name+14,36)) +
        String.valueOf(Character.forDigit (name+15,36));
    }

    public String getName () {
        return name;
    }

    public void setSex (int sex) {
        if (sex == 0) {
            this.sex = Sex.MAN;
        } else {
            this.sex = Sex.WOMAN;
        }
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

                    throw new SameNameSameAgeException ("У людей с ID "+i+" и "+element+" возраст и имя совпали");
               }
            }
        }
    }

    @Override
    public String toString () {
        return ("age="+age +", name="+name +", sex="+sex);
    }

    @Override
    public int compareTo(Object o) {
        Person p = (Person)o;

        return Comparator.comparing(Person::getSex)
                .thenComparing(Person::getAge)
                .thenComparing(Person::getName)
                .compare(this, p);
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

interface Sort {
   void sort (Person[] p);
}

/**
 * Сортировка пузырьком
 */
class BubbleSort implements Sort {
    public void sort(Person[] p) {

/**
 * Сортировка по имени
 */
        for (int i=Task3.count-1; i>=1; i--) {
            for (int j=0; j<i; j++) {
                String currStrWithoutSpace = p[j].getName().trim();
                String nextStrWithoutSpace = p[j+1].getName().trim();
                int minLength = Integer.min(currStrWithoutSpace.length(), nextStrWithoutSpace.length());
                for (int n=0; n<minLength; n++) {
                    if ((int)p[j].getName().charAt(n)-(int)'a'+1 > (int)p[j+1].getName().charAt(n)-(int)'a'+1) {
                        swap(p, j,j+1);
                        break;
                    }
                }
            }
        }
/**
 * Сортировка по возрасту
 */
        for (int i=Task3.count-1; i>=1; i--) {
            for (int j=0; j<i; j++) {
                if (p[j].getAge() > p[j+1].getAge()) {
                    swap(p, j,j+1);
                }
            }
        }
/**
 * Сортировка по полу
 */
        for (int i=Task3.count-1; i>=1; i--) {
            for (int j=0; j<i; j++) {
                if (p[j].getSex().ordinal() > p[j+1].getSex().ordinal()) {
                    swap(p, j,j+1);
                }
            }
        }

    }
    private void swap(Person[] p, int jCur, int jNext) {
        Person temp = p[jCur];
        p[jCur]=p[jNext];
        p[jNext]=temp;
    }
}

/**
 * Сортировка сравнением
 */
class ComparableSort implements Sort {
    public void sort(Person[] p) {
        Collections.sort(Arrays.asList(p));
//        Arrays.sort(p);
    }
}