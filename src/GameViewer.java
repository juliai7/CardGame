import javax.swing.*;
import java.awt.*;

public class GameViewer extends JFrame {
    private Image[] cardImages;
    private final int WINDOW_WIDTH = 1000;
    private final int WINDOW_HEIGHT = 800;
    // Game instance variable so the front end has access to information in the back end
    private Game game;
    private Image[] result;

    public GameViewer(Game game) {
        this.game = game;
        cardImages = new Image[53];
        for (int i = 0; i < 52; i++) {
            // Initialize each card in the front end
            cardImages[i] = new ImageIcon("Resources/" + (i + 1) + ".png").getImage();
        }
        cardImages[52] = new ImageIcon("Resources/back.png").getImage();
        result = new Image[3];
        // Initialize win, lose, or tie images in the front end
        result[0] = new ImageIcon("Resources/youWin.png").getImage();
        result[1] = new ImageIcon("Resources/youLose.png").getImage();
        result[2] = new ImageIcon("Resources/tie.png").getImage();


        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("TicTacToe");
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);

    }

    public Image[] getCardImages() {
        return cardImages;
    }

    public void paint(Graphics g) {
        // If the game hasn't started yet
        if (game.getRounds() == 0) {
            // Then print instructions and title
            g.setFont(new Font("Serif", Font.PLAIN, 100));
            String s = "CRAZY 8'S";
            for (int i = 0; i < 9; i++) {
                String letter = String.valueOf(s.charAt(i));
                // Gets random colors for each letter in the title
                Color c = new Color((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255));
                g.setColor(c);
                g.drawString(letter, 150 + (i * 75), 150);
            }

            g.setColor(Color.black);
            g.setFont(new Font("Serif", Font.PLAIN, 15));
            g.drawString("How to play Crazy 8s:", 100, 300);
            g.drawString("Players try to get rid of their cards by placing them on top of the flipped up card in the center.", 100, 350);
            g.drawString("Players may do so if the card they want to get rid of is the same number or suit of the card flipped up.", 100, 400);
            g.drawString("8s are wild and if a player plays an 8, they must state what suit the 8 will be for the next player.", 100, 450);
        }
        // If the game is still going
        else if (!game.gameOver()) {
            g.setColor(Color.white);
            // Reset window
            g.drawRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
            g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
            // Draw the "deck"
            g.drawImage(cardImages[52], 525, 325, 100, 150, this);

            for (int i = 0; i < game.getP1().getHand().size(); i++) {
                // Draw each card in the player's hand
                game.getP1().getHand().get(i).draw(g, 100 + (i * 40), 600);
            }
            for (int i = 0; i < game.getP2().getHand().size(); i++) {
                // Draw the number of cards that the computer has
                g.drawImage(cardImages[52], 300 + (i * 25), 50, 100, 150, this);

            }
            // Draws the top card on the pile
            game.getTopCard().draw(g, 400, 325);
        }
        // Once someone has won, lost, or tied
        else if (game.gameOver()) {
            g.setColor(Color.white);
            // Reset window
            g.drawRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
            g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
            // If the game is tied, display the tied image
            if (game.isTied()) {
                g.drawImage(result[2], 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT, this);
            }
            // If the player won, display the you won image
            else if (game.isCurrentPlayer()) {
                g.drawImage(result[0], 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT, this);
            }
            // If the player lost, display the you lost image
            else if (!game.isCurrentPlayer()){
                g.drawImage(result[1], 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT, this);
            }
        }
    }
}
