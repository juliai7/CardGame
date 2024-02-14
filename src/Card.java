import java.awt.*;
import java.util.Scanner;
public class Card {
    private String suit;
    private String rank;
    // Declare primitive variable
    private int point;
    public GameViewer window;



    public Card (String otherRank, String otherSuit, int otherPoint, GameViewer window) {
        suit = otherSuit;
        rank = otherRank;
        point = otherPoint;
    }
    // Can write getters and setters with the correct return type
    public String getSuit() {
        return suit;
    }
    public String getRank() {
        return rank;
    }
    public int getPoint() {
        return point;
    }
    public void setSuit(String suit) {
        this.suit = suit;
    }
    public void setRank(String rank) {
        this.rank = rank;
    }
    public void setPoint(int point) {
        this.point = point;
    }
    public String toString() {
        return rank + " of " + suit;
    }

    public void draw (Graphics g) {
//        for (int i = 0; i < 53; i++) {
//            g.drawImage(window.getCardImages()[i], 100, 100, 100, 100, window);
//        }
    }
}
