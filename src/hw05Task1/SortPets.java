package hw05Task1;

import java.util.Comparator;

class SortPets implements Comparator<Animal>
{

    public int compare(Animal an1, Animal an2) {
        double c = 0;
        c = an1.getOwner().getName().compareTo(an2.getOwner().getName());
        if (c == 0) {
            c = an1.getNickname().compareTo(an2.getNickname());
        }
        if (c == 0) {
            c = an1.getWeight() - an2.getWeight();
        }

        return (int)c;
    }
}