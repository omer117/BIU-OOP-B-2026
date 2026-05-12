/*

Name: Omer Asraf
ID: 211384755
Course: OOP

*/


/**
 * The Collidable interface represents an object that can be collided with in
 * the game. It defines methods to get the collision rectangle and to handle a
 * hit event when a collision occurs.
 */
public interface Collidable {
    Rectangle getCollisionRectangle();

    Velocity hit(Point collisionPoint, Velocity currentVelocity);
}
