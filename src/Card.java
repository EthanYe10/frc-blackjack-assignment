public class Card {
    private String suit;
    private char rank;
    private int value;

    public Card(String suit, char rank) {
        this.suit = suit;
        this.rank = rank;
        this.value = calculateValue();
    }

    public int calculateValue(){
        switch (this.rank){
            case '2':
                return 2;
            case '3':
                return 3;
            case '4':
                return 4;
            case '5':
                return 5;
            case '6':
                return 6;
            case '7':
                return 7;
            case '8':
                return 8;
            case '9':
                return 9;
            case '0': // represents 10
            case 'J':
            case 'Q':
            case 'K':
                return 10;
            case 'A':
                return 0; // handle value of Ace in Hand class
            default:
                return 0;
        }
    }

    public String getSuit() {
        return suit;
    }
    public char getRank() {
        return rank;
    }
    public int getValue() {
        return value;
    }
    @Override
    public String toString(){
        String _rank;
        if (rank == '0'){
            _rank = "10";
        } else {
            _rank = String.valueOf(rank);
        }
        return _rank + " of " + suit;
    }
}