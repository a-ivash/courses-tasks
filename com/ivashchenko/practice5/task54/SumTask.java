package com.ivashchenko.practice5.task54;

import java.util.concurrent.RecursiveTask;

/** This class describes the recursive task of finding sum of sub array. */
public class SumTask extends RecursiveTask<Long>{
    private int start;
    private int end;
    private int[] numbers;

    public SumTask(int start, int end, int[] numbers) {
        this.start = start;
        this.end = end;
        this.numbers = numbers;
    }

    @Override
    protected Long compute() {
        // Base condition. The simplest task.
        if (end - start < 20) {
            long sum = 0;
            for (int i = start; i < end; i++) {
                sum += numbers[i];
            }
            return sum;
        }

        // Otherwise get the index of middle element.
        int middle = (end + start) / 2;
        // And start recursive tasks for left and right part recursively.
        SumTask part1 = new SumTask(start, middle, numbers);
        part1.fork();
        SumTask part2 = new SumTask(middle, end, numbers);

        long result = part2.compute();
        return part1.join() + result;
    }
}
