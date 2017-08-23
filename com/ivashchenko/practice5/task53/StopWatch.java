package com.ivashchenko.practice5.task53;

/** Simple class providing service of time measurement. */
public class StopWatch {
    private static long startTime;
    private static long endTime;
    private static boolean isActive = false;

    public static void start() {
        startTime = System.currentTimeMillis();
        isActive = true;
    }

    public static void stop() {
        if (!isActive) return;
        endTime = System.currentTimeMillis();
        isActive = false;
    }

    public static double getElapsedSeconds() {
        return (endTime - startTime) / 1000.0;
    }
}
