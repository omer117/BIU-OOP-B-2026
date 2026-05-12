import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.Sleeper;
import src.Ball;

import java.awt.Color;
import java.util.Random;

/**
 * MultipleFramesBouncingBallsAnimation class - creates an animation of multiple
 * bouncing balls in different frames with obstacles.
 */
public class MultipleFramesBouncingBallsAnimation {

    /**
     * The main method to run the animation.
     *
     * @param args the command line arguments representing the sizes of the balls.
     *             The first half of the arguments are for balls in the gray frame,
     *             and the second half are for balls in the main screen.
     */
    public static void main(String[] args) {
        GUI gui = new GUI("Task 4", GameFrame.DEFAULTMAXWIDTH, GameFrame.DEFAULTMAXHEIGHT);
        Sleeper sleeper = new Sleeper();
        Random rand = new Random();

        GameFrame mainScreen = new GameFrame(GameFrame.DEFAULTMINX, GameFrame.DEFAULTMINY, GameFrame.DEFAULTMAXWIDTH,
                GameFrame.DEFAULTMAXHEIGHT, true);
        GameFrame grayFrame = new GameFrame(50, 50, 450, 450, true);
        GameFrame grayAsObstacle = new GameFrame(50, 50, 450, 450, false);
        GameFrame yellowObstacle = new GameFrame(450, 450, 150, 150, false);

        Ball[] balls = new Ball[args.length];
        int mid = args.length / 2;

        // Create hallf of the balls based on the input sizes and assign them to the
        // appropriate areas.
        for (int i = 0; i < mid; i++) {
            int size = (int) Double.parseDouble(args[i]);
            Ball b;
            do {
                b = Ball.createBallInFrame(size, 50, 500, 50, 500, Color.RED, rand);
            } while (b.getX() + b.getSize() > 450 && b.getY() + b.getSize() > 450);
            balls[i] = b;
        }
        // Create the second half of the balls based on the input sizes and assign them
        // to the appropriate areas, ensuring they do not start within the gray frame or
        // yellow obstacle.
        for (int i = mid; i < args.length; i++) {
            int size = (int) Double.parseDouble(args[i]);
            Ball b;
            boolean invalid;
            do {
                b = Ball.createBallInFrame(size, 0, 800, 0, 600, Color.BLUE, rand);
                double x = b.getX();
                double y = b.getY();
                int radius = b.getSize();
                boolean inGray = (x + radius > 50 && x - radius < 500 && y + radius > 50 && y - radius < 500);
                boolean inYellow = (x + radius > 450 && x - radius < 600 && y + radius > 450 && y - radius < 600);
                invalid = inGray || inYellow;
            } while (invalid);
            balls[i] = b;
        }

        while (true) {
            DrawSurface d = gui.getDrawSurface();
            d.setColor(Color.GRAY);
            d.fillRectangle(50, 50, 450, 450);
            d.setColor(Color.YELLOW);
            d.fillRectangle(450, 450, 150, 150);

            for (int i = 0; i < balls.length; i++) {
                balls[i].moveOneStep();
                if (i < mid) {
                    grayFrame.checkCollision(balls[i]);
                    yellowObstacle.checkCollision(balls[i]);
                } else {
                    mainScreen.checkCollision(balls[i]);
                    grayAsObstacle.checkCollision(balls[i]);
                    yellowObstacle.checkCollision(balls[i]);
                }
                balls[i].drawOn(d);
            }
            gui.show(d);
            sleeper.sleepFor(50);
        }
    }
}