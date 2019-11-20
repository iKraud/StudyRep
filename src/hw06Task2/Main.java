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
который создаст n файлов размером size в каталоге path. words - массив слов, probability - вероятность. ??????????????
 */

public class Main {
    public static void main(String[] args) {
        Random rnd = new Random();
        int lettersInWord; //латинских букв в слове 1<=n2<=15
        int sentenceInParagrarh; //предложений в абзаце 1<=n3<=20
        int wordsInSentence; //слов в предложении 1<=n1<=15
        int probability = 3; //вероятность вхождения одного из слов массива в следующее предложение (1/probability)
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
            additionalArray.add(word.toUpperCase()); //upperCase - чтобы отличить основные слова от дополнительных
        }
        // генерация дополнительного массива слов

        // создание параграфов
        List<String> sentenceArray = new ArrayList<>();
        int amountOfParagraph = 20; // количество абзацев getFiles - size??????????????
        for (int par = 0; par < amountOfParagraph; par++) {
            sentenceInParagrarh = rnd.nextInt(19)+1; //предложений в абзаце 1<=n3<=20
            for (int sent = 0; sent < sentenceInParagrarh; sent++) {
                wordsInSentence = rnd.nextInt(14)+1; //слов в предложении 1<=n1<=15
                for (int wordCount = 0; wordCount < wordsInSentence; wordCount++) {
                    String word = "";
                    //Есть вероятность probability вхождения одного из слов этого массива в следующее предложение (1/probability).
                    if (rnd.nextInt(probability) == (probability-1)) {
                        word = additionalArray.get(rnd.nextInt(additionalArray.size()));
                    } else {
                        lettersInWord = rnd.nextInt(14)+1; //латинских букв в слове 1<=n2<=15
                        for (int letter = 0; letter < lettersInWord; letter++) {
                            word += String.valueOf(Character.forDigit(rnd.nextInt(35-10)+10,36));
                            if ((wordCount == 0) && (letter == 0)) {
                                word = word.toUpperCase(); // Предложение начинается с заглавной буквы
                            }
                        }
                    }
                    sentenceArray.add(word);
                    if (wordCount < (wordsInSentence-1)) {
                        switch (rnd.nextInt(5)) {
                            case 0: //В предложении после произвольных слов могут находиться запятые
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
                if (sent < sentenceInParagrarh-1) { //добавил, чтобы абзац не заканчивался на пробел
                    sentenceArray.add(" ");
                }
            }
            sentenceArray.add("\n\r"); // В конце абзаца стоит разрыв строки и перенос каретки.
        }
        // создание параграфов
        for (String el : sentenceArray) {
            System.out.print(el);
        }
    }
        
}
