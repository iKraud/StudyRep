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
    public static void main(String[] args) {
        Random rnd = new Random();
        int lettersInWord; //латинских букв в слове 1<=n2<=15
        int probability = 10; //вероятность вхождения одного из слов массива в следующее предложение (1/probability)
        int amountOfFiles = 10; // файлов нужно сформировать
        // String path = "Путь:\\" //путь для сохранения файлов

        // генерация дополнительного массива слов
        int wordsInAdditionalArray = 40; //rnd.nextInt(1000); //слов в дополнительном массиве 1<=n4<=1000

        List<String> additionalArray = new ArrayList<>();
        for (int i = 0; i < wordsInAdditionalArray; i++) {
            String word = "";
            lettersInWord = rnd.nextInt(14)+1; //латинских букв в слове 1<=n2<=15
            for (int j = 0; j < lettersInWord; j++) {
                word += String.valueOf(Character.forDigit(rnd.nextInt(35-10)+10,36));
            }
            additionalArray.add(word);
        }
        for (String el : additionalArray) {
            System.out.println(el);
        }
        // генерация дополнительного массива слов

        // создание параграфов
        List<String> sentenceArray = new ArrayList<>();
        int wordsInSentence = rnd.nextInt(14)+1; //слов в предложении 1<=n1<=15
        int sentenceInParagrarh = rnd.nextInt(19)+1; //предложений в абзаце 1<=n3<=20
        int amountOfParagraph = 10; // количество абзацев getFiles - size
        for (int par = 0; par < amountOfParagraph; par++) {
            for (int sent = 0; sent < sentenceInParagrarh; sent++) {
                for (int wordCount = 0; wordCount < wordsInSentence; wordCount++) {
                    String word = "";
                    lettersInWord = rnd.nextInt(14)+1; //латинских букв в слове 1<=n2<=15
                    for (int j = 0; j < lettersInWord; j++) {
                        word += String.valueOf(Character.forDigit(rnd.nextInt(35-10)+10,36));
                    }
                    sentenceArray.add(word);
                    sentenceArray.add(" ");
                }
                sentenceArray[0] = sentenceArray[0].toUpperCase; // Предложение начинается с заглавной буквы
                sentenceArray.remove(sentenceArray.size()); //удаляем последний пробел
                switch (rnd.nextInt(4)) { // Предложение заканчивается (.|!|?)+" "
                    case 0:
                        sentenceArray.add("! ");
                        break;
                    case 1:
                        sentenceArray.add("? ");
                        break;
                    default:
                        sentenceArray.add(". ");
                }
            }
            sentenceArray.remove(sentenceArray.size()); //удаляем последний пробел
            sentenceArray.add("\n\r"); // В конце абзаца стоит разрыв строки и перенос каретки.
        }
        // создание параграфов
    }
        
}
