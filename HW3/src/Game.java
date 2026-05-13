
/*

Name: Omer Asraf
ID: 211384755
Course: OOP

*/
import java.awt.Color;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * The Game class represents the main game logic and manages the game
 * environment, sprites, and the game loop. It provides methods to add
 * collidable objects and sprites to the game, initialize the game state, and
 * run the game loop to update and render the game continuously.
 */
public class Game {

    private SpriteCollection sprites;
    private GameEnvironment gameEnv;
    private GUI gui;
    private Sleeper sleeper;

    /**
     * Constructs a new Game instance.
     */
    public Game() {
        this.sprites = new SpriteCollection();
        this.gameEnv = new GameEnvironment();
        this.gui = new GUI("Game", 800, 600);
        this.sleeper = new Sleeper();

    }

    /**
     * Adds a collidable object to the game environment.
     *
     * @param c the collidable object to be added to the game environment.
     */
    public void addCollidable(Collidable c) {
        this.gameEnv.addCollidable(c);
    }

    /**
     * Adds a sprite to the game.
     *
     * @param s the sprite to be added to the game.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Initializes the game state by creating and adding game objects such as balls
     * and paddles to the game.
     */
    public void initialize() {
        Block topWall = new Block(new Rectangle(new Point(0, 0), 800, 20));
        Block leftWall = new Block(new Rectangle(new Point(0, 20), 20, 580));
        Block rightWall = new Block(new Rectangle(new Point(780, 20), 20, 580));
        Block bottomWall = new Block(new Rectangle(new Point(20, 580), 760, 20));
        topWall.addToGame(this);
        leftWall.addToGame(this);
        rightWall.addToGame(this);
        bottomWall.addToGame(this);

        Color[] colors = {Color.GRAY, Color.RED, Color.YELLOW, Color.BLUE, Color.PINK, Color.GREEN};

        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 12 - row; col++) {
                double x = 780 - ((col + 1) * 50);
                double y = 100 + (row * 20);

                Block b = new Block(new Rectangle(new Point(x, y), 50, 20));
                b.getCollisionRectangle().setColor(colors[row]);
                b.addToGame(this);
            }
        }

        Ball ball1 = new Ball(new Point(300, 400), 5, Color.WHITE);
        ball1.setVelocity(new Velocity(2, -2));
        ball1.setGameEnvironment(this.gameEnv);
        ball1.addToGame(this);

        Ball ball2 = new Ball(new Point(500, 400), 5, Color.WHITE);
        ball2.setVelocity(new Velocity(-2.5, -2.5));
        ball2.setGameEnvironment(this.gameEnv);
        ball2.addToGame(this);

        Paddle paddle = new Paddle(new Rectangle(new Point(350, 560), 100, 20), gui);
        paddle.getCollisionRectangle().setColor(Color.YELLOW);
        paddle.addToGame(this);
    }

    /**
     * Runs the game loop, which continuously updates and renders
     * the game at a fixed frame rate.
     */
    public void run() {
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;

        while (true) {
            long startTime = System.currentTimeMillis();
            DrawSurface d = gui.getDrawSurface();

            d.setColor(Color.BLUE);
            d.fillRectangle(0, 0, 800, 600);

            this.sprites.drawAllOn(d);
            gui.show(d);

            this.sprites.notifyAllTimePassed();

            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }

    /**
     * The main method serves as the entry point for the game. It creates a new Game
     * instance, initializes the game state, and starts the game loop.
     *
     * @param args the command-line arguments (not used).
     */

}