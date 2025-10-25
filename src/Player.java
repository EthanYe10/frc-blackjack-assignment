import java.util.Scanner;

public class Player extends Person {
    private boolean playing;
    private Scanner scanner; 
    public Player(String name, Scanner scanner) {
        super(name);
        this.playing = true;
        this.scanner = scanner;
    }
    public boolean checkAction(){
        System.out.println(this.name + ", do you want to 'hit' or 'stand'?");
        String action = scanner.nextLine().toLowerCase();
        boolean hit = action.equals("hit");
        return hit;
    }
    public boolean isPlaying() {
        return playing;
    }
    public void setPlaying(boolean playing) {
        this.playing = playing;
    }
}
