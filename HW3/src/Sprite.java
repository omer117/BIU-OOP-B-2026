/*

Name: Omer Asraf
ID: 211384755
Course: OOP

*/

import biuoop.DrawSurface;

/**
 * The Sprite interface defines the behavior for drawable
 * and updateable objects in the game.
 */
public interface Sprite {

    /**
     * Draws the sprite on the given surface.
     *
     * @param surface the surface to draw on.
     */
    void drawOn(DrawSurface surface);

    /**
     * Notifies the sprite that time has passed, allowing it to update its state.
     */
    void timePassed();

    /**
     * Adds the sprite to the game.
     *
     * @param g the game to add the sprite to.
     */
    void addToGame(Game g);
}