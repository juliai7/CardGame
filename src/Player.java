import java.util.ArrayList;
public class Player {
    private ArrayList<Card> hand;
    private int points;
    private ArrayList<Card> cards;
    private String name;

    public Player(String inputname) {
        //assign object
        name = inputname;
        points = 0;
        hand = new ArrayList<Card>();
    }
    public Player(String inputname, ArrayList<Card>handOfCards) {
        name = inputname;
        cards = handOfCards;
        points = 0;
        hand = new ArrayList<Card>();
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public void setHand(ArrayList<Card> hand) {
        this.hand = hand;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addPoints(int numPoints) {
        points += numPoints;
    }

    public void addCard (Card card) {
        hand.add(card);
    }
    public String toString() {
        return name + " has " + points + " points \n" + name + "'s cards: " + hand;
    }
}
