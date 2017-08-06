package com.ivashchenko.practice4.task46;

import java.util.List;

/**
 * This class is used to demonstrate working with CollectionHandler class
 * @version 0.01
 * @author Alex Ivashchenko
 */
public class Program {
    public static void main(String[] args) {
        CollectionHandler collectionHandler = new CollectionHandler(10);
        collectionHandler.printPoints();
        List<Point2D> points = collectionHandler.getLargestTrianglePoints();
        System.out.println(points);
    }
}
