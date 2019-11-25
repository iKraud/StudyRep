package hw08Task1;

/**
 * Задание 1. Необходимо разработать класс, реализующий следующие методы:
 * void serialize (Object object, String file);
 * Object deSerialize(String file);
 *
 * Методы выполняют сериализацию объекта Object в файл file и десериализацию объекта из этого файла.
 * Обязательна сериализация и десериализация "плоских" объектов (все поля объекта - примитивы, или String).
 *
 * Задание 2. Предусмотреть работу c любыми типами полей (полями могут быть ссылочные типы).
 *
 * Требование: Использовать готовые реализации (Jaxb, jackson и т.д.) запрещается.
 */
public class Main {
    public static void main(String[] args) {
        String path = "R:\\ССиОР\\СРТиС\\03 - Разработка\\Тимохин И.В\\055_IdeaProjects\\src\\hw08Task1\\Text.txt";
        Department department = new Department("NYPD",1);
        Worker worker1 = new Worker("Adam", 17, Access.ACTIVE, department);
        Serializer serializer = new Serializer();
        serializer.serialize(worker1, path);
        Worker worker2 = (Worker) serializer.deSerialize(path);
        System.out.println(worker2);
    }
}

public enum Access {
    ACTIVE, INACTIVE
}

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

public class Serializer implements Serializable {
    public void serialize (Object object, String file) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.getClass();
            oos.writeObject(object);
            System.out.println("Объект успешно сериализован!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Object deSerialize (String file) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))){
            Object object = ois.readObject();
            System.out.println("Объект успешно ДЕсериализован!");
            return object;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    //        MyObject myObject = new MyObject(1, 'c', "args");
//        Field[] fields = myObject.getClass().getDeclaredFields();
//        for (Field el: fields) {
//            System.out.println(el.getName() + " " + el.getType() + " " + el.getModifiers());
//        }
//        Method[] methods = myObject.getClass().getDeclaredMethods();
//        for (Method el: methods) {
//            System.out.println(el.getExceptionTypes());
//        }
//        Class c = Class.forName("MyObject");
//        Object object = c.newInstance();
//        MyObject myObject1 = (MyObject) object;
}

public class Worker implements Serializable {
    private static final long serialVersionUID = -6657542965966980624L;

    private String name;
    private int floor;
    private Access access;
    private Department department;

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
