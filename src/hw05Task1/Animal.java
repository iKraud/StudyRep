package hw05Task1;

import java.util.Objects;
import java.util.function.Predicate;

class Animal {
    private String nickname;
    private Person owner;
    private double weight;

    public Animal (String nickname, Person owner, double weight) {
        setNickname(nickname);
        setOwner(owner);
        setWeight(weight);
    }
    public void setNickname (String nickname) {
        Predicate<String> isEmpty = s -> s.equals("");
        if (isEmpty.negate().test(nickname)) { // было if (!nickname.equals(""))
            this.nickname = nickname;
        } else {
            this.nickname = "Клички пока нет";
        }
    }
    public String getNickname () {
        return nickname;
    }
    public void setOwner(Person owner) {
        this.owner = owner;
    }
    public Person getOwner() {
        return owner;
    }
    public void setWeight (double weight) {
        if (weight > 0) {
            this.weight = weight;
        }
    }
    public double getWeight() {
        return weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Animal)) return false;
        Animal animal = (Animal) o;
        return  Double.compare(animal.weight, weight) == 0 &&
                nickname.equals(animal.nickname) &&
                owner.equals(animal.owner);
    }
    @Override
    public int hashCode() {
        return Objects.hash(nickname, owner, weight);
    }
    @Override
    public String toString() {
        return "{кличка='" + nickname + '\'' +
                ", вес=" + weight + '}' +
                ", хозяин=" + owner;
    }
}
