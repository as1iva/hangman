import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static Scanner scanner = new Scanner(System.in);
    public static Random random = new Random();
    public static int mistakes = 0;
    public static int MISTAKES_TO_LOSE = 6;
    public static String GAME_STATE_PLAYER_WON = "Вы победили!";
    public static String GAME_STATE_PLAYER_LOSE = "Вы проиграли!";
    public static String GAME_STATE_NOT_FINISHED = "Игра не закончена";

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
        return maskWord;
    }

    public static String checkGameState(char[] maskWord, int mistakes) {
        if (mistakes == MISTAKES_TO_LOSE) {
            return GAME_STATE_PLAYER_LOSE;
        } else {
            printHangman(mistakes);
        }

        for (char letter : maskWord) {
            if (letter == '_') {
                System.out.println(maskWord);
                return GAME_STATE_NOT_FINISHED;
            }
        }
        return GAME_STATE_PLAYER_WON;
    }

    public static void main(String[] args) {
        do {
            System.out.println("1 - Новая игра\n" +
                    "2 - Выйти");

            int choice = scanner.nextInt();

            if (choice == 1) {
                //startGameLoop();
            }
            else {
                System.out.println("До встречи!");
                return;
            }
        } while (true);
    }
}