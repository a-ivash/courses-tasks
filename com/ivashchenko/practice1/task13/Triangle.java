package com.ivashchenko.practice1.task13;
/**
 * This class is used to represent triangle with max level = 9.
 * This class supports building triangle of specified level and printing it to console.
 * @version 0.01
 * @author Alex Ivashchenko
 */
public class Triangle {
    private final int MAX_LEVEL = 9;
    private int[][] triangle;

    public void buildTriangle(int level) {
        if ((level <= 0) || (level > MAX_LEVEL)) {
            System.out.println("Level value is incorrect");
            return;
        }
        triangle = new int[level][];
        for (int row = 0; row < level; row += 1) {
            triangle[row] = new int[2*row+1];
            for(int col = 0; col < row + 1; col++) {
                triangle[row][col] = col + 1;
                triangle[row][2*row-col] = col + 1;
            }
        }
    }

    public void printTriangle() {
        if(triangle == null) return;
        for (int row = 0; row < triangle.length; row++) {
            for (int j = 0; j < triangle.length - row; j++) {
                System.out.print(' ');
            }
            for (int element: triangle[row]) {
                System.out.print(element);
            }
            System.out.println();
        }
    }

}

