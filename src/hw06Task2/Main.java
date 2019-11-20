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
        int wordsInSentence; //слов в предложении 1<=n1<=15
        int lettersInWord; //латинских букв в слове 1<=n2<=15
        int sentenceInParagrarh; //предложений в абзаце 1<=n3<=20

        int probability = 10; //вероятность вхождения одного из слов массива в следующее предложение (1/probability)
        int amountOfFiles = 10; // файлов нужно сформировать
        // String path = "Путь:\\" //путь для сохранения файлов
        // количество абзацев getFiles - size

        // генерация дополнительного массива слов
        int wordsInAdditionalArray = 40; //rnd.nextInt(1000); //слов в дополнительном массиве 1<=n4<=1000

        List<String> AdditionalArray = new ArrayList<>();
        for (int i = 0; i < wordsInAdditionalArray; i++) {
            String additionalWord = "";
            lettersInWord = rnd.nextInt(14)+1; //латинских букв в слове 1<=n2<=15
            for (int j = 0; j < lettersInWord; j++) {
                additionalWord += String.valueOf(Character.forDigit(rnd.nextInt(35-10)+10,36));
            }
            AdditionalArray.add(additionalWord);
        }
        for (String el : AdditionalArray) {
            System.out.println(el);
        }
        // генерация дополнительного массива слов
        
        
    }
        
}
