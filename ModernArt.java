package assignments.basics;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

import static java.awt.Color.*;

/**
 * Exercising some graphics primitives from Java 2D
 *
 * Tip: Use some loops in the paint method.
 */
public class ModernArt extends JPanel {

    public static void main(String[] args) {
        new ModernArt().program();
    }

    void program() {
        // All we need to do is call this.
        initGraphics();
    }

    // This is our standard method to paint in graphical programs
    // This method is automagically called by Java. We don't need to call it
    public void paint(Graphics g) {
        // Do some default painting (this statement always first)
        super.paint(g);

        // g2 is your painting toolbox (this statement always second )
        Graphics2D g2 = (Graphics2D) g;

        // Use graphic primitives from toolbox to draw, you try ....!
        Color[] colours = {RED, MAGENTA, CYAN, BLACK, BLUE, DARK_GRAY, GRAY, GREEN, LIGHT_GRAY, ORANGE, PINK, YELLOW};
        Random random = new Random();

        for (int i = 0; i < 10; i++){
            for (int j = 10; j > 0; j--){
                g2.setColor(colours[random.nextInt(3)]);
                g2.drawLine(10 +i*35, 20*i, j*10 + 10, j*20);
            }
        }


        for (int i = 0; i < 50; i++){
            g2.setColor(colours[random.nextInt(colours.length)]);
            g2.drawLine(200, 200, 200 + (int)(250*Math.sin(i)), 200 + (int)(250*Math.cos(i + 250*(int)(Math.round(Math.PI/2)))));
            g2.drawOval(0, 0, 200 + (int)(250*Math.sin(i)), 200 + (int)(250*Math.cos(i + 250*(int)(Math.round(Math.PI/2)))));
        }


    }

    // ------- Nothing to below ------------------------------------

    // This is our standard method to start up the graphics
    void initGraphics() {
        int width = 400;
        int height = 400;
        setPreferredSize(new Dimension(width, height));
        JFrame window = new JFrame();
        window.setTitle("Modern art");
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.add(this);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}
