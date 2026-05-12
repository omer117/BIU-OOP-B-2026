/*

Name: Omer Asraf
ID: 211384755
Course: OOP

*/

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;



public class Game {

    private SpriteCollection sprites;
    private GameEnvironment gameEnv;
    private GUI gui;
    private Sleeper sleeper;

    public Game() {
        this.sprites = new SpriteCollection();
        this.gameEnv = new GameEnvironment();
        this.gui = new GUI("Game", 800, 600);
        this.sleeper = new Sleeper();

    }

    public void addCollidable(Collidable c) {
        this.gameEnv.addCollidable(c);
    }

    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    public void initialize() {
        // Ball ball = new Ball(new Point(400, 300), 5, Color.RED);
        // ball.setVelocity(5, 5);
        // ball.addToGame(this);

        // Paddle paddle = new Paddle(new Rectangle(new Point(350, 550), 100, 20));
        // paddle.addToGame(this);

        // for()
        // }

    }

    public void run() {

        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;

        while (true) {
            long startTime = System.currentTimeMillis();

            DrawSurface d = gui.getDrawSurface();
            this.sprites.drawAllOn(d);
            this.sprites.notifyAllTimePassed();
            gui.show(d);
            this.sprites.notifyAllTimePassed();

            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.initialize();
        game.run();
    }
}