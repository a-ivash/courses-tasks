package com.ivashchenko.practice5.task52;

/** This class demonstrates parallel work of 2 threads: Counter and Printer. */
public class ThreadContainer {
    // Common resource for threads to work with
    private DataShare dataShare;
    Thread counter;
    Thread printer;

    public ThreadContainer() {
        dataShare = new DataShare();
        counter = new Thread(new Counter(dataShare));
        printer = new Thread(new Printer(dataShare));
    }

    public void runThreads(){
        counter.start();
        printer.start();
    }
}
