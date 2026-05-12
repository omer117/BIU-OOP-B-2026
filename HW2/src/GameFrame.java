import src.Ball;
import src.Velocity;

/**
 * GameFrame represents the boundaries of the game area and handles collision
 * detection for a ball.
 */
public class GameFrame {
    private int minX, maxX, minY, maxY;
    private boolean isContainer;
    public static final int DEFAULTMAXWIDTH = 800;
    public static final int DEFAULTMAXHEIGHT = 600;
    public static final int DEFAULTMINX = 0;
    public static final int DEFAULTMINY = 0;


    /**
     * Constructs a GameFrame with the specified position, size, and type (internal
     * or external).
     *
     * @param minX           the minimum x-coordinate of the frame.
     * @param minY           the minimum y-coordinate of the frame.
     * @param width       the width of the frame.
     * @param height      the height of the frame.
     * @param isContainer true if the frame is internal (ball bounces off the
     *                    edges),
     *                    false if the frame is external (ball bounces when it
     *                    crosses the edges).
     */
    public GameFrame(int minX, int minY, int width, int height, boolean isContainer) {
        this.minX = minX;
        this.maxX = minX + width;
        this.minY = minY;
        this.maxY = minY + height;
        this.isContainer = isContainer;
    }

    /**
     * Checks for collisions between the ball and the frame edges, and updates the
     * ball's velocity accordingly.
     *
     * @param ball the ball to check for collisions with the frame edges.
     */
    public void checkCollision(Ball ball) {
        double x = ball.getX();
        double y = ball.getY();
        int radius = ball.getSize();
        Velocity currentVelocity = ball.getVelocity();

        if (isContainer) {
            // Check for collisions with the frame edges and reverse velocity accordingly.
            if (x + radius >= maxX && currentVelocity.getDx() > 0) {
                ball.setVelocity(-currentVelocity.getDx(), currentVelocity.getDy());
            } else if (x - radius <= minX && currentVelocity.getDx() < 0) {
                ball.setVelocity(-currentVelocity.getDx(), currentVelocity.getDy());
            }

            if (y + radius >= maxY && currentVelocity.getDy() > 0) {
                ball.setVelocity(currentVelocity.getDx(), -currentVelocity.getDy());
            } else if (y - radius <= minY && currentVelocity.getDy() < 0) {
                ball.setVelocity(currentVelocity.getDx(), -currentVelocity.getDy());
            }

        } else {
            if (x + radius > minX && x - radius < maxX && y + radius > minY && y - radius < maxY) {
                if (x <= minX && currentVelocity.getDx() > 0) {
                    ball.setVelocity(-currentVelocity.getDx(), currentVelocity.getDy());
                } else if (x >= maxX && currentVelocity.getDx() < 0) {
                    ball.setVelocity(-currentVelocity.getDx(), currentVelocity.getDy());
                } else if (y <= minY && currentVelocity.getDy() > 0) {
                    ball.setVelocity(currentVelocity.getDx(), -currentVelocity.getDy());
                } else if (y >= maxY && currentVelocity.getDy() < 0) {
                    ball.setVelocity(currentVelocity.getDx(), -currentVelocity.getDy());
                }
            }
        }
    }
}