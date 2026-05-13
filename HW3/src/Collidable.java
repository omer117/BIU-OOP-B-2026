/**
 * The Collidable interface represents an object that can be collided with in
 * the game. It defines methods to get the collision rectangle and to handle a
 * hit event when a collision occurs.
 */
public interface Collidable {
    /**
     * Returns the rectangle that represents the collision area of the object.
     *
     * @return the collision rectangle of the object.
     */
    Rectangle getCollisionRectangle();

    /**
     * Handles a hit event when a collision occurs. It takes the collision point and
     * the current velocity of the ball as parameters and returns the new velocity
     * after the collision.
     *
     * @param collisionPoint  the point at which the collision occurred.
     * @param currentVelocity the current velocity of the ball before the collision.
     * @return the new velocity of the ball after the collision.
     */
    Velocity hit(Point collisionPoint, Velocity currentVelocity);
}
