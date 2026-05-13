
/**
 * A Point is repressented by x and y coordinates.
 * Point here is a 2D location.
 */
public class Point {
    private double x, y;
    public static final double THRESHOLD = 0.001;

    /**
     * Our constructor creates a new Point,
     * with x and y coordinates.
     *
     * @param x - the x coordinate;
     * @param y - the y coordinate;
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * A method that gets another Point,
     * and returns the distance between the points.
     * (current and the point given).
     *
     * @param other - The other Point to measure the distance from (or to..).
     * @return - the distance between the points.
     */
    public double distance(Point other) {
        if (other == null) {
            return 0;
        }
        return Math.sqrt(
                (this.x - other.getX()) * (this.x - other.getX())
                        +
                        (this.y - other.getY()) * (this.y - other.getY()));
    }

    /**
     * A method that gets another Point,
     * and returns if they equal in coordinates.
     *
     * @param other - The other Point to check equality with.
     * @return - true if the points are equal, false if their not.
     */
    public boolean equals(Point other) {
        if (other == null) {
            return false;
        }
        return (Math.abs(this.x - other.getX()) < THRESHOLD)
                &&
                (Math.abs(this.y - other.getY()) < THRESHOLD);

    }

    /**
     * A GET method that returns the x coordinate of the Point.
     *
     * @return the x coordinate.
     */
    public double getX() {
        return this.x;
    }

    /**
     * A GET method that returns the y coordinate of the Point.
     *
     * @return the y coordinate
     *
     */
    public double getY() {
        return this.y;
    }

}
