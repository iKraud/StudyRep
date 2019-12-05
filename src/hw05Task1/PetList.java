package hw05Task1;

import java.util.*;

public class PetList {
    static int count = 0;
    Map<Integer, Animal> petMap = new HashMap<>();

    public void addPet (Animal animal) throws DuplicatePetException {
        if (petMap.containsValue(animal)) {
            throw new DuplicatePetException("Питомец: " + animal + " уже есть в базе");
        }
        petMap.put(++count, animal);
    }
    public void findPetByNickName (String nickName) {
        System.out.println("Ищем питомца по кличке: " + nickName);
        int total = 0;
        for (Map.Entry<Integer, Animal> el : petMap.entrySet()) {
            if (el.getValue().getNickname() == nickName) {
                total++;
                System.out.println(el.getValue());
            }
        }
        System.out.println("Найдено " + total + " питомцев с такой кличкой!");
    }
    public void editPetInfo (int id, String newNickName, double newWeight) {
        if (petMap.containsKey(id)) {
            for (Map.Entry<Integer, Animal> el : petMap.entrySet()) {
                if (el.getKey() == id) {
                    el.getValue().setNickname(newNickName);
                    el.getValue().setWeight(newWeight);
                    System.out.println("Питомец с ID:" + id + " успешно отредактировано. Теперь его характеристики:");
                    System.out.println(el.getValue());
                }
            }
            return;
        }
        System.out.println("Питомца с ID:" + id + " в базе нет!");
    }
    public void printPetList () {
        List<Animal> list = new ArrayList<>();
        for (Map.Entry<Integer, Animal> el : petMap.entrySet()) {
            list.add(el.getValue());
        }
        Collections.sort(list, new Comparator() {
            @Override
            public int compare(Object o, Object t1) {
                double c;
                c = ((Animal) o).getOwner().getName().compareTo(((Animal) t1).getOwner().getName());
                if (c == 0) {
                    c = ((Animal) o).getNickname().compareTo(((Animal) t1).getNickname());
                }
                if (c == 0) {
                    c = ((Animal) o).getWeight() - ((Animal) t1).getWeight();
                }
                return (int) c;
            }
        });

        for (Object el : list) {
            System.out.println(el);
        }
    }
}
