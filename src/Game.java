//Crazy 8's by Julia Im
//02/19/2024
// Card game where each player is trying to get rid of all their cards and the first player with no cards left wins
// If the player can play an 8, then they get to choose the suit of the 8 placed
// In this game, the user will be playing against a computer
import java.util.ArrayList;
import java.util.Scanner;
public class Game {
    // Declare object
    private Player p1;
    private Player p2;
    private Deck deck;
    public GameViewer window;
    private Card topCard;
    private int rounds;
    private boolean currentPlayer;

    public Game() {
        // Initialize object
        window = new GameViewer(this);
        rounds = 0;
        // Boolean to represent whose turn it is (true is player 1 and false is comp)
        currentPlayer = true;
        // Create variables for each part of a card
        int[] point = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
        String[] rank = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        String[] suit = {"Spades", "Hearts", "Diamonds", "Clubs"};
        deck = new Deck(point, rank, suit);
        deck.shuffle();
        topCard = deck.deal();
        // Take in user name
        System.out.println("Enter your name: ");
        Scanner input = new Scanner(System.in);
        String name = input.nextLine();
        p1 = new Player(name);

        p2 = new Player("Computer");

        deck.shuffle();
        // Deal each player 7 cards
        for (int i = 0; i <= 6; i++) {
            p1.addCard(deck.deal());
            p2.addCard(deck.deal());

        }
    }

    public int getRounds() {
        return rounds;
    }

    public void printInstructions() {
        System.out.println("How to play Crazy 8s:" +
                "\nPlayers try to get rid of their cards by placing them on top of the flipped up card in the center." +
                "\nPlayers may do so if the card they want to get rid of is the same number or suit of the card flipped up." +
                "\n8s are wild and if a player plays an 8, they must state what suit the 8 will be for the next player.");
    }

    public void compTurn() {
        // Boolean to see if it can play a card or not
        boolean canPlay = false;
        rounds++;
        // Iterate through computer hand and see if any card matches with top card
        for (int i = 0; i < p2.getHand().size(); i++) {
            Card p2Card = p2.getHand().get(i);
            if (p2Card.getSuit() == topCard.getSuit() || p2Card.getRank() == topCard.getSuit()) {
                topCard = p2Card;
                p2.getHand().remove(i);
                System.out.println("\nComputer played " + topCard.toString() + "\nTop Card is now " + topCard.toString());
                canPlay = true;
                break;
            }
        }
        // If computer doesn't have any cards that match then takes a card
        if (!canPlay) {
            p2.addCard(deck.deal());
            // Check if there are still cards left in the deck after dealing
            if (!isTied()) {
                System.out.println("Computer drew a card. Computer now has " + p2.getHand().size() + " cards.");
            } else {
                System.out.println("You Tied! No more cards left.");
            }
        }

    }

    public void playGame() {
        Scanner input = new Scanner(System.in);
        deck.shuffle();
        printInstructions();
        System.out.println("Top card is " + topCard.toString());
        // Can use while loops
        while (!gameOver()) {
            // If its the users turn
            if (currentPlayer) {
                // Get rid of instructions and move on to the cards
                window.repaint();
                rounds++;
                boolean canPlay = false;
                System.out.println(p1.getName() + "'s cards \n");
                // Checks if they have a card to play
                for (int i = 0; i < p1.getHand().size(); i++) {
                    Card p1Card = p1.getHand().get(i);
                    // Prints the cards that the user has
                    System.out.println("Card " + (i + 1) + ": " + p1Card.toString());
                    if (p1Card.getSuit().equals(topCard.getSuit()) || p1Card.getRank().equals(topCard.getRank())) {
                        canPlay = true;
                    }
                }
                // Adds a card and skips their turn if they don't have a card to play
                if (!canPlay) {
                    p1.addCard(deck.deal());
                    if (!isTied()) {
                        System.out.println("Looks like you can't play a card! That means you must draw one!");
                        currentPlayer = false;
                        continue;
                    } else {
                        // End program once deck is used up.
                        System.out.println("You Tied! No more cards left.");
                        break;
                    }
                } else {
                    boolean canPlay2 = false;
                    while (!canPlay2) {
                        System.out.println("Which card would you like to play? (ex: 1): ");
                        int cardNum = input.nextInt();
                        input.nextLine();
                        cardNum = cardNum - 1;

                        // Check if the card is an 8 or not
                        if (check8(cardNum)) {
                            canPlay2 = true;
                        }
                        // If it's a regular card, check if it matches the top card
                        else if (p1.getHand().get(cardNum).getSuit().equals(topCard.getSuit()) || p1.getHand().get(cardNum).getRank().equals(topCard.getRank())) {
                            // Make the top card the player's card if it matches
                            topCard = p1.getHand().get(cardNum);
                            // Take out of player's hand
                            p1.getHand().remove(cardNum);
                            System.out.println("Top Card is now " + topCard.toString());
                            canPlay2 = true;
                            if (!gameOver()) {
                                currentPlayer = false;
                            }
                        } else {
                            // Reprompt if card isn't playable
                            System.out.println("You can't play that card. Try again!");
                        }

                    }
                }
            }
            // Computers turn
            if (!currentPlayer && !gameOver()) {
                compTurn();
                // Checks if game is over so it doesn't switch turns display the wrong winner
                if (!gameOver()) {
                    currentPlayer = true;
                }
            }
        }
        // Once game is over, repaint to allow new image to be shown
        window.repaint();
        // If the players hand is empty then game is over and they've won
        if (gameOver() && isCurrentPlayer() && !isTied()) {
            System.out.println(p1.getName() + " wins!");
        }
        if (gameOver() && !isCurrentPlayer() && !isTied()) {
            System.out.println("Computer wins!");
        }
    }

    public boolean check8(int cardNum) {
        Scanner input = new Scanner(System.in);
        //Checks to see if card they chose is an 8
        if (p1.getHand().get(cardNum).getRank().equals("8") && (p1.getHand().get(cardNum).getSuit().equals(topCard.getSuit()) || topCard.getRank().equals("8"))) {
            System.out.println("Please input a suit (ex: spades)");
            String suitInput = input.nextLine();
            while (!suitInput.equalsIgnoreCase("Hearts") && !suitInput.equalsIgnoreCase("Diamonds") && !suitInput.equalsIgnoreCase("Clubs") && !suitInput.equalsIgnoreCase("Spades")){
                // If it doesn't exist, reprompt the user
                System.out.println("That's not a suit! Please input either Spades, Diamonds, Clubs, or Hearts");
                suitInput = input.nextLine();
            }
                // If the suit exists set the top card equal to that and remove a card from p1 deck
                if (suitInput.equalsIgnoreCase("Hearts")) {
                    System.out.println("Top Card is now 8 of Hearts");
                    // Set top card equal to new card
                    topCard = deck.getEightofHearts();
                    // Remove old card
                    p1.getHand().remove(cardNum);
                    return true;
                } else if (suitInput.equalsIgnoreCase("Diamonds")) {
                    System.out.println("Top Card is now 8 of Diamonds");
                    topCard = deck.getEightOfDiamonds();
                    p1.getHand().remove(cardNum);
                    return true;
                } else if (suitInput.equalsIgnoreCase("Clubs")) {
                    System.out.println("Top Card is now 8 of Clubs");
                    topCard = deck.getEightOfClubs();
                    p1.getHand().remove(cardNum);
                    return true;
                } else if (suitInput.equalsIgnoreCase("Spades")) {
                    System.out.println("Top Card is now 8 of Spades");
                    topCard = deck.getEightOfSpades();
                    p1.getHand().remove(cardNum);
                    return true;
                }

            // Only change players if the game isn't over so it doesn't return the wrong winner.
            if (!gameOver()) {
                currentPlayer = false;
            }
        }
        // If not an 8, return false.
        return false;
    }

    public boolean isCurrentPlayer() {
        return currentPlayer;
    }

    public boolean isTied() {
        // If no more cards left to play then they have tied
        if (deck.isEmpty()) {
            return true;
        }
        return false;
    }
    public Player getP1() {
        return p1;
    }

    public Player getP2() {
        return p2;
    }

    public Card getTopCard() {
        return topCard;
    }

    // Check if hand is empty to see if game is done
    public boolean gameOver() {
        if (p1.getHand().isEmpty()) {
            return true;
        }
        else if (p2.getHand().isEmpty()) {
            return true;
        }
        else if (isTied()) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Game newGame = new Game();
        newGame.playGame();
    }
}
