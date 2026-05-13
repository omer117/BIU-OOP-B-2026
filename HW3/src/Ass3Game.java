/**
 * Ass3Game class - the main entry point for the game. It initializes and runs the game loop.
 */
public class Ass3Game {

    /**
     * The main method to start the game. It creates a new Game instance, initializes it, and runs the game loop.
     * @param args command-line arguments (not used).
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.initialize();
        game.run();
    }
}