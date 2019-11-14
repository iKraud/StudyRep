package hw05Task1;

import java.util.TreeSet;

class Animal {
    TreeSet<Animal> animal= new TreeSet<>();
    private int id;
    private String nickname;
    private Person owner;
    private double weight;

    public Animal (String nickname, Person owner, double weight) throws SuchDogIsAlreadyExist {
        if (animal.contains(nickname)) {
            throw new SuchDogIsAlreadyExist("Собака с такой кличкой уже есть в базе");
        } else {
            setNickname(nickname);
            this.owner = owner;
            this.weight = weight;
        }
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