package hw03Task1;

import hw03Task2.ObjectBox;

/**
 * @author "Timohin Igor"
 * Задание 3. Доработать классы MathBox и ObjectBox таким образом, чтобы MathBox был наследником ObjectBox.
 * Необходимо сделать такую связь, правильно распределить поля и методы.
 * Функциональность в целом должна сохраниться.
 * При попытке положить Object в MathBox должно создаваться исключение.
 */

public class Main {
    public static void main(String[] args) throws ObjectInMathBox {
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

        ObjectBox objectBox = new ObjectBox();
        objectBox.addObject(mathBox);
        objectBox.dump();
        objectBox.deleteObject(mathBox);
        MathBox mathBox1 = new MathBox(objectBox);
    }
}