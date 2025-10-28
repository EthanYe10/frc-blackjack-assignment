import java.util.ArrayList;

public class Deck {
    private ArrayList<Card> cards;
    private String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
    private char[] ranks = {'2', '3', '4', '5', '6', '7', '8', '9', '0', 'J', 'Q', 'K', 'A'};
    public Deck() {
        this.cards = new ArrayList<>();
        for (String suit : suits) {
            for (char rank : ranks) {
                cards.add(new Card(suit, rank));
            }
        }
        this.shuffle();
    }
    public ArrayList<Card> getCards() {
        return cards;
    }
    public void shuffle() {
        java.util.Collections.shuffle(cards);
    }

    public void skipCard(){
        if (!cards.isEmpty()){
            cards.add(cards.remove(0));
        }
    }

    public Card peekCard(){
        if (cards.isEmpty()){
            return null;
        }
        return cards.get(0);
    }

    public Card deal(){
        if (cards.isEmpty()){
            return null;
        }
        return cards.remove(0);
    }

    public void reset(){
        this.cards.clear();
        for (String suit : suits) {
            for (char rank : ranks) {
                cards.add(new Card(suit, rank));
            }
        }
        this.shuffle();
    }
}
