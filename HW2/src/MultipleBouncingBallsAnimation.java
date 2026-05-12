import java.awt.Color;
import java.util.Random;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import src.Ball;

/**
 * A class to animate multiple bouncing balls within a game frame.
 */
public class MultipleBouncingBallsAnimation {

    /**
     * The main method to run the animation.
     *
     * @param args - an array of strings representing the sizes of the balls to be Created.
     */
    public static void main(String[] args) {
        GUI gui = new GUI("Task 3", 800, 600);
        GameFrame frame = new GameFrame(0, 0, 800, 600, true);
        Random rand = new Random();
        Ball[] balls = new Ball[args.length];

        // Create balls based on the input sizes and assign them to the appropriate areas.
        for (int i = 0; i < args.length; i++) {
            int size = (int) Double.parseDouble(args[i]);
            balls[i] = Ball.createBallInFrame(size, 0, 800, 0, 600, Color.GREEN, rand);
        }

        // Animation loop to move the balls and check for collisions with the frame edges.
        Sleeper sleeper = new Sleeper();
        while (true) {
            DrawSurface d = gui.getDrawSurface();
            for (Ball b : balls) {
                b.moveOneStep();
                frame.checkCollision(b);
                b.drawOn(d);
            }
            gui.show(d);
            sleeper.sleepFor(50);
        }
    }
}