package hw05Task1;

class Person {
    private String name;
    private int age;
    private Sex sex;

    public Person (String name, int age, int sex) {
        setName(name);
        setAge(age);
        setSex(sex);
    }
    public void setName (String name) {
        this.name = name;
    }
    public String getName () {
        return name;
    }
    public void setAge (int age) {
        this.age = age;
    }
    public int getAge () {
        return age;
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
}