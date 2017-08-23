package com.ivashchenko.practice5.task52;

/** This class contains current integer value.
 * Counter class instance tries to increment it. Printer instance gets this value.*/
public class DataShare {
    private int counter = 0;
    private static int expected = 1;
    private static final int MAX_VALUE = 1_000_000;

    public synchronized int getCounter() {
        while(counter < expected) {
            try{
                wait();
            } catch (InterruptedException e){

            }
        }
        expected ++;
        notify();
        return counter;
    }

    public synchronized void incrementCounter() {
        while (this.counter == expected){
            try {
                wait();
            } catch (InterruptedException e) {

            }
        }
        this.counter ++;
        notify();
    }

    public synchronized boolean isCompleted() {
        return counter >= MAX_VALUE;
    }

}
