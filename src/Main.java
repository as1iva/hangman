import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static Scanner scanner = new Scanner(System.in);

    public static Random random = new Random();

    public static int MISTAKES_TO_LOSE = 6;

    public static char[] getWord() {
        List<String> wordsFile = null;


        try {
            wordsFile = Files.readAllLines(Paths.get("russian-words.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        char[] word = wordsFile.get(random.nextInt(0, wordsFile.size())).toCharArray();

        return word;
    }

    public static void printHangman(int mistake) {
        switch (mistake) {
            case 0:
                System.out.println("""
            +---+
            |   |
                |
                |
                |
                |
          =========
        """);
                break;
            case 1:
                System.out.println("""
            +---+
            |   |
            O   |
                |
                |
                |
          =========
        """);
                break;
            case 2:
                System.out.println("""
            +---+
            |   |
            O   |
            |   |
                |
                |
          =========
        """);
                break;
            case 3:
                System.out.println("""
            +---+
            |   |
            O   |
           /|   |
                |
                |
          =========
        """);
                break;
            case 4:
                System.out.println("""
            +---+
            |   |
            O   |
           /|\\  |
                |
                |
          =========
        """);
                break;
            case 5:
                System.out.println("""
            +---+
            |   |
            O   |
           /|\\  |
           /    |
                |
          =========
        """);
                break;
            case 6:
                System.out.println("""
            +---+
            |   |
            O   |
           /|\\  |
           / \\  |
                |
          =========
        """);
                break;
        }
    }

    public static char[] maskWord(char[] word) {
        char[] maskWord = word.clone();

        for (int index = 0; index < maskWord.length; index++) {
            maskWord[index] = '_';
        }

        return maskWord;
    }

    public static char[] checkWord(char guess, char[] word, char[] maskWord) {
        int mistakes = 0;
        int counter = 0;

        for (int i = 0; i < word.length; i++) {
            if (guess == word[i]) {
                maskWord[i] = guess;
                counter++;
            }
        }

        if (counter == 0) {
            mistakes++;
        }

        checkGameState(maskWord, mistakes);
        return maskWord;
    }

    public static void checkGameState(char[] maskWord, int mistakes) {
        if (mistakes == MISTAKES_TO_LOSE) {
            System.out.println("Вы проиграли!\n");
        } else {
            printHangman(mistakes);
        }

        for (char letter : maskWord) {
            if (letter == '_') {
                System.out.println(maskWord);
                return;
            } else {
                break;
            }
        }
        System.out.println("Вы выиграли!\n");
    }


    public static void main(String[] args) {
        do {
            System.out.println("1 - Новая игра\n" +
                    "2 - Выйти");

            int choice = scanner.nextInt();

            if (choice == 1) {
                // gameLoop

                char[] word = getWord();
                char[] guessWord = maskWord(word);

                System.out.println(word);
                System.out.println(guessWord);

                char guess = scanner.next().charAt(0);
                char[] check = checkWord(guess, word, guessWord);

                System.out.println(check);
            }
            else {
                System.out.println("До встречи!");
                return;
            }
        } while (true);
    }
}