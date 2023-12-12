import java.util.ArrayList;
import java.lang.Math;
public class Deck {
    private ArrayList<Card> cards;
    //Declared a primitive variable
    private int cardsLeft;

    public Deck(int[] points, String[] ranks, String[] suits) {
        cards = new ArrayList<Card>();
        for (String rank : ranks) {
            for (int point : points) {
                for (String suit: suits) {
                    Card card = new Card(rank, suit, point) ;
                    cards.add(card);
                    cardsLeft+=1;
                }
            }
        }
        this.shuffle();

    }
    public boolean isEmpty() {
        if (cardsLeft == 0) {
            return true;
        }
        return false;
    }

    public int getCardsLeft() {
        return cardsLeft;
    }

    public Card deal() {
        if (isEmpty()) {
            return null;
        }
        cardsLeft--;
        return cards.get(cardsLeft);
    }

    public void shuffle() {
        for (int i = cards.size() - 1; i > 0; i--) {
            //can use math class including multiplication and math.random
            int r = (int) (Math.random() * i);
            Card temp = cards.get(i);
            cards.set(i, cards.get(r));
            cards.set(r, temp);
        }
    }
}
