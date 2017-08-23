package com.ivashchenko.practice5.task53;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/** This class contains an array of threads trying to write/read to/from Map instance. */
public class ThreadContainer {
    private static final int INITIAL_CAPACITY = 16;
    private static final int THREADS_NUMBER = 4;
    private static final int NUMBER_OF_RECORDS = 10000000;
    private Map<Integer, String> data;

    private Thread[] readThreads = new Thread[THREADS_NUMBER];
    private Thread[] writeThreads = new Thread[THREADS_NUMBER];

    /** Used to initialize array of threads with readers and writers from/to some Map */
    private void initThreadArray(Map<Integer, String> data, boolean isConcurrent){
        this.data = data;
        for (int i = 0; i < THREADS_NUMBER; i++) {
            readThreads[i] = new Thread(new MapReader(data,
                    0, NUMBER_OF_RECORDS, isConcurrent),
                    "ReadThread" + i);
            writeThreads[i] = new Thread(new MapWriter(data,
                    0, NUMBER_OF_RECORDS, isConcurrent),
                    "WriteThread" + i);
        }
    }

    private void useConcurrentHashMap() {
        initThreadArray(new ConcurrentHashMap<>(INITIAL_CAPACITY), true);
    }

    private void useHashMap() {
        initThreadArray(new HashMap<>(INITIAL_CAPACITY), false);
    }

    /** Starts all read/write threads and joins them. Allows to measure running time.*/
    private void startThreads() {
        for (Thread writeThread: writeThreads) {
            writeThread.start();
        }
        for (Thread readThread: readThreads) {
            readThread.start();
        }

        for (Thread writeThread: writeThreads) {
            try {
                writeThread.join();
            } catch (InterruptedException e) { }
        }

        for (Thread readThread: readThreads) {
            try {
                readThread.join();
            } catch (InterruptedException e) { }
        }
    }

    public void measureThreadWorkTime() {
        System.out.println("Number of threads: " + THREADS_NUMBER);
        System.out.println("Number of records: " + NUMBER_OF_RECORDS);
        System.out.println("Initial capacity of Map instance: " + INITIAL_CAPACITY);

        useConcurrentHashMap(); // filling array of threads with instance working with ConcurrentHashMap
        StopWatch.start(); // starting time measurement
        startThreads();
        StopWatch.stop();
        System.out.println("Concurrent operations finished in " + StopWatch.getElapsedSeconds() + " seconds");

        useHashMap();
        StopWatch.start();
        startThreads();
        StopWatch.stop();
        System.out.println("Sync operations finished in " + StopWatch.getElapsedSeconds() + " seconds");
    }
}
