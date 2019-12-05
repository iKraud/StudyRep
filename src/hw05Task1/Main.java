package hw05Task1;

/**
 * @author "Timohin Igor"
 *
 * Разработать программу – картотеку домашних животных. У каждого животного есть уникальный идентификационный номер,
 * кличка, хозяин (объект класс Person с полями – имя, возраст, пол), вес.
 *
 * Реализовать:
 * метод добавления животного в общий список (учесть, что добавление дубликатов должно приводить к исключительной ситуации)
 * поиск животного по его кличке (поиск должен быть эффективным)
 * изменение данных животного по его идентификатору
 * вывод на экран списка животных в отсортированном порядке. Поля для сортировки –  хозяин, кличка животного, вес.
 */

public class Main {
    public static void main(String []args) throws DuplicatePetException {
        Person owner1 = new Person ("Adam",21,0);
        Person owner2 = new Person ("Briana",22,1);
        Person owner3 = new Person ("Clair",23,1);

        Animal animal1 = new Animal ("Richard",owner1,11.1);
        Animal animal2 = new Animal ("Kitty",owner2,22.2);
        Animal animal3 = new Animal ("Fluffy",owner2,44.44);
        Animal animal4 = new Animal ("Kitty",owner2,55.55);
        Animal animal5 = new Animal ("Fluffy",owner3,33.3);

        Animal animal6 = new Animal ("Kitty",owner2,22.2); // для проверки исключения дублей

        PetList petList = new PetList();
        petList.addPet(animal1);
        petList.addPet(animal2);
        petList.addPet(animal3);
        petList.addPet(animal4);
        petList.addPet(animal5);
//        petList.addPet(animal6); // для проверки исключения дублей
        petList.findPetByNickName("fluffy"); //поиск питомца
        System.out.println();

//редактирование питомца
        int petIdToEdit = 1;
        petList.editPetInfo(petIdToEdit, "Tom", 50);
        System.out.println();

        petList.printPetList(); //вывод отсортированного списка
    }
}
