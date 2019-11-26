package hw08Task1;

import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Serializer {
    public void serialize(Object object, String file) throws IllegalAccessException {
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
        String fileContent = "";
        while (fis.ready()) {
            fileContent += fis.readLine();
        }
        fis.close();
        List<String> parts = new ArrayList<>() {
            {
                add("fieldName:");
                add("fieldValue:");
            }
        };
        List<Integer> classesInFileContent = new ArrayList<>();
        for (int i = 0; i < (fileContent.length() - "class:".length()); i++) {
            if (fileContent.substring(i, i + "class:".length()).equals("class:")) {
                i += "class:".length();
                classesInFileContent.add(i);
            }
        }
        List<Integer> fieldsInClasses = new ArrayList<>();
        int fieldsCount = 0;
        for (int i = 0 + "class:".length(); i < (fileContent.length() - "fieldName:".length()); i++) {
            if (fileContent.substring(i, i + "fieldName:".length()).equals("fieldName:")) {
                fieldsCount ++;
            } else if (fileContent.substring(i, i + "class:".length()).equals("class:")) {
                fieldsInClasses.add(fieldsCount);
                fieldsCount = 0;
            }
        }
        fieldsInClasses.add(fieldsCount); //для последнего класса в файле

        String searchResult;
        int beginNextSearch = 0;
        for (int n = 0; n < classesInFileContent.size(); n++) {
            for (int f = 0; f < fieldsInClasses.get(n); f++) {
                Field fieldToFill = null;
                for (int i = 0; i < parts.size(); i++) {
                    searchResult = searchSubString(fileContent, parts.get(i), beginNextSearch);
                    switch (i) {
                        case 0: //fieldName
                            for (Field el : worker.getClass().getDeclaredFields()) {
                                if (el.toString().equals(searchResult)) {
                                    fieldToFill = el;
                                    beginNextSearch += classesInFileContent.get(n) + "class:".length() + searchResult.length() + "fieldName:".length();
                                    System.out.println("поле найдено");
                                }
                            }
                            break;
                        case 1: //fieldValue
                            fieldToFill.setAccessible(true);
                            if (fieldToFill.getType() == String.class) {
                                fieldToFill.set(worker, searchResult);
                            } else if (fieldToFill.getType() == int.class) {
                                fieldToFill.set(worker, Integer.parseInt(searchResult));
                            } else if (fieldToFill.getType().isEnum()) {
                                fieldToFill.set(worker, Enum.valueOf((Class<Enum>) fieldToFill.getType(), searchResult));
                            }
                            break;
                        default:
                            throw new IllegalStateException("Unexpected value: " + i);
                    }
                }
            }
        }
        return worker;
    }

    /**
     * поиск подстроки
     * @param source источник для поиска
     * @param subString искомая подстрока
     */
    public String searchSubString (String source, String subString, int beginNextSearch) {
        int searchBigin;
        int searchEnd;
        searchBigin = source.indexOf(subString, beginNextSearch) + subString.length();
        searchEnd = source.indexOf(";", searchBigin);
        return source.substring(searchBigin, searchEnd);
    }
}