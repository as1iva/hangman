import java.util.Scanner;

public class Main {
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