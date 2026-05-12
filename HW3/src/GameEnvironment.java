/*

Name: Omer Asraf
ID: 211384755
Course: OOP

*/


import java.util.List;

/**
 * GameEnvironment class represents the game environment which holds a list of
 * collidable objects and provides functionality to find the closest collision
 * point for a given trajectory.
 */
public class GameEnvironment {
    private List<Collidable> collidables;

    public GameEnvironment() {
        this.collidables = new java.util.ArrayList<>();
    }

    /**
     * Adds a collidable object to the game environment.
     *
     * @param c the collidable object to be added.
     */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }

    /**
     * Returns the closest collision information for a given trajectory. If there is
     * no collision, it returns null.
     *
     * @param trajectory the line representing the trajectory of an object.
     * @return the CollisionInfo of the closest collision, or null if there is no
     *         collision.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        CollisionInfo closestInfo = null;
        double minDistance = Double.MAX_VALUE;

        for (int i = 0; i < this.collidables.size(); i++) {
            Rectangle rect = this.collidables.get(i).getCollisionRectangle();
            Point closestPoint = trajectory.closestIntersectionToStartOfLine(rect);
            if (closestPoint != null) {
                double dist = trajectory.start().distance(closestPoint);
                if (dist < minDistance) {
                    minDistance = dist;
                    closestInfo = new CollisionInfo(closestPoint, this.collidables.get(i));
                }
            }
        }
        return closestInfo;
    }
}