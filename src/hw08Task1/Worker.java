package hw08Task1;

public class Worker {
    private String name;
    private int floor;
    private Access access;
    private Department department;

    public Worker () {}

    public Worker (String name, int floor, Access access, Department department) {
        setName(name);
        setFloor(floor);
        setAccess(access);
        setDepartment(department);
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getFloor() {
        return floor;
    }
    public void setFloor(int floor) {
        this.floor = floor;
    }
    public Access getAccess() {
        return access;
    }
    public void setAccess(Access access) {
        this.access = access;
    }
    public Department getDepartment() {
        return department;
    }
    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Worker{" +
                "name='" + name + '\'' +
                ", floor=" + floor +
                ", access=" + access +
                ", department=" + department +
                '}';
    }
}
