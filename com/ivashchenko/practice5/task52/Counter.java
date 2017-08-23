package com.ivashchenko.practice5.task52;

/** Class for incrementing value in DataShare instance. */
public class Counter implements Runnable {
    DataShare dataShare;
    public Counter(DataShare dataShare) {
        this.dataShare = dataShare;
    }

    @Override
    public void run() {
        while (!dataShare.isCompleted()) {
            dataShare.incrementCounter();
        }
    }
}
