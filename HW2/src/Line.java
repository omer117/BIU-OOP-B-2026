/*

Name: Omer Asraf
ID: 211384755
Course: OOP

*/





/**
 * A Line is repesented here with 2 Points,
 * Starting Point and Ending Point.
 */
public class Line {

    private Point start;
    private Point end;

    /**
     * First Constructor gets 2 points and creates a line.
     *
     * @param start - The starting point of this Line.
     * @param end - The ending point of this Line.
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Second Constructor gets 4 coordinates(2 x's and 2 y's),
     * creates 2 Points, and then creates a Line from those Points.
     *
     * @param x1 - The x coordinate of the starting Point.
     * @param y1 - The y coordinate of the starting Point.
     * @param x2 - The x coordinate of the ending Point.
     * @param y2 - The y coordinate of the ending Point.
     */
    public Line(double x1, double y1, double x2, double y2) {
        Point startN = new Point(x1, y1);
        Point endN = new Point(x2, y2);
        this.start = startN;
        this.end = endN;
    }

    /**
     * A method that measures the Line length.
     *
     * @return - returns the length of the line.
     */
    public double length() {
        return this.start.distance(end);
    }

    /**
     * A method that finds the middle Point of the Line.
     *
     * @return returns the middle Point of the Line
     */
    public Point middle() {
        double middleXCo = (this.start.getX() + this.end.getX()) / 2;
        double middleYCo = (this.start.getY() + this.end.getY()) / 2;

        Point middlePoint = new Point(middleXCo, middleYCo);
        return middlePoint;
    }

    /**
     * A GET method that returns the starting Point.
     *
     * @return starting point of the Line.
     */
    public Point start() {
        return this.start;
    }

    /**
     * A GET method that returns the ending Point.
     *
     * @return ending point of the Line.
     */
    public Point end() {
        return this.end;
    }

    /**
     * A method that calculate the Line Slop.
     *
     * @return returns the Line Slop.
     */
    public double getLineSlope() {
        return ((this.start.getY() - this.end.getY())
                /
                (this.start.getX() - this.end.getX()));

    }

    /**
     * A Method that checks if the line is vertical (infinite slop).
     *
     * @return returns true if it is vertical, false if it is'nt.
     */
    public boolean isVertical() {
        return Math.abs(this.start.getX() - this.end.getX()) < Point.THRESHOLD;
    }

    /**
     *
     *
     * @return returns the b in the Line equation (y=mx+b)
     */
    public double getLineB() {
        return (this.start.getY() - getLineSlope() * this.start.getX());
    }

    /**
     * A method that checks if the given Point is within the bounds of the Line.
     *
     * @param p - A given Point.
     * @return returns true if the Point is on the Line, false if not.
     */
    public boolean isInBounds(Point p) {
        double minX = Math.min(this.start.getX(), this.end.getX());
        double maxX = Math.max(this.start.getX(), this.end.getX());
        double minY = Math.min(this.start.getY(), this.end.getY());
        double maxY = Math.max(this.start.getY(), this.end.getY());

        return (p.getX() >= minX - Point.THRESHOLD
                && p.getX() <= maxX + Point.THRESHOLD)
                && (p.getY() >= minY - Point.THRESHOLD
                        && p.getY() <= maxY + Point.THRESHOLD);
    }

    /**
     * A method that checks if a given Line is intersecting with this Line.
     *
     * @param other - A given Line.
     * @return returns true if the line given is intersecting with this line, false
     *         if not.
     */
    public boolean isIntersecting(Line other) {
        // If there is a only one intersection point
        if (this.intersectionWith(other) != null) {
            return true;
        }

        // we check if we have infinite intersection points
        if (this.isVertical() && other.isVertical()) {

            if (Math.abs(this.start.getX() - other.start.getX()) < Point.THRESHOLD) {

                return this.isInBounds(other.start) || this.isInBounds(other.end)
                        || other.isInBounds(this.start) || other.isInBounds(this.end);

                    }
        } else if (!this.isVertical() && !other.isVertical()) {
            double m1 = this.getLineSlope();
            double m2 = other.getLineSlope();
            // If the slopes are equal, they are parallel
            if (Math.abs(m1 - m2) < Point.THRESHOLD) {
                // we check If they also have the same b (from the equation y=mx+b)
                if (Math.abs(this.getLineB() - other.getLineB()) < Point.THRESHOLD) {
                    // Check if they overlap within their X and Y bounds
                    return this.isInBounds(other.start) || this.isInBounds(other.end)
                            || other.isInBounds(this.start) || other.isInBounds(this.end);
                }
            }
        }
        return false;
    }

    /**
     * A method that checks if 2 given Lines are intersecting with this Line.
     *
     * @param other1
     * @param other2
     * @return returns true if they are, false if not.
     */
    public boolean isIntersecting(Line other1, Line other2) {
        return intersectionWith(other1) != null
                && intersectionWith(other2) != null;
    }

    /**
     * A method that given a Line and returns the intersection point with it.
     *
     * @param other
     * @return returns the intersection Point if they are intersecting, null if they
     *         are not.
     */
    public Point intersectionWith(Line other) {

        double intersectionX, intersectionY;
        if (other == null) {
            return null;
        }
        if (this.isVertical() && other.isVertical()) {
            return null;
        }

        if (!this.isVertical() && !other.isVertical()) {
            if (Math.abs(this.getLineSlope() - other.getLineSlope()) < Point.THRESHOLD) {
                return null;
            }
        }

        if (this.isVertical()) {
            intersectionX = this.start.getX();
            double m2 = other.getLineSlope();
            double b2 = other.getLineB();
            intersectionY = m2 * intersectionX + b2;
        } else if (other.isVertical()) {
            intersectionX = other.start.getX();
            double m1 = this.getLineSlope();
            double b1 = this.getLineB();
            intersectionY = m1 * intersectionX + b1;
        } else {
            double m1 = this.getLineSlope();
            double b1 = this.getLineB();
            double m2 = other.getLineSlope();
            double b2 = other.getLineB();

            intersectionX = (b2 - b1) / (m1 - m2);
            intersectionY = m1 * intersectionX + b1;
        }

        Point intersectionPoint = new Point(intersectionX, intersectionY);

        if (this.isInBounds(intersectionPoint)
                && other.isInBounds(intersectionPoint)) {
            return intersectionPoint;
        }

        return null;
    }

    /**
     * A method that checks if the line given is equal to this one.
     *
     * @param other
     * @return returns true if it is, false if it's not.
     */
    public boolean equals(Line other) {
        if (other == null) {
            return false;
        }

        // we got 2 options if the lines are equal:(A,B are points, A start, B end)
        // 1. first line is: A - > B and the second line is: A -> B
        // or
        // 2. first line is: A - > B and the second line is: B -> A
        return (this.start.equals(other.start) && this.end.equals(other.end))
                ||
                (this.start.equals(other.end) && this.end.equals(other.start));

    }
}