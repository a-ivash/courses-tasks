package com.ivashchenko.practice5.task51;

/** This class demonstrates work with Thread.sleep method.*/
public class ThreadRunner {
    public void runThread() {
        Thread thread = new Thread(){
            final static long SLEEP_TIME = 1000;
            @Override
            public void run() {
                try {
                    for (int i = 10; i > 0; i--) {
                        System.out.println(i);
                        sleep(SLEEP_TIME);
                    }
                    System.out.println("Bomb");
                } catch (InterruptedException e) {

                }
            }
        };
        thread.start();
    }
}
