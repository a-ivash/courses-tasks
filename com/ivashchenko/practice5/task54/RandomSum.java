package com.ivashchenko.practice5.task54;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;

/** This class contains pool of task, array of random numbers. */
public class RandomSum {
    private final int NUMBER_OF_ELEMENTS;
    private static final int NUMBER_OF_THREADS = 8;
    private int[] numbers;

    private ForkJoinPool pool = new ForkJoinPool(NUMBER_OF_THREADS);

    public RandomSum(int numberOfElements){
        NUMBER_OF_ELEMENTS = numberOfElements;
        numbers = new int[NUMBER_OF_ELEMENTS];

        Random random = new Random();
        for (int i = 0; i < NUMBER_OF_ELEMENTS; i++) {
            numbers[i] = i + 1; //random.nextInt(10);
        }
        printNumbers();
    }

    public long getSumOfRandomNumbers() {
        return pool.invoke(new SumTask(0, numbers.length, numbers));
    }

    public void printNumbers() {
        System.out.println(Arrays.toString(numbers));
    }

}
