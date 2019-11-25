package hw08Task1;

import java.io.*;
import java.lang.reflect.Field;

public class Serializer {
    public void serialize (Object object, String file) throws NoSuchFieldException {

//        object.getClass().getDeclaredFields();
//        object.getClass().getDeclaredMethods();
//        object.getClass().getDeclaredConstructors();
        Field field = object.getClass().getDeclaredField("name");

//        String text = "Hello world!"; // строка для записи
        try (FileOutputStream fos = new FileOutputStream(file);
             BufferedOutputStream bos = new BufferedOutputStream(fos)) {
            byte[] buffer = field.getByte();
            bos.write(buffer, 0, buffer.length);
        } catch (IOException e) {
            e.printStackTrace();
        }



        String text = "Hello Wolrd!";
        byte[] buffer = text.getBytes();
        try{
            fos.write(buffer);
        }
        catch(Exception ex){

            System.out.println(ex.getMessage());
        }

            System.out.println("Объект успешно сериализован!");


// с Serializable
//        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
//            oos.writeObject(object);
//            System.out.println("Объект успешно сериализован!");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
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
