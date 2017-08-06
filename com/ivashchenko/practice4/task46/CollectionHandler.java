package com.ivashchenko.practice4.task46;

import java.util.*;

/**
 * This class is used to store 2d point in HashSet and finding 3 points forming largest triangle.
 * @version 0.01
 * @author Alex Ivashchenko
 */
public class CollectionHandler {
    private Set<Point2D> points = new HashSet<>();
    private Random random = new Random();

    public CollectionHandler(int numberOfPoints) {
        generatePoints(numberOfPoints);
    }

    private void generatePoints(int numberOfPoints) {
        while (points.size() < numberOfPoints) {
            Point2D point2D = new Point2D(random.nextInt() % 100, random.nextInt() % 100);
            points.add(point2D);
        }
    }

    public void printPoints() {
        System.out.println(points);
    }

    public List<Point2D> getLargestTrianglePoints() {
        List<Point2D> retPoints = new ArrayList<>();
        List<Point2D> targetPoints = new ArrayList<>(points);
        double maxDist = 0;
        Point2D f = null;
        Point2D s = null;
        Point2D t = null;
        for (Point2D p1: points) {
            for (Point2D p2: points) {
                for (Point2D p3: points) {
                    double tDist = getPerimeter(p1, p2, p3);
                    if(tDist > maxDist) {
                        maxDist = tDist;
                        f = p1;
                        s = p2;
                        t = p3;
                    }
                }
            }
        }

        System.out.println("Max perimeter = " + maxDist);
        retPoints.add(f);
        retPoints.add(s);
        retPoints.add(t);
        return retPoints;
    }

    private double getPerimeter(Point2D f, Point2D s, Point2D t) {
        double dist = Point2D.getDistance(f, s);
        dist += Point2D.getDistance(s, t);
        dist += Point2D.getDistance(f, t);
        return dist;
    }
}
