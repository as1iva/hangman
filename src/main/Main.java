package main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {

    public static Scanner scanner = new Scanner(System.in);
    public static Random random = new Random();
    public static List<Character> letters = new ArrayList<>();
    public static int mistakes = 0;
    public static int MISTAKES_TO_LOSE = 6;
    private final static String PLAY = "1";
    private final static String EXIT = "2";
    public static String GAME_STATE_PLAYER_WON = "Вы победили!";
    public static String GAME_STATE_PLAYER_LOSE = "Вы проиграли!";
    public static String GAME_STATE_NOT_FINISHED = "Игра не закончена";

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

    public static char[] getWord() {
        List<String> wordsFile = null;
        int sizeFile = 0;

        try {
            wordsFile = Files.readAllLines(Paths.get("resources/russian-words.txt"));
            sizeFile = wordsFile.size();
        } catch (IOException e) {
            System.out.println("Ошибка. Файл не найден.");
            System.exit(1);
        } catch (NullPointerException e) {
            System.out.println("Ошибка. Файл пустой.");
            System.exit(1);
        }

        int randomWord = random.nextInt(sizeFile);

        return wordsFile.get(randomWord).toCharArray();
    }

    public static char[] maskWord(char[] word) {
        char[] maskWord = word.clone();

        Arrays.fill(maskWord, '_');

        return maskWord;
    }

    public static void checkWord(char guess, char[] word, char[] maskWord) {
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
    }

    public static boolean isLetterCorrect(Character guess) {
        String regex = "^[а-яё]+$";
        if (!guess.toString().matches(regex)) {
            System.out.println("Можно вводить только кириллицу в нижнем регистре.");
            return false;
        }

        if (letters.isEmpty()) {
            letters.add(guess);
            System.out.println(letters);
            return true;
        }

        for (Character letter : letters) {
            if (guess.equals(letter)) {
                System.out.println("Эта буква была введена раннее, попробуйте другую.");
                return false;
            }
        }
        letters.add(guess);

        Collections.sort(letters);
        System.out.println(letters);

        return true;
    }

    public static boolean isChoiceCorrect(String choice) {
        String regex = "^[1-9]$";
        if (!choice.matches(regex)) {
            System.out.println("Можно ввести только одну цифру.");
            return false;
        }
        return true;
    }

    public static String checkGameState(char[] maskWord, int mistakes) {
        if (mistakes == MISTAKES_TO_LOSE) {
            printHangman(MISTAKES_TO_LOSE);
            return GAME_STATE_PLAYER_LOSE;
        } else {
            printHangman(mistakes);
        }

        for (char letter : maskWord) {
            if (letter == '_') {
                System.out.print("Загаданное слово: ");
                System.out.println(maskWord);
                return GAME_STATE_NOT_FINISHED;
            }
        }
        return GAME_STATE_PLAYER_WON;
    }

    public static void startGameLoop() {
        char[] word = getWord();
        char[] maskWord = maskWord(word);

        printHangman(0);

        System.out.print("Загаданное слово: ");
        System.out.println(maskWord);
        do {
            System.out.print("Введите букву: ");

            String letter = scanner.nextLine();
            char guess;

            if (letter != null && letter.length() == 1) {
                guess = letter.charAt(0);
            } else {
                System.out.println("Можно вводить только одну букву.");
                continue;
            }

            if (!isLetterCorrect(guess)) {
                continue;
            }

            checkWord(guess, word, maskWord);
            String gameState = checkGameState(maskWord, mistakes);

            if (!Objects.equals(gameState, GAME_STATE_NOT_FINISHED)) {
                System.out.println(gameState);
                System.out.println("Загаданное слово: " + String.valueOf(word) + "\n");

                mistakes = 0;
                letters.clear();

                return;
            }
        } while (true);
    }

    public static void main(String[] args) {
        System.out.println("Добро пожаловать в виселицу!");
        do {
            System.out.printf("%s - Новая игра\n%s - Выйти\n", PLAY, EXIT);

            String choice = scanner.nextLine();

            if (!isChoiceCorrect(choice)) {
                continue;
            }

            if (Objects.equals(choice, PLAY)) {
                startGameLoop();
            } else if (Objects.equals(choice, EXIT)) {
                System.out.println("До встречи!");
                scanner.close();
                return;
            } else {
                System.out.println("Введите корректную цифру.");
            }
        } while (true);
    }
}