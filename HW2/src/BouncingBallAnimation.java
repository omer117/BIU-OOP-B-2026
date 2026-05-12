import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.Sleeper;
import src.Ball;
import src.Point;

import java.awt.Color;

/**
 * BouncingBallAnimation class - creates a single bouncing ball animation
 * using the GameFrame logic.
 */
public class BouncingBallAnimation {

    /**
     * Draws the animation of a single bouncing ball.
     *
     * @param start the starting point of the ball.
     * @param dx    the horizontal velocity.
     * @param dy    the vertical velocity.
     */
    private static void drawAnimation(Point start, double dx, double dy) {
        GUI gui = new GUI("Bouncing Ball", GameFrame.DEFAULTMAXWIDTH, GameFrame.DEFAULTMAXHEIGHT);
        Sleeper sleeper = new Sleeper();

        Ball ball = new Ball(start, 30, Color.BLACK);
        ball.setVelocity(dx, dy);

        GameFrame frame = new GameFrame(GameFrame.DEFAULTMINX, GameFrame.DEFAULTMINY, GameFrame.DEFAULTMAXWIDTH,
                GameFrame.DEFAULTMAXHEIGHT, true);

        while (true) {
            ball.moveOneStep();
            frame.checkCollision(ball);

            DrawSurface d = gui.getDrawSurface();
            ball.drawOn(d);
            gui.show(d);
            sleeper.sleepFor(50);
        }
    }

    /**
     * The main method to run the animation.
     *
     * @param args (x, y, dx, dy)
     */
    public static void main(String[] args) {
        double x = Double.parseDouble(args[0]);
        double y = Double.parseDouble(args[1]);
        double dx = Double.parseDouble(args[2]);
        double dy = Double.parseDouble(args[3]);

        drawAnimation(new Point(x, y), dx, dy);
    }
}