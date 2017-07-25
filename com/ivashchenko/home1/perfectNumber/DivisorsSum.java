package com.ivashchenko.home1.perfectNumber;

import java.util.LinkedList;
import java.util.List;

/**
 * This class is used to calculate sum of all divisors of specified number except this number;
 * This class supports finding the sum of divisors and printing them to console.
 * @version 0.01
 * @author Alex Ivashchenko
 */
public class DivisorsSum {
    /** Range of numbers to calculate divisors sum of */
    private int number;
    private List<Integer> numbers;

    public DivisorsSum(int number) {
        this.number = number;
        numbers = new LinkedList<>();
        calculateDividersSum();
    }

    private void calculateDividersSum() {
        for (int curNumber = 2; curNumber <= number; curNumber++) {
            int sum = 0;
            for (int curDiv = 1, prevDivider = curNumber; curDiv < prevDivider; curDiv++) {
                // Checking only the half of divisors
                if (curNumber % curDiv != 0) {
                    continue;
                }
                // Adding the current divisor and "mirror" divisor.
                sum += curDiv + ((curDiv != curNumber / curDiv) ? curNumber / curDiv : 0);
                prevDivider = curNumber / curDiv;
            }
            sum -= curNumber;
            if(sum == curNumber) numbers.add(sum);
        }
    }

    public void printNumbers() {
        for(int num: numbers){
            System.out.println(num);
        }
        System.out.println("Number of perfect numbers = " + numbers.size());
    }
}
