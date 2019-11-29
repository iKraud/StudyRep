package hw08Task1;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * Задание 1. Необходимо разработать класс, реализующий следующие методы:
 * void serialize (Object object, String file);
 * Object deSerialize(String file);
 *
 * Методы выполняют сериализацию объекта Object в файл file и десериализацию объекта из этого файла.
 * Обязательна сериализация и десериализация "плоских" объектов (все поля объекта - примитивы, или String).
 *
 * Задание 2. Предусмотреть работу c любыми типами полей (полями могут быть ссылочные типы).
 *
 * Требование: Использовать готовые реализации (Jaxb, jackson и т.д.) запрещается.
 */
public class Main {
    public static void main(String[] args) throws IllegalAccessException, IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException {
//        String path = "R:\\ССиОР\\СРТиС\\03 - Разработка\\Тимохин И.В\\055_IdeaProjects\\src\\hw08Task1\\Text.txt";
        String path = "C:\\Java\\IdeaProjects\\untitled\\src\\hw08Task1\\Text.txt";
        Department department = new Department("NYPD",1);
        Worker worker1 = new Worker("Adam", 17, Access.ACTIVE, department);
        Serializer serializer = new Serializer();
        serializer.serialize(worker1, path);
        Worker worker2 = (Worker) serializer.deSerialize(path);
        System.out.println(worker2);
    }
}