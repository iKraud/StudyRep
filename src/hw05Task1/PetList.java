package hw05Task1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PetList {
    List<Animal> petList = new ArrayList<>();

    public void addPet (Animal animal) throws SuchPetIsAlreadyExist {
        for (Animal an : petList) {
            if (an.equals(animal)) {
                petList.remove(animal);
                Animal.count -= 1;
                throw new SuchPetIsAlreadyExist("Питомец: " + animal + " уже есть в базе");
            }
        }
        petList.add(animal);
    }
    public void findPetByNickName (String nickName) {
        System.out.println("Ищем питомца по кличке: " + nickName);
        int total = 0;
        for (Animal an : petList) {
            if (an.getNickname() == nickName) {
                total += 1;
                System.out.println(an);
            }
        }
        System.out.println();
        System.out.println("Найдено " + total + " питомцев с такой кличкой!");
    }
    public void editPetInfo (int id, String newNickName, double newWeight) {
        for (Animal an : petList) {
            if (an.getId()==id) {
                an.setNickname(newNickName);
                an.setWeight(newWeight);
            }
        }
    }
    public void printPetList () {
        petList.sort(new SortPets());
        for (Animal an : petList) {
            System.out.println(an);
        }
    }
}