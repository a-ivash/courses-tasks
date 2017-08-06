package com.ivashchenko.practice4.task46;

/**
 * This class is used to represent information about 2d point
 * @version 0.01
 * @author Alex Ivashchenko
 */
public class Point2D{
    private int x;
    private int y;

    public Point2D(int x, int y) {
        this.x = x;
        this.y = y;
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object obj) {
        Point2D p1 = (Point2D)obj;
        return (p1.getX() == getX() && p1.getY() == getY());
    }

    @Override
    public String toString() {
        return "(" + getX() + ", " + getY() + ")";
    }

    @Override
    public int hashCode() {
        return 31 * x + y;
    }

    public static double getDistance(Point2D first, Point2D second) {
        return Math.sqrt(Math.pow(first.getX() - second.getX(), 2) + Math.pow(first.getY() - second.getY(), 2));
    }
}
