package com.ivashchenko.practice5.task53;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/** Runnable instance trying to read data from Map instance. */
public class MapReader implements Runnable {
    private final Map<Integer, String> mapToRead;
    private int from;
    private int to;
    /** Used to avoid synchronized block for ConcurrentHashMap */
    private boolean isConcurrent;

    public MapReader(Map<Integer, String> mapToRead, int from, int to, boolean isConcurrent) {
        this.mapToRead = mapToRead;
        this.from = from;
        this.to = to;
        this.isConcurrent = isConcurrent;
    }

    @Override
    public void run() {
        for (int i = from; i <= to; i++) {
           if(isConcurrent) {
               mapToRead.get(i);
               continue;
           }
           synchronized (mapToRead) {
                while (!mapToRead.containsKey(i)) {
                    try {
                        /* Waiting for necessary key to be available in KeySet of mapToRead. */
                        mapToRead.wait();
                    } catch (InterruptedException e) { }
                }
                mapToRead.get(i);
            }
        }
    }
}
