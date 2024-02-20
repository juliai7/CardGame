import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.lang.Math;
public class Deck {
    //declare arraylist
    private ArrayList<Card> cards;
    //Declared a primitive variable
    public GameViewer window;
    public Image[] cardImage;
    private int cardsLeft;
    private Card eightOfSpades;
    private Card eightofHearts;
    private Card eightOfDiamonds;
    private Card eightOfClubs;



    public Deck(int[] points, String[] ranks, String[] suits) {
        // Initialize arraylist
        cards = new ArrayList<Card>();
        // Counter to iterate through card images
        int j = 1;
        for (int i = 0; i < ranks.length; i++) {
            for (String suit: suits) {
                String rank = ranks[i];
                Image cardImage = new ImageIcon("Resources/" + j + ".png").getImage();
                // Assign each card image to the correct card
                Card card = new Card(rank,suit, points[i], window, cardImage) ;
                if (card.getRank().equals("8")) {
                    // Create unique cards for the eights so can later check if the card is an eight or not in the Game class
                    if (card.getSuit().equals("Spades")) {
                        eightOfSpades = card;
                    }
                    else if (card.getSuit().equals("Hearts")) {
                        eightofHearts = card;
                    }
                    else if (card.getSuit().equals("Diamonds")) {
                        eightOfDiamonds = card;
                    }
                    else {
                        eightOfClubs = card;
                    }
                }
                // Add the newly created card to the deck
                cards.add(card);
                cardsLeft+=1;
                j++;
            }
        }
        this.shuffle();

    }
    public Card getEightOfSpades() {
        return eightOfSpades;
    }

    public Card getEightofHearts() {
        return eightofHearts;
    }

    public Card getEightOfDiamonds() {
        return eightOfDiamonds;
    }

    public Card getEightOfClubs() {
        return eightOfClubs;
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
        // If the deck isn't empty then return the card on top
        if (isEmpty()) {
            return null;
        }
        cardsLeft--;
        return cards.get(cardsLeft);
    }

    public void shuffle() {
        for (int i = cards.size() - 1; i > 0; i--) {
            // Swap card with a random card in the deck and repeat
            int r = (int) (Math.random() * i);
            Card temp = cards.get(i);
            cards.set(i, cards.get(r));
            cards.set(r, temp);
        }
    }
}
