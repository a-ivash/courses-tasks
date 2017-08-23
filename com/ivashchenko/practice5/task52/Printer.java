package com.ivashchenko.practice5.task52;

/** Class for getting value from DataShare instance and printing it. */
public class Printer implements Runnable {
    DataShare dataShare;
    public Printer(DataShare dataShare) {
        this.dataShare = dataShare;
    }

    @Override
    public void run() {
        while (!dataShare.isCompleted()) {
            System.out.println(dataShare.getCounter());
        }
    }
}
