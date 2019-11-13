package hw03Task1;

/**
 * @author "Timohin Igor"
 *
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

public class Main {
    public static void main(String[] args) {
        Number[] number = {1,2,3,3,4,5};
        for (Number el : number) {
            System.out.print(el + " ");
        }
        System.out.println();

        MathBox mathBox = new MathBox(number);
        System.out.println(mathBox.summator());
        mathBox.splitter(2);
        System.out.println(mathBox);
        mathBox.remover(1);
        System.out.println(mathBox);
    }
}
