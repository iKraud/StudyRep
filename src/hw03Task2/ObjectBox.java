package hw03Task2;

import java.util.TreeSet;

/**
 * @author "Timohin Igor"
 *
 * Задание 2. Создать класс ObjectBox, который будет хранить коллекцию Object.
 * У класса должен быть метод addObject, добавляющий объект в коллекцию.
 * У класса должен быть метод deleteObject, проверяющий наличие объекта в коллекции и при наличии удаляющий его.
 * Должен быть метод dump, выводящий содержимое коллекции в строку.
 */
public class ObjectBox {
    TreeSet<Object> coll = new TreeSet<>();

    public void addObject (Object o) {
        coll.add(o);
    }

    public void deleteObject (Object o) {
        if (coll.contains(o)) {
            coll.remove(o);
        }
    }

    public void dump () {
        for (Object o : coll) {
            System.out.println(o.toString());
        }
    }
}
