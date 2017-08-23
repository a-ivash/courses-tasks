package com.ivashchenko.practice5.task53;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/** Runnable instance writing values to Map instance.*/
public class MapWriter implements Runnable {
    private final Map<Integer, String> mapToWrite;
    private int from;
    private int to;
    /** Used to avoid synchronized block for ConcurrentHashMap */
    private boolean isConcurrent;

    public MapWriter(Map<Integer, String> mapToWrite, int from, int to, boolean isConcurrent) {
        this.mapToWrite = mapToWrite;
        this.from = from;
        this.to = to;
        this.isConcurrent = isConcurrent;
    }

    @Override
    public void run() {
        for (int i = from; i <= to; i++) {
            if (isConcurrent) {
                mapToWrite.put(i, Thread.currentThread().getName());
                continue;
            }
            /* Otherwise block resource. */
            synchronized (mapToWrite) {
                mapToWrite.put(i, Thread.currentThread().getName());
                mapToWrite.notifyAll(); // Notifying every waiting thread that new value is available.
            }

        }
    }
}
