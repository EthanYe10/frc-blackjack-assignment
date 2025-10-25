import java.util.ArrayList;

enum PlayerType {
    PLAYER, 
    DEALER
}

public class Hand {
    private ArrayList<Card> cards;
    private PlayerType playerType;
    private int value;

    public Hand(PlayerType playerType) {
        this.cards = new ArrayList<>();
        this.playerType = playerType;
    }

    public void addCard(Card card) {
        cards.add(card);
        if (card.getRank() == 'A'){
            if (value + 11 <= 21){
                value += 11;
            } else {
                value += 1;
            }
        } else {
            value += card.getValue();
        }
    }
    public ArrayList<Card> getCards() {
        return cards;
    }
    public PlayerType getPlayerType() {
        return playerType;
    }
    public int getValue() {
        return value;
    }
    public boolean isBust() {
        return value > 21;
    }
}
