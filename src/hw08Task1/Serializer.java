package hw08Task1;

import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Serializer {
    public void serialize(Object object, String file) throws NoSuchFieldException, IllegalAccessException {
        String info = "";
        Class className = object.getClass();
        info += "class:" + className  + ";";

        for (Field el : object.getClass().getDeclaredFields()) {
            Field fieldName = el;
            el.setAccessible(true);
            el.getType();
            String fieldValue = el.get(object).toString();

            info += "fieldName:" + fieldName +  ";" + "fieldValue:" + fieldValue + ";";
        }
//        Field fieldName = object.getClass().getDeclaredField("name");
//        fieldName.setAccessible(true);
//        String fieldValue = (String) fieldName.get(object);
//
//        info += ";fieldName:" + fieldName + ";fieldValue:" + fieldValue + ";";
        try (FileOutputStream fos = new FileOutputStream(file);
             PrintWriter pw = new PrintWriter(fos)) {
            pw.write(info);
            System.out.println("Объект успешно сериализован!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Object deSerialize(String file) throws IOException, IllegalAccessException {
        Worker worker = new Worker();
        BufferedReader fis = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF8"));
        String s = "";
        while (fis.ready()) {
            s += fis.readLine();
        }
        fis.close();
        int searchBigin;
        int searchEnd;
        String searchResult;
        Field fieldToFill = null;
        List<String> parts = new ArrayList<>() {
            {
                add("class:");
                add("fieldName:");
                add("fieldValue:");
            }
        };
        List<Integer> whereIsClass = new ArrayList<>();
        int numberOfClass = 0;
        for (int i = 0; i < (s.length() - "class:".length()); i++) {
            if (s.substring(i, i + "class:".length()).equals("class:")) {
                i += "class:".length();
                whereIsClass.set(numberOfClass, i);
                numberOfClass ++;
            }
        }

        for (int i = 0; i < parts.size(); i++) {
            searchBigin = s.indexOf(parts.get(i)) + parts.get(i).length();
            searchEnd = s.indexOf(";", searchBigin);
            searchResult = s.substring(searchBigin, searchEnd);
            switch (i) {
                case 0: // class
                   if (worker.getClass().toString().equals(searchResult)) {
                       System.out.println("класс совпадает");
                   } else {
                       System.out.println("Ошибочный класс. Десериализация отменена!");
                       return null;
                   }
                    break;
                case 1: //fieldName
                    for (Field el : worker.getClass().getDeclaredFields()) {
                        if (el.toString().equals(searchResult)) {
                            fieldToFill = el;
                            System.out.println("поле найдено");
                        }
                    }
                    break;
                case 2: //fieldValue
                    fieldToFill.setAccessible(true);
                    fieldToFill.set(worker, searchResult);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + i);
            }
        }
//        try (FileInputStream fis = new FileInputStream(file);
//             BufferedInputStream bis = new BufferedInputStream(fis)) {
//            byte[] info = bis.readAllBytes();
//            int ch;
//            while((bis.read())!=-1) {
//
//            }
//
//            System.out.println("Объект успешно ДЕсериализован!");
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        return worker;
    }
}
