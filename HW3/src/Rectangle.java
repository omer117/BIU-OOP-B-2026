/*

Name: Omer Asraf
ID: 211384755
Course: OOP

*/

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The Rectangle class represents a rectangle defined by its upper-left corner,
 * width, height, and color. It provides methods to calculate the intersection
 * points with a given line and to set the color of the rectangle.
 *
 */
class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;
    private Color color;

    /**
     * Constructs a rectangle with the specified upper-left corner, width, and
     * height.
     *
     * @param upperLeft the upper-left corner of the rectangle.
     * @param width     the width of the rectangle.
     * @param height    the height of the rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        this.color = Color.BLACK;
    }

    /**
     * Returns the intersection points between the rectangle and a given line.
     *
     * @param line the line to intersect with the rectangle.
     * @return a list of intersection points.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        List<Point> intersectionPoints = new ArrayList<>();

        double x = this.upperLeft.getX();
        double y = this.upperLeft.getY();
        double w = this.width;
        double h = this.height;

        Line topEdge = new Line(x, y, x + w, y);
        Line bottomEdge = new Line(x, y + h, x + w, y + h);
        Line leftEdge = new Line(x, y, x, y + h);
        Line rightEdge = new Line(x + w, y, x + w, y + h);
        Line[] edges = { topEdge, bottomEdge, leftEdge, rightEdge };

        for (int i = 0; i < edges.length; i++) {
            Point intersection = line.intersectionWith(edges[i]);
            if (intersection != null && !intersectionPoints.contains(intersection)) {
                intersectionPoints.add(intersection);
            }
        }
        return intersectionPoints;

    }

    /**
     * Sets the color of the rectangle.
     *
     * @param color the color to set for the rectangle.
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Returns the color of the rectangle.
     *
     * @return the color of the rectangle.
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Returns the height of the rectangle.
     *
     * @return the height of the rectangle.
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Returns the upper-left corner of the rectangle as a Point.
     *
     * @return the upper-left corner of the rectangle.
     */
    public Point getUpperLeft() {
        return this.upperLeft;

    }

    /**
     * Returns the color of the rectangle.
     *
     * @return the color of the rectangle.
     */
    public Color getColor() {
        return this.color;
    }
}