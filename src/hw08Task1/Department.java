package hw08Task1;

import java.io.Serializable;

public class Department implements Serializable {
    private static final long serialVersionUID = 6264470610159095067L;
    private String name;
    private int id;

    public Department(String name, int id) {
        setName(name);
        setId(id);
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Department{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
