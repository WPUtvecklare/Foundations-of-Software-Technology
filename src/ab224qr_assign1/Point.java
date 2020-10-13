/*
 * Date: 2020-09-08
 * File Name: Point.java
 * Author: Adam Bergman
 */

package ab224qr_assign1;

/**
 * Class Description: A point with X and Y coordinates
 * @version 1.0
 * @author Adam Bergman
 */
public class Point {
    private int x;
    private int y;

    /**
     * Constructor with arguments to initialize the coordinates
     * @param x coordinate
     * @param y coordinate
     */
    public Point (int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Constructor without arguments, setting the coordinates to 0
     */
    public Point () {
        this.x = 0;
        this.y = 0;
    }

    /**
     * Starting point of the application
     * @param args
     */
    public static void main(String[] args) {
        Point p1 = new Point();
        Point p2 = new Point(3,4);

        System.out.println(p1.toString());
        System.out.println(p2.toString());

        if (p1.isEqualTo(p2))
            System.out.println("The two points are equal");

        double dist = p1.distanceTo(p2);
        System.out.println("Point Distance: " + dist);

        p2.move(5, -2);
        p1.moveToXY(8,2);

        if (p1.isEqualTo(p2))
            System.out.println("The two points are equal");
    }

    /**
     * Presents the point's coordinates as a string
     * @return the point coordinates
     */
    @Override
    public String toString() {
        return "(" + this.x + "," + this.y + ")";
    }

    /**
     * Checks whether the given Point has the same coordinates as the current
     * @param p Point to compare with
     * @return true if they are same, else false
     */
    private boolean isEqualTo(Point p) {
        return this.x == p.x && this.y == p.y;
    }

    /**
     * Calculates the distance between two points
     * @param p Point to compare with
     * @return the distance between the points
     */
    private double distanceTo (Point p) {
        return Math.sqrt(Math.pow(this.x - p.x, 2) + Math.pow(this.y - p.y, 2));
    }

    /**
     * Moves the points in certain steps
     * @param x vertical position
     * @param y horizontal position
     */
    private void move(int x, int y) {
        this.x += x;
        this.y += y;
    }

    /**
     * Overrides the existing coordinates
     * @param x vertical position
     * @param y horizontal position
     */
    private void moveToXY(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
