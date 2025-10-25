public class Person {
    protected int wins;
    protected String name;
    protected Hand hand;

    public Person(String name) {
        this.name = name;
        this.wins = 0;
        resetHand();
    }
    public String getName() {
        return name;
    }
    public int getWins() {
        return wins;
    }
    public void incrementWins() {
        this.wins += 1;
    }
    public Hand getHand() {
        return hand;
    }
    public void resetHand() {
        if (name.equals("Dealer")){
            this.hand = new Hand(PlayerType.DEALER);
        } else {
            this.hand = new Hand(PlayerType.PLAYER);
        }
    }
    public void drawCard(Card card){
        this.hand.addCard(card);
    }

    public void showHand(){
        System.out.println(this.name + "'s hand:");
        for (Card card : this.hand.getCards()){
            System.out.println(card);
        }
        System.out.println("Total value: " + this.hand.getValue());
        System.out.println();
    }
    public void clear(){
        resetHand();
    }
}
