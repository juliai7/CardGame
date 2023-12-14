import java.util.Scanner;
//class containing instance variables, constructors, and methods
public class Game {
    //declare object
    private Player p1;
    private Player p2;
    private Deck deck;

    private Card topCard;

    public Game () {
        //initialize object
        int[] point = {1};
        String[] rank = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        String[] suit = {"Spades", "Hearts", "Diamonds", "Clubs"};
        deck = new Deck(point, rank, suit);
        deck.shuffle();
        topCard = deck.deal();

        System.out.println("Enter your name: ");
        Scanner input = new Scanner(System.in);
        //read in user input
        String name = input.nextLine();
        p1 = new Player(name);

        p2 = new Player("Computer");
        
        deck.shuffle();
        for (int i = 0; i <= 6; i++) {
            p1.addCard(deck.deal());
            p2.addCard(deck.deal());
        }
    }

    public void printInstructions() {
        System.out.println("How to play Crazy 8s:\nPlayers try to get rid of their cards by placing them on top of the flipped up card in the center.\nPlayers may do so if the card they want to get rid of is the same number or suit of the card flipped up.\n8s are wild and if a player plays an 8, they must state what suit the 8 will be for the next player.\n");
    }

    public void compTurn() {
        boolean canPlay = false;
        for (int i = 0; i < p2.getHand().size(); i++) {
            if (p2.getHand().get(i).getSuit() == topCard.getSuit() || p2.getHand().get(i).getRank() == topCard.getSuit()) {
                topCard = p2.getHand().get(i);
                p2.getHand().remove(i);
                System.out.println("\nComputer played " + topCard.toString() + "\nTop Card is now " + topCard.toString());
                //assign primitive variable
                canPlay = true;
                break;
            }

        }
        if (!canPlay) {
            System.out.println("Computer drew a card. Computer now has " + (p2.getHand().size() + 1) + " cards.");
            //can use arraylist methods
            p2.addCard(deck.deal());
        }
    }
    public void playGame() {
        Scanner input = new Scanner(System.in);
        deck.shuffle();
        printInstructions();
        System.out.println("Top card is " + topCard.toString());
        boolean currentPlayer = true;
        //can use while loops
        while (!gameOver()) {
            while (currentPlayer) {
                //initizlized primitive variable
                boolean canPlay = false;
                System.out.println(p1.getName() + "'s cards \n");
                //can use for loops and traverse arraylist
                for (int i = 0; i < p1.getHand().size(); i++) {
                    System.out.println("Card " + (i + 1) + ": " + p1.getHand().get(i).toString());
                    if (p1.getHand().get(i).getSuit().equals(topCard.getSuit()) || p1.getHand().get(i).getRank().equals(topCard.getRank())) {
                        canPlay = true;
                    }
                }
                //can use if loops
                if (!canPlay) {
                    System.out.println("Looks like you can't play a card! That means you must draw one!");
                    //modify arraylist
                    p1.addCard(deck.deal());
                    currentPlayer = false;
                }
                else if (canPlay) {
                    System.out.println("Which card would you like to play? (ex: 1): ");
                    int cardNum = input.nextInt();
                    cardNum = cardNum - 1;
                    boolean canPlay2 = false;
                    while (!canPlay2) {
                        if (p1.getHand().get(cardNum).getRank().equals("8") && p1.getHand().get(cardNum).getSuit().equals(topCard.getSuit())) {
                            System.out.println("Please input a suit (ex: spades)");
                            String suitInput = input.nextLine();
                            boolean existing = false;
                            if (suitInput.equalsIgnoreCase("Hearts") || suitInput.equalsIgnoreCase("Diamonds") || suitInput.equalsIgnoreCase("Clubs") || suitInput.equalsIgnoreCase("Spades")) {
                                existing = true;
                            }
                            if (suitInput.equalsIgnoreCase("Hearts")){
                                System.out.println("Top Card is now 8 of Hearts");
                                Card newTopCard = new Card("8", "Hearts", 1);
                                topCard = newTopCard;
                            }
                            else if (suitInput.equalsIgnoreCase("Diamonds")){
                                System.out.println("Top Card is now 8 of Diamonds");
                                Card newTopCard = new Card("8", "Diamonds", 1);
                                topCard = newTopCard;
                            }
                            else if (suitInput.equalsIgnoreCase("Clubs")){
                                System.out.println("Top Card is now 8 of Clubs");
                                Card newTopCard = new Card("8", "Clubs", 1);
                                topCard = newTopCard;
                            }
                            else if (suitInput.equalsIgnoreCase("Spades")){
                                System.out.println("Top Card is now 8 of Spades");
                                Card newTopCard = new Card("8", "Spades", 1);
                                topCard = newTopCard;
                            }
                            while (!existing) {
                                System.out.println("That's not a suit! Please input either Spades, Diamonds, Clubs, or Hearts");
                                suitInput = input.nextLine();
                                break;
                            }
                        }
                        else if (p1.getHand().get(cardNum).getSuit().equals(topCard.getSuit()) || p1.getHand().get(cardNum).getRank().equals(topCard.getRank())) {
                            topCard = p1.getHand().get(cardNum);
                            p1.getHand().remove(cardNum);
                            System.out.println("Top Card is now " + topCard.toString());
                            canPlay2 = true;
                            currentPlayer = false;
                            break;
                        } else {
                            System.out.println("You can't play that card. Try again!");
                            canPlay2 = false;
                            break;
                        }

                    }
                }
            }
            while (!currentPlayer && !gameOver()) {
                compTurn();
                currentPlayer = true;
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
