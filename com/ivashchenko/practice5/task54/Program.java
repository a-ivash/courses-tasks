package com.ivashchenko.practice5.task54;

public class Program {
    private static final int NUMBER_OF_ELEMENTS = 1_000_000;
    public static void main(String[] args) {
        RandomSum randomSum = new RandomSum(NUMBER_OF_ELEMENTS);
        System.out.println("Sum = " + randomSum.getSumOfRandomNumbers());
    }
}
