import java.util.ArrayList;
import java.util.Scanner;

public class BlackJackGame {
    private Deck deck;
    private Dealer dealer;
    private ArrayList<Player> players; // players in the game
    private ArrayList<Player> activePlayers; // players currently playing
    private Scanner scanner;

    public BlackJackGame(Scanner scanner) {
        this.scanner = scanner;
        this.deck = new Deck();
        this.dealer = new Dealer();
        this.players = new ArrayList<>();
        this.activePlayers = new ArrayList<>();
        this.addPlayers();
    }
    public void resetGame(){
        this.deck.reset();
        this.dealer.clear();
        for (int i = players.size() - 1; i >= 0; i--) {
            Player player = players.get(i);
            System.out.println("Do you want to continue playing, " + player.getName() + "? (yes/no)");
            String response = scanner.nextLine().toLowerCase();
            if (response.equals("no")) {
                System.out.println("Thanks for playing, " + player.getName() + "! You won " + player.getWins() + " times.");
                players.remove(i);
            } else {
                player.clear();
                player.setPlaying(true);
            }
        }
        activePlayers.clear();
        activePlayers.addAll(players);
        addPlayers();
    }

    public void addPlayers(){
        System.out.println("How many players are joining this round?");
        int numPlayers = Integer.parseInt(scanner.nextLine());
        for (int i = 1; i <= numPlayers; i++) {
            System.out.println("Enter name for Player " + i + ":");
            String name;
            if (!scanner.hasNextLine()) {
                name = "Player " + i;
            } else {
                name = scanner.nextLine();
            }
            Player player = new Player(name, scanner);
            players.add(player);
            activePlayers.add(player);
        }
    }

    public void initialDeal() {
        for (Player player : players) {
            player.drawCard(deck.deal());
            player.drawCard(deck.deal());
            player.showHand();
        }

        // Deal to dealer, making sure that dealer always gets blackjack or a 20

        while (deck.peekCard().getRank() != 'A' && deck.peekCard().getRank() != '0' && 
        deck.peekCard().getRank() != 'K' && deck.peekCard().getRank() != 'Q' && 
        deck.peekCard().getRank() != 'J') {
            deck.skipCard();
        }
        dealer.drawCard(deck.deal());
        System.out.println("dealer draw 1 done");

        while (deck.peekCard().getRank() != 'A' && deck.peekCard().getRank() != '0' && 
        deck.peekCard().getRank() != 'K' && deck.peekCard().getRank() != 'Q' && 
        deck.peekCard().getRank() != 'J') {
            deck.skipCard();
        }
        dealer.drawCard(deck.deal());
        System.out.println("dealer initial draw done");
        dealer.showInitialHand();
        // dealer.showHand();
        
        checkInitialBlackjack();
    }

    public boolean checkBlackJack(Person person) {
        return person.getHand().getValue() == 21;
    }

    public void checkInitialBlackjack() {
        boolean dealerBlackJack = checkBlackJack(dealer);
        if (dealerBlackJack) {
            System.out.println("Dealer has a Blackjack!");
        }
        for (Player player : new ArrayList<>(activePlayers)) {
            boolean playerBlackJack = checkBlackJack(player);
            if (playerBlackJack && dealerBlackJack) {
                System.out.println(player.getName() + " and Dealer both have Blackjack! It's a push.");
                player.setPlaying(false);
            } else if (playerBlackJack) {
                System.out.println(player.getName() + " has a Blackjack! " + player.getName() + " wins!");
                player.incrementWins();
                player.setPlaying(false);
            } else if (dealerBlackJack) {
                System.out.println("Dealer has a Blackjack! " + player.getName() + " loses.");
                player.setPlaying(false);
            }
        }
    }

    public void checkWin(Player player) {
        int dealerValue = dealer.getHand().getValue();
        // for (Player player : new ArrayList<>(players)){
        //     if (!player.isPlaying()){
        //         continue;
        //     }
        //     if (dealer.getHand().isBust()){
        //         System.out.println("Dealer busts! " + player.getName() + " wins!");
        //         player.incrementWins();
        //         player.setPlaying(false);
        //     } else if (player.getHand().isBust()) {
        //         System.out.println(player.getName() + " busts and loses.");
        //         player.setPlaying(false);
        //     } else {
        //         int playerValue = player.getHand().getValue();
        //         if (playerValue > dealerValue) {
        //             System.out.println(player.getName() + " wins with " + playerValue + " against dealer's " + dealerValue + "!");
        //             player.incrementWins();
        //         } else if (playerValue < dealerValue) {
        //             System.out.println(player.getName() + " loses with " + playerValue + " against dealer's " + dealerValue + ".");
        //         } else {
        //             System.out.println(player.getName() + " and Dealer push with " + playerValue + ".");
        //         }
        //     }
        // }


        int playerValue = player.getHand().getValue();
        // if (dealer.getHand().isBust()){
        //     System.out.println("Dealer busts! " + player.getName() + " wins!");
        //     player.incrementWins();
        //     player.setPlaying(false);
        // } else if (player.getHand().isBust()) {
        //     System.out.println(player.getName() + " busts and loses.");
        //     player.setPlaying(false);
        // } else {
        //     if (playerValue > dealerValue) {
        //         System.out.println(player.getName() + " wins with " + playerValue + " against dealer's " + dealerValue + "!");
        //         player.incrementWins();
        //     } else if (playerValue < dealerValue) {
        //         System.out.println(player.getName() + " loses with " + playerValue + " against dealer's " + dealerValue + ".");
        //     } else {
        //         System.out.println(player.getName() + " and Dealer push with " + playerValue + ".");
        //     }
        // }

        if (dealer.getHand().isBust()) {
            System.out.println("Dealer busts! " + player.getName() + " wins!");
            player.incrementWins();
        } else if (player.getHand().isBust()) {
            System.out.println(player.getName() + " busts and loses.");
        } else if (playerValue > dealerValue) {
            System.out.println(player.getName() + " wins with " + playerValue + " against dealer's " + dealerValue + "!");
            player.incrementWins();
        } else if (playerValue < dealerValue) {
            System.out.println(player.getName() + " loses with " + playerValue + " against dealer's " + dealerValue + ".");
        } else {
            System.out.println(player.getName() + " and Dealer push with " + playerValue + ".");
        }
        player.setPlaying(false);
        activePlayers.remove(player);
    }

    public void determineWinners() {
        int dealerValue = dealer.getHand().getValue();
        
        for (Player player : new ArrayList<>(activePlayers)) {
            int playerValue = player.getHand().getValue();
            
            if (dealer.getHand().isBust()) {
                System.out.println(player.getName() + " wins!");
                player.incrementWins();
            } else if (playerValue > dealerValue) {
                System.out.println(player.getName() + " wins with " + playerValue + " against dealer's " + dealerValue + "!");
                player.incrementWins();
            } else if (playerValue < dealerValue) {
                System.out.println(player.getName() + " loses with " + playerValue + " against dealer's " + dealerValue + ".");
            } else {
                System.out.println(player.getName() + " pushes with " + playerValue + ".");
            }
        }
        
        activePlayers.clear();
    }

    
    public void playerTurn(Player player) {
        while (player.isPlaying()) {
            boolean hit = player.checkAction();
            if (hit) {
                Card drawnCard = deck.deal();
                System.out.println(player.getName() + " draws: " + drawnCard);
                player.drawCard(drawnCard);
                player.showHand();
                if (player.getHand().isBust()) {
                    System.out.println(player.getName() + " busts and loses. ");
                    player.setPlaying(false);
                    activePlayers.remove(player);
                }
            } else {
                System.out.println(player.getName() + " stands.");
                player.setPlaying(false);
            }
        }
    }

    public void dealerTurn() {
        if (activePlayers.isEmpty()) {
            System.out.println("All players have busted. Dealer does not play.");
            return;
        }
        dealer.showHand();
        while (dealer.checkAction()) {
            Card drawnCard = deck.deal();
            System.out.println("Dealer draws: " + drawnCard);
            dealer.drawCard(drawnCard);
            dealer.showHand();
        }
        if (dealer.getHand().isBust()) {
            System.out.println("Dealer busts!");
        } 
    }

    public void run() {
        boolean gameOver = false;
        while (!gameOver){
            initialDeal();
            for (Player player : new ArrayList<>(activePlayers)) {
                if (player.isPlaying()) {
                    playerTurn(player);
                }
            }
            dealerTurn();
            determineWinners();
            resetGame();
            if (players.isEmpty()) {
                gameOver = true;
                System.out.println("All players have left the game. Game over.");
                break;
            }
            System.out.println("Starting a new round...");
            System.out.println();
        }
    }
}
