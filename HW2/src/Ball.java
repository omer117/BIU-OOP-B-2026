import biuoop.DrawSurface;
import java.awt.Color;
import java.util.Random;

/**
 * The Ball class represents a ball with a center point, radius, color, and
 * velocity. It provides methods to draw the ball on a surface, move it
 * according to its velocity, and create a random ball within specified frame
 * boundaries.
 */
public class Ball {
    private Point center;
    private int r;
    private Color color;
    private Velocity velocity;

    /**
     * Constructs a Ball with the specified center, radius, and color. The initial
     * velocity is set to (0, 0).
     *
     * @param center the center point of the ball.
     * @param r      the radius of the ball.
     * @param color  the color of the ball.
     */
    public Ball(Point center, int r, Color color) {
        this.center = center;
        this.r = r;
        this.color = color;
        this.velocity = new Velocity(0, 0);
    }

    /**
     * Returns the x-coordinate of the ball's center.
     *
     * @return the x-coordinate of the ball's center.
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * Returns the y-coordinate of the ball's center.
     *
     * @return the y-coordinate of the ball's center.
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * Returns the radius of the ball.
     *
     * @return the radius of the ball.
     */
    public int getSize() {
        return this.r;
    }

    /**
     * Returns the color of the ball.
     *
     * @return the color of the ball.
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * Returns the velocity of the ball.
     *
     * @return the velocity of the ball.
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * Sets the velocity of the ball.
     *
     * @param v the new velocity of the ball.
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * Sets the velocity of the ball using the specified dx and dy values.
     *
     * @param dx the change in x-coordinate per second.
     * @param dy the change in y-coordinate per second.
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * Draws the ball on the given DrawSurface.
     *
     * @param surface the DrawSurface on which to draw the ball.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle(getX(), getY(), this.r);
    }

    /**
     * Moves the ball one step according to its velocity.
     */
    public void moveOneStep() {
        this.center = this.getVelocity().applyToPoint(this.center);
    }

    /**
     * Creates a random ball within the specified frame boundaries. The ball's size
     * and speed are determined based on the provided parameters.
     *
     * @param size  the radius of the ball.
     * @param minX  the minimum x-coordinate of the frame.
     * @param maxX  the maximum x-coordinate of the frame.
     * @param minY  the minimum y-coordinate of the frame.
     * @param maxY  the maximum y-coordinate of the frame.
     * @param color the color of the ball.
     * @param rand  the Random object used to generate random values.
     * @return a new Ball object with random position and velocity within the
     *         specified frame.
     */
    public static Ball createBallInFrame(int size, int minX, int maxX, int minY, int maxY, Color color, Random rand) {
        // Ensure the ball fits within the frame boundaries.
        int rangeX = maxX - minX - 2 * size;
        int rangeY = maxY - minY - 2 * size;

        // Generate random x and y coordinates for the ball's center, ensuring it stays
        // within the frame.
        int x = (rangeX > 0) ? rand.nextInt(rangeX) + minX + size : minX + size;
        int y = (rangeY > 0) ? rand.nextInt(rangeY) + minY + size : minY + size;

        // Create the ball with the generated position and specified size and color.
        Ball ball = new Ball(new Point(x, y), size, color);

        double speed;
        if (size >= 50) {
            speed = 1;
        } else {
            speed = 50 / size;
        }
        ball.setVelocity(Velocity.fromAngleAndSpeed(rand.nextInt(360), speed));
        return ball;
    }
}