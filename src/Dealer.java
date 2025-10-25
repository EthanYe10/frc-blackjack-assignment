public class Dealer extends Person {
    public Dealer() {
        super("Dealer");
    }
    public boolean checkAction(){
        return this.hand.getValue() < 17;
    }

    public void showInitialHand(){
        System.out.println(this.name + "'s hand:");
        System.out.println(this.hand.getCards().get(0));
        System.out.println("Hidden card");
        System.out.println();
    }
}
