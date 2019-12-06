package hw03Task1;

import hw03Task2.ObjectBox;

import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author "Timohin Igor"
 * Задание 1. Написать класс MathBox, реализующий следующий функционал:
 *
 * Конструктор на вход получает массив Number. Элементы не могут повторяться. Элементы массива внутри объекта раскладываются в подходящую коллекцию (выбрать самостоятельно).
 * Существует метод summator, возвращающий сумму всех элементов коллекции.
 * Существует метод splitter, выполняющий поочередное деление всех хранящихся в объекте элементов на делитель, являющийся аргументом метода.
 * Хранящиеся в объекте данные полностью заменяются результатами деления.
 * Необходимо правильно переопределить методы toString, hashCode, equals, чтобы можно было использовать MathBox
 * для вывода данных на экран и хранение объектов этого класса в коллекциях (например, hashMap). Выполнение контракта обязательно!
 * Создать метод, который получает на вход Integer и если такое значение есть в коллекции, удаляет его.
 */

public class MathBox <T extends Number> extends ObjectBox {
/**
 * Создаём коллекцию
 */
    Set<Number> coll = new TreeSet<>();
// если <T super Number> (или extends) - то "Unexpected bound"
// если просто <T> - то в  методе splitter ругается, когда в эту же коллекцию вместо первоначального int кладу double
    public MathBox (T[] number) {
        int nl = number.length;
        for (int i=0; i<nl; i++) {
            coll.add(number[i]);
        }
    }

    public MathBox (Object o) throws ObjectInMathBox {
        throw new ObjectInMathBox ("Класс " + MathBox.class + " может принимать только массивы в качестве аргументов");
    }
/**
 * Метод суммирующий элементы коллекции
 */
    public Number summator () {
        double sum = 0;
        for (Number el : coll) {
            sum += el.doubleValue();
        }
        return sum;
    }
/**
 * Метод перезаписывающий имеющующся коллекцию элементами, поделёнными на определённый делитель
 */
    public void splitter (double d) {
        Set<Number> temp = new TreeSet<>();
// если <T super Number> (или extends) - "Unexpected bound"
// если <T> - то ругается при делении, мол деление не может быть применено к int, T
        for (Number el : coll) {
            temp.add((el.intValue() / d));
        }
        coll.clear();
        coll = temp;
    }
/**
 * Метод удаляющий найденный в коллекции Integer
 */ 
    public void remover (Integer d) {
        if (coll.contains(d.doubleValue())) {
            coll.remove(d.doubleValue());
        }
    }

    @Override
    public String toString () {
        String totalString = "";
        for (Number el : coll) {
            totalString += el+", ";
        }
        return totalString.substring(0,totalString.length()-2);
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (!(o instanceof MathBox)) return false;
        MathBox mathBox = (MathBox) o;
        return Objects.equals(coll, mathBox.coll);
    }

    @Override
    public int hashCode () {
        return Objects.hash(coll);
    }
}