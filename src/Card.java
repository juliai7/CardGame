import java.util.Scanner;
public class Card {
    private String suit;
    private String rank;
    // Declare primitive variable
    private int point;

    public Card (String otherRank, String otherSuit, int otherPoint) {
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
}
