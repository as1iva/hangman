import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static char[] getWord() {
        List<String> wordsFile = null;
        Random random = new Random();

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
            case 1:
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
            case 2:
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
            case 3:
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
            case 4:
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
            case 5:
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
            case 6:
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
            case 7:
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

        for (int i = 0; i < word.length; i++) {
            if (guess == word[i]) {
                maskWord[i] = guess;
            } else {
                mistakes++;
            }
        }
        return maskWord;
    }

    public static void main(String[] args) {
        do {
            System.out.println("1 - Новая игра\n" +
                    "2 - Выйти");

            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();

            if (choice == 1) {
                // gameLoop
            }
            else {
                System.out.println("До встречи!");
                return;
            }
        } while (true);
    }
}