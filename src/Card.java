import java.awt.*;
import java.util.Scanner;
public class Card {
    private String suit;
    private String rank;
    private int point;
    private GameViewer window;

    private Image image;



    public Card (String otherRank, String otherSuit, int otherPoint, GameViewer window, Image image) {
        suit = otherSuit;
        rank = otherRank;
        point = otherPoint;
        this.window = window;
        this.image = image;
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

    public void draw (Graphics g, int x, int y) {
        // Draws card images and takes in coordinates to where its going to be placed
        g.drawImage(image, x, y, 100, 150, window);

    }
}
