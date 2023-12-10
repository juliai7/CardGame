import java.util.Scanner;
public class Game {
    private Player p1;
    private Player p2;
    private Deck deck;

    private Card topCard;

    public Game () {
        int[] point = {1};
        String[] rank = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        String[] suit = {"Spades", "Hearts", "Diamonds", "Clubs"};
        deck = new Deck(point, rank, suit);
        topCard = deck.deal();

        System.out.println("Enter your name: ");
        Scanner input = new Scanner(System.in);
        String name = input.nextLine();
        p1 = new Player(name);

        p2 = new Player("Computer");

        for (int i = 0; i <= 6; i++) {
            deck.shuffle();
            p1.addCard(deck.deal());
            p2.addCard(deck.deal());
        }
    }

    public void printInstructions() {
        System.out.println("Players try to get rid of their cards by placing them on top of the flipped up card in the center. Players may do so if the card they want to get rid of is the same number or suit of the card flipped up. 8s are wild and if a player plays an 8, they must state what suit the 8 will be for the next player.");
    }

    public void compTurn() {
        boolean canPlay = false;
        for (int i = 0; i < p2.getHand().size(); i++) {
            if (p2.getHand().get(i).getSuit() == topCard.getSuit() || p2.getHand().get(i).getRank() == topCard.getSuit()) {
                topCard = p2.getHand().get(i);
                p2.getHand().remove(i);
                System.out.println("\nComputer played " + topCard.toString() + "\nTop Card is now " + topCard.toString());
                canPlay = true;
            }

        }
        if (!canPlay) {
            System.out.println("Computer drew a card. Computer now has " + (p2.getHand().size() + 1) + " cards.");
            p2.addCard(deck.deal());
        }
    }
    public void playGame() {
        Scanner input = new Scanner(System.in);
        deck.shuffle();
        printInstructions();
        System.out.println("Top card is " + topCard.toString());
        while (!gameOver()) {
            boolean canPlay = false;
            System.out.println(p1.getName() + "'s cards \n");
            
            for (int i = 0; i < p1.getHand().size(); i++) {
                System.out.println("Card " + (i + 1) + ": " + p1.getHand().get(i).toString());
                if (p1.getHand().get(i).getSuit().equals(topCard.getSuit()) || p1.getHand().get(i).getRank().equals(topCard.getRank())) {
                    canPlay = true;
                }
            }
            if (!canPlay) {
                System.out.println("Looks like you can't play a card! That means you must draw one!");
                p1.addCard(deck.deal());
                compTurn();
            }
            if (canPlay) {
                System.out.println("Which card would you like to play? (ex: 1): ");
                int cardNum = input.nextInt();
                cardNum = cardNum - 1;
                boolean canPlay2 = false;
                while (!canPlay2) {
                    if (p1.getHand().get(cardNum).getSuit().equals(topCard.getSuit()) || p1.getHand().get(cardNum).getRank().equals(topCard.getRank())) {
                        topCard = p1.getHand().get(cardNum);
                        p1.getHand().remove(cardNum);
                        System.out.println("Top Card is now " + topCard.toString());
                        canPlay2 = true;
                        compTurn();
                    }
                    else {
                        System.out.println("You can't play that card. Try again!");
                        break;
                    }
                }
            }
        }
            gameOver();
        }


    public boolean gameOver() {
        if (p1.getHand().isEmpty()) {
            System.out.println(p1.getName() + " wins!");
            return true;
        }
        else if (p2.getHand().isEmpty()) {
            System.out.println("Computer wins!");
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Game newGame = new Game();
        newGame.playGame();
    }
}
