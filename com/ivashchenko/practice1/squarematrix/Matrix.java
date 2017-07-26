package com.ivashchenko.practice1.squarematrix;

import java.util.Arrays;
import java.util.Random;

/**
 * This class is used to represent square matrix filled with random numbers of short type.
 * This class supports finding the column containing the max value in matrix
 * and removing the corresponding column.
 * @version 0.01
 * @author Alex Ivashchenko
 */
public class Matrix {
    private final byte size = 7;
    private short[][] matrix = new short[size][size];


    public Matrix() {
        initMatrixWithRandomNumbers();
    }

    private byte getColumnWithMaxElement() {
        byte maxCol = 0, maxRow = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (matrix[i][j] > matrix[maxRow][maxCol]) {
                    maxRow = (byte)i;
                    maxCol = (byte)j;
                }
            }
        }

        System.out.println(String.format("Max at [Row = %d, Col = %d]  = %d", maxRow + 1,
                                        maxCol + 1, matrix[maxRow][maxCol]));
        return maxCol;
    }

    private void initMatrixWithRandomNumbers() {
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = (short)random.nextInt();
            }
        }
    }

    /** Method for removing ANY column from matrix.*/
    private void removeColumn(byte column) {
        for (int i = 0; i < size; i++) {
            for (int j = column + 1; j < size; j++) {
                matrix[i][j - 1] = matrix[i][j];
            }
            matrix[i] = Arrays.copyOf(matrix[i], size - 1);
        }
    }

    public void removeColumnWithMaxElement() {
        byte minColumn = getColumnWithMaxElement();
        removeColumn(minColumn);
    }

    public void printMatrix() {
        for (short[] row: matrix) {
            for (short number: row) {
                System.out.print(String.format("%6d\t", number));
            }
            System.out.println();
        }
        System.out.println();
    }
}