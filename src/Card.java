import java.util.Scanner;
public class Card {
    private String suit;
    private char rank;
    private int point;

    public Card (String otherSuit, char otherRank, int otherPoint) {
        suit = otherSuit;
        rank = otherRank;
        point = otherPoint;
    }
    public String getSuit() {
        return suit;
    }
    public char getRank() {
        return rank;
    }
    public int getPoint() {
        return point;
    }
    public void setSuit(String suit) {
        this.suit = suit;
    }
    public void setRank(char rank) {
        this.rank = rank;
    }
    public void setPoint(int point) {
        this.point = point;
    }
    public String toString() {
        return rank " of " suit;
    }
}
