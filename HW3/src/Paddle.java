/*

Name: Omer Asraf
ID: 211384755
Course: OOP

*/


import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;

/** Paddle class represents a paddle in the game. */
public class Paddle implements Sprite, Collidable {
    private KeyboardSensor keyboard;
    private Rectangle rectangle;

    public Paddle(Rectangle rectangle, GUI gui) {
        this.rectangle = rectangle;
        this.keyboard = gui.getKeyboardSensor();
    }

    /**
     * Moves the paddle left by 10 pixels, ensuring it does not go beyond the left
     * edge of the screen.
     */
    public void moveLeft() {
        double newX = this.rectangle.getUpperLeft().getX() - 10;
        if (newX < 0) {
            newX = 0;
        }
        this.rectangle = new Rectangle(new Point(newX, this.rectangle.getUpperLeft().getY()),
                this.rectangle.getWidth(), this.rectangle.getHeight());
    }

    /**
     * Moves the paddle right by 10 pixels, ensuring it does not go beyond the right
     * edge of the screen.
     */
    public void moveRight() {
        double newX = this.rectangle.getUpperLeft().getX() + 10;
        if (newX > 800 - this.rectangle.getWidth()) {
            newX = 800 - this.rectangle.getWidth();
        }
        this.rectangle = new Rectangle(new Point(newX, this.rectangle.getUpperLeft().getY()),
                this.rectangle.getWidth(), this.rectangle.getHeight());
    }

    /**
     * Checks for keyboard input and moves the paddle accordingly.
     */
    @Override
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    /**
     * Draws the paddle on the given draw surface.
     *
     * @param d the draw surface on which to draw the paddle.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.rectangle.getColor());
        d.fillRectangle((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
        d.setColor(this.rectangle.getColor());
        d.drawRectangle((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
    }

    /**
     * Returns the collision rectangle of the paddle.
     *
     * @return the collision rectangle.
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * Handles the collision with a ball.
     *
     * @param collisionPoint  the point where the collision occurs.
     * @param currentVelocity the current velocity of the ball.
     * @return the new velocity of the ball after the collision.
     */
    @Override
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        double newDx = currentVelocity.getDx();
        double newDy = currentVelocity.getDy();

        double leftX = this.rectangle.getUpperLeft().getX();
        double rightX = leftX + this.rectangle.getWidth();
        double topY = this.rectangle.getUpperLeft().getY();
        double bottomY = topY + this.rectangle.getHeight();

        if (Math.abs(collisionPoint.getX() - leftX) < Point.THRESHOLD ||
                Math.abs(collisionPoint.getX() - rightX) < Point.THRESHOLD) {
            newDx = -newDx;
        }

        if (Math.abs(collisionPoint.getY() - topY) < Point.THRESHOLD ||
                Math.abs(collisionPoint.getY() - bottomY) < Point.THRESHOLD) {
            newDy = -newDy;
        }

        return new Velocity(newDx, newDy);
    }

    /**
     * Adds the paddle to the game.
     *
     * @param g the game to which to add the paddle.
     */
    @Override
    public void addToGame(Game g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

}
