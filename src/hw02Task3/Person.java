package hw02Task3;

import java.util.Comparator;

class Person implements Comparable {
    private int age;
    private String name;
    private Sex sex;

    public void setAge (int age) {
        if (age >= 0 && age <=100) {
            this.age = age;
        }
    }

    public int getAge () {
        return age;
    }

    public void setName (int name) {
        this.name =
        String.valueOf(Character.forDigit (name+10,36)) +
        String.valueOf(Character.forDigit (name+11,36)) +
        String.valueOf(Character.forDigit (name+12,36)) +
        String.valueOf(Character.forDigit (name+13,36)) +
        String.valueOf(Character.forDigit (name+14,36)) +
        String.valueOf(Character.forDigit (name+15,36));
    }

    public String getName () {
        return name;
    }

    public void setSex (int sex) {
        if (sex == 0) {
            this.sex = Sex.MAN;
        } else {
            this.sex = Sex.WOMAN;
        }
    }

    public Sex getSex () {
        return sex;
    }

    public Person (int age, int name, int sex) {
        setAge(age);
        setName(name);
        setSex(sex);
    }

    public static void Compare (Person[] p, int element) throws SameNameSameAgeException {
        for (int i = 0; i <=element; i++) {
            if (i != element) {
                if ((p[i].getAge()==p[element].getAge()) &
                    (p[i].getName().equals(p[element].getName()))) {

                    throw new SameNameSameAgeException ("У людей с ID "+i+" и "+element+" возраст и имя совпали");
               }
            }
        }
    }

    @Override
    public String toString () {
        return ("age="+age +", name="+name +", sex="+sex);
    }

    @Override
    public int compareTo(Object o) {
        Person p = (Person)o;

        return Comparator.comparing(Person::getSex)
                .thenComparing(Person::getAge)
                .thenComparing(Person::getName)
                .compare(this, p);
    }
}
