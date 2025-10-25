import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        BlackJackGame game = new BlackJackGame(scanner);
        game.run();
    }
}
