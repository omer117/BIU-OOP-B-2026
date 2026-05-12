package src;

/**
 * Velocity specifies the change in position on the `x` and the `y` axes.
 */
public class Velocity {
    private double dx, dy;

    /**
     * Constructs a Velocity object with the change in x and y coordinates.
     *
     * @param dx The change in x-coordinate.
     * @param dy The change in y-coordinate.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Returns the change in x-coordinate.
     *
     * @return The change in x-coordinate.
     */
    public double getDx() {
        return dx;
    }

    /**
     * Returns the change in y-coordinate.
     *
     * @return The change in y-coordinate.
     */
    public double getDy() {
        return dy;
    }

    /**
     * Creates a Velocity object from an angle and speed. The angle is measured in
     * degrees, where 0 degrees is straight up, and the angle increases clockwise
     * (0 degrees is up, 90 degrees is right, 180 degrees is down, 270 degrees is
     * left).
     * The speed is the magnitude of the velocity.
     *
     * @param angle The angle of the velocity in degrees.
     * @param speed The speed of the velocity.
     * @return A Velocity object representing the velocity with the given angle and
     *         speed.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {

        // Convert the angle from degrees to radians for trigonometric calculations
        double radians = Math.toRadians(angle);
        double dx = speed * Math.sin(radians);
        double dy = -speed * Math.cos(radians);

        return new Velocity(dx, dy);
    }

    /**
     * Takes a point with position (x,y) and returns a new point with position
     * (x+dx, y+dy).
     *
     * @param p The point to which the velocity is applied.
     * @return A new point with the velocity applied.
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + dx, p.getY() + dy);
    }
}
