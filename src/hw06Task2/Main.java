package hw06Task2;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

/**
 * @author "Timohin Igor"
 *
Задание 2. Создать генератор текстовых файлов, работающий по следующим правилам:
Предложение состоит из 1<=n1<=15 слов.
В предложении после произвольных слов могут находиться запятые.
Слово состоит из 1<=n2<=15 латинских букв
Слова разделены одним пробелом
Предложение начинается с заглавной буквы
Предложение заканчивается (.|!|?)+" "
Текст состоит из абзацев. в одном абзаце 1<=n3<=20 предложений.
В конце абзаца стоит разрыв строки и перенос каретки.
Есть массив слов 1<=n4<=1000.
Есть вероятность probability вхождения одного из слов этого массива в следующее предложение (1/probability).
Необходимо написать метод getFiles(String path, int n, int size, String[] words, int probability),
который создаст n файлов размером size в каталоге path. words - массив слов, probability - вероятность.
 */

public class Main {

    public static void main(String[] args) throws IOException {
        Random rnd = new Random();
        int lettersInWord; //латинских букв в слове 1<=n2<=15
/**
 * Генерация дополнительного массива слов
 */
        int wordsInAdditionalArray = rnd.nextInt(1000)+1; //слов в дополнительном массиве 1<=n4<=1000

        List<String> additionalArray = new ArrayList<>();
        for (int i = 0; i < wordsInAdditionalArray; i++) {
            String word = "";
            lettersInWord = rnd.nextInt(15)+1; //латинских букв в слове 1<=n2<=15
            for (int j = 0; j < lettersInWord; j++) {
                word += String.valueOf(Character.forDigit(rnd.nextInt(35-10)+10,36));
            }
            additionalArray.add(word.toUpperCase()); //upperCase - чтобы отличить основные слова от дополнительных
        }
        getFiles ("C:\\Java\\IdeaProjects\\untitled\\src\\hw06Task2\\", 5, 7, additionalArray, 4);
    }

/**
 * Создание абзацев
 *
 * @param path каталог, в котором будут создаваться конечные файлы
 * @param n количество создаваемых файлов
 * @param size размер файлов
 * @param words массив дополнительных слов
 * @param probability вероятность попадания слов из дополнительнго массива в финальный файл
 */
    private static void getFiles(String path, int n, int size, List<String> words, int probability) throws IOException {
        Random rnd = new Random();
        int sentenceInParagraph; //предложений в абзаце 1<=n3<=20
        int wordsInSentence; //слов в предложении 1<=n1<=15
        int lettersInWord; //латинских букв в слове 1<=n2<=15
        List<String> sentenceArray = new ArrayList<>();
        int amountOfParagraph = 0;
        for (int f = 0; f < n; f++) {
            do {
                sentenceInParagraph = rnd.nextInt(20) + 1; //предложений в абзаце 1<=n3<=20
                for (int sentence = 0; sentence < sentenceInParagraph; sentence++) {
                    wordsInSentence = rnd.nextInt(15) + 1; //слов в предложении 1<=n1<=15
                    for (int wordCount = 0; wordCount < wordsInSentence; wordCount++) {
                        String word = "";
                        //Есть вероятность probability вхождения одного из слов этого массива в следующее предложение (1/probability)
                        if (rnd.nextInt(probability) == (probability - 1)) {
                            word = words.get(rnd.nextInt(words.size()));
                        } else {
                            lettersInWord = rnd.nextInt(15) + 1; //латинских букв в слове 1<=n2<=15
                            for (int letter = 0; letter < lettersInWord; letter++) {
                                word += String.valueOf(Character.forDigit(rnd.nextInt(35 - 10) + 10, 36));
                                if ((wordCount == 0) && (letter == 0)) {
                                    word = word.toUpperCase(); // Предложение начинается с заглавной буквы
                                }
                            }
                        }
                        sentenceArray.add(word);
                        if (wordCount < (wordsInSentence - 1)) {
                            switch (rnd.nextInt(5)) {
                                case 0: // В предложении после произвольных слов могут находиться запятые
                                    sentenceArray.add(", ");
                                    break;
                                default:
                                    sentenceArray.add(" ");
                            }
                        }
                    }

                    switch (rnd.nextInt(5)) { // Предложение заканчивается (.|!|?)+" "
                        case 0:
                            sentenceArray.add("!");
                            break;
                        case 1:
                            sentenceArray.add("?");
                            break;
                        default:
                            sentenceArray.add(".");
                    }
                    if (sentence < sentenceInParagraph - 1) { // чтобы абзац не заканчивался на пробел
                        sentenceArray.add(" ");
                    }
                }
                sentenceArray.add("\n\r"); // В конце абзаца стоит разрыв строки и перенос каретки
            } while (++amountOfParagraph < size);
/**
 * Запись в файлы
 */
            FileOutputStream fos = new FileOutputStream(path + "File_" + f +".txt");
            for (String el : sentenceArray) {
                fos.write(el.getBytes());
            }
            fos.close();
        }
        System.out.println("Файлы успешно созданы по пути: " + path);
    }
}