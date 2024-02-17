import javax.swing.*;
import java.awt.*;

public class GameViewer extends JFrame {
    private Image[] cardImages;
    private final int WINDOW_WIDTH = 1000;
    private final int WINDOW_HEIGHT = 800;
    private Game game;

    public GameViewer(Game game) {
        this.game = game;
        cardImages = new Image[53];
        for (int i = 0; i < 52; i++) {
            cardImages[i] = new ImageIcon("Resources/" + (i + 1) + ".png").getImage();
        }
        cardImages[52] = new ImageIcon("Resources/back.png").getImage();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("TicTacToe");
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);

    }

    public Image[] getCardImages() {
        return cardImages;
    }
    public void paint (Graphics g) {
        if (game.getRounds() == 0) {
            g.setFont(new Font("Serif", Font.PLAIN, 100));
            String s = "CRAZY 8'S";
            for (int i = 0; i < 9; i++) {
                String letter = String.valueOf(s.charAt(i));
                Color c = new Color((int) (Math.random() * 255), (int) (Math.random() * 255),(int) (Math.random() * 255));
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
        else if (!game.gameOver()) {
            g.setColor(Color.white);
            g.drawRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
            g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
            g.drawImage(cardImages[52], 525, 325, 100, 150, this);

            for (int i = 0; i < game.getP1().getHand().size(); i++) {
                game.getP1().getHand().get(i).draw(g, 100 + (i * 75), 600);
            }
            int p2HandSize = game.getP2().getHand().size();
            for (int i = 0; i < p2HandSize; i++) {
                g.drawImage(cardImages[52], 300 + (i * 25), 150, 100, 150, this);

                }
            }
            game.getTopCard().draw(g, 400, 325);
        }
        else {
            if (!game.isCurrentPlayer()) {

        }


    }
}
