package hw05Task1;

/**
 * Разработать программу – картотеку домашних животных. У каждого животного есть уникальный идентификационный номер, кличка, хозяин (объект класс Person с полями – имя, возраст, пол), вес.
 *
 * Реализовать:
 *
 * метод добавления животного в общий список (учесть, что добавление дубликатов должно приводить к исключительной ситуации)
 * поиск животного по его кличке (поиск должен быть эффективным)
 * изменение данных животного по его идентификатору
 * вывод на экран списка животных в отсортированном порядке. Поля для сортировки –  хозяин, кличка животного, вес.
 */

public class Main {
    public static void main(String []args) {
        System.out.println();
    }
}
class Animal {
    TreeSet<Animal> animal= new TreeSet<>();
    private int id;
    private String nickname;
    private Person owner;
    private double weight;
    
    public Animal (String nickname, Person owner, double weight) {
        if (animal.contains(nickname)) {
               throw new SuchDogIsAlreadyExist ("Собака с такой кличкой уже есть в базе");
        } else {
            setNickname(nickname);
            this.owner = owner;
            this.weight = weight;    
        }
    public int getId () {
        return id;
    }
    public void setNickname (String nickname) {
        this.nickname = nickname;
    }
    public String getNickname () {
        return nickname;
    }
    public void setWeight (double weight) {
        this.weight = weight;
    }
        
    }
}
class Person {
    private String name;
    private int age;
    private Sex sex;
    
    public Person (String name, int age, int sex) {
        setName(name);
        setAge(age);
        setSex(sex);
    }
    public void setName (String name) {
        this.name = name;
    }
    public String getName () {
        return name;
    }
    public void setAge (int age) {
        this.age = age;
    }
    public int getAge () {
        return age;
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
}
enum Sex {
    MAN, WOMAN;
}
public SuchDogIsAlreadyExist extends Exception {
    public SuchDogIsAlreadyExist (String mes) {
        super(mes)
    }
}
