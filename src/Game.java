import java.util.Scanner;
public class Game {
    private Player p1;
    private Player p2;
    private Deck deck;

    public Game () {
        int[] point = {1};
        String[] rank = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        String[] suit = {"Spades", "Hearts", "Diamonds", "Clubs"};
        deck = new Deck(point, rank, suit);

        Scanner input = new Scanner(System.in);
        String name = input.nextLine();
        p1 = new Player(name);

        String name2 = input.nextLine();
        p2 = new Player(name2);

        for (int i = 0; i <= 7; i++) {
            p1.addCard(deck.deal());
            p2.addCard(deck.deal());
        }
    }

    public String printInstructions() {
        return "Players try to get rid of their cards by placing them on top of the flipped up card in the center. Players may do so if the card they want to get rid of is the same number or suit of the card flipped up. 8s are wild and if a player plays an 8, they must state what suit the 8 will be for the next player.";
    }

    public void playGame() {
        printInstructions();

    }

    public static void main(String[] args) {

    }
}
