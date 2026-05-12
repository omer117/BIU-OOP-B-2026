/*

Name: Omer Asraf
ID: 211384755
Course: OOP

*/


import biuoop.DrawSurface;

/**
 * The Block class represents a rectangular block that can be collided with. It
 * implements the Collidable and Sprite interfaces, allowing it to be drawn on a
 * surface and to interact with other game elements.
 */
public class Block implements Collidable, Sprite {

    private Rectangle rect;


    public Block(Rectangle rect) {
        this.rect = rect;
    }


    /**
     * Draws the block on the given surface.
     *
     * @param surface - The surface to draw on.
     */
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.rect.getColor());
        surface.fillRectangle((int) this.rect.getUpperLeft().getX(), (int) this.rect.getUpperLeft().getY(),
                (int) this.rect.getWidth(), (int) this.rect.getHeight());

        surface.setColor(this.rect.getColor());
        surface.drawRectangle((int) this.rect.getUpperLeft().getX(),
                (int) this.rect.getUpperLeft().getY(),
                (int) this.rect.getWidth(),
                (int) this.rect.getHeight());
    }


    /**
     * Updates the block's state based on the passage of time.
     */
    @Override
    public void timePassed() {
        // No time-based behavior for blocks
    }

    /**
     *  Returns the rectangle representing the block's collision area.
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }

    /**
     * Adds the block to the game, registering it as both a collidable and a sprite.
     *
     * @param g the game to which the block should be added.
     */
    @Override
    public void addToGame(Game g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

    /**
     * Handles the hit event when the block is collided with.
     *
     * @param collisionPoint  The point of collision.
     * @param currentVelocity The current velocity of the colliding object.
     * @return The new velocity after the hit.
     */
    @Override
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        double newDx = currentVelocity.getDx();
        double newDy = currentVelocity.getDy();

        double leftX = this.rect.getUpperLeft().getX();
        double rightX = leftX + this.rect.getWidth();
        double topY = this.rect.getUpperLeft().getY();
        double bottomY = topY + this.rect.getHeight();

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

}
