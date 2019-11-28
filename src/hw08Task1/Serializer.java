package hw08Task1;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class Serializer {
    /**
     * упаковывает объект в файл
     * @param object объект для упаковки
     * @param file путь к файлу, в который будем упаковывать
     * @throws IllegalAccessException
     */
    public void serialize(Object object, String file) throws IllegalAccessException {
        String info = getStringFromObject(object);

        try (FileOutputStream fos = new FileOutputStream(file);
            PrintWriter pw = new PrintWriter(fos)) {
            pw.write(info);
            System.out.println("Объект успешно сериализован!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * извлекает объект из файла
     * @param file путь к файлу, из которого будем извлекать объект
     * @return
     * @throws IOException
     * @throws IllegalAccessException
     * @throws ClassNotFoundException
     * @throws InstantiationException
     */
    public Object deSerialize(String file) throws IOException, IllegalAccessException, ClassNotFoundException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        BufferedReader fis = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF8"));
        String fileContent = "";
        while (fis.ready()) {
            fileContent += fis.readLine();
        }
        fis.close();

        int beginNextSearch = 0;
        Object object = getObjectFromString(fileContent, beginNextSearch);
        System.out.println("Объект успешно ДЕсериализован!");
        return object;
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

    /**
     * обращение объекта в строку
     * @param object объект для обращения в строку
     * @return строку, представляющую собой объект, разбитый по полям и значения
     * @throws IllegalAccessException
     */
    public String getStringFromObject (Object object) throws IllegalAccessException {
        Object currentObject = object;
        String info = "class:" + object.getClass() + ";"; //сначала записываем название нового класса
        for (Field el : object.getClass().getDeclaredFields()) {
            Field fieldName = el;
            el.setAccessible(true);
            info += "fieldName:" + fieldName +  ";"; //перебираем поля
            if (!el.getType().isPrimitive() &&
                    (!el.getType().equals(String.class)) &&
                    (!el.getType().isEnum())) {
                el.setAccessible(true);
                currentObject = el.get(currentObject);
                info += getStringFromObject(currentObject); //если поле это объект, то рекурсия
            }
            info += "fieldValue:" + el.get(object).toString() + ";"; //заполняем значения полей
        }
        return info;
    }

    /**
     * получение объекта из строки
     * @param fileContent
     * @param beginNextSearch
     * @return объект полученный из файла в виде строки
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public Object getObjectFromString (String fileContent, int beginNextSearch) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        String searchResult;
        String className;
        Object subObject;
        searchResult = searchSubString(fileContent, "class:", beginNextSearch);
        beginNextSearch += "class:".length() + (searchResult + ";").length();

        className = searchResult.substring(searchResult.lastIndexOf(" ") + 1);
        Class CurrentClass = Class.forName(className);
        Object object = CurrentClass.getDeclaredConstructor().newInstance(); //сначала создаём объект нового класса

        for (Field fd : object.getClass().getDeclaredFields()) { //перебираем поля
            searchResult = searchSubString(fileContent, "fieldName:", beginNextSearch);
            beginNextSearch += ("fieldName:").length() + ((searchResult + ";").length());
            if (fileContent.substring(beginNextSearch, beginNextSearch + "class:".length()).equals("class:")) { //если после имени поля идёт класс
                subObject = getObjectFromString(fileContent, beginNextSearch); //значит рекурсия
                fd.setAccessible(true);
                fd.set(object, subObject);
            } else { //записываем значение
                searchResult = searchSubString(fileContent, "fieldValue:", beginNextSearch);
                beginNextSearch += ("fieldValue:").length() + ((searchResult + ";").length());
                fd.setAccessible(true);
                if (fd.getType() == String.class) {
                    fd.set(object, searchResult);
                } else if (fd.getType() == int.class) {
                    fd.set(object, Integer.parseInt(searchResult));
                } else if (fd.getType().isEnum()) {
                    fd.set(object, Enum.valueOf((Class<Enum>) fd.getType(), searchResult));
                }
            }
        }
        return object;
    }
}
