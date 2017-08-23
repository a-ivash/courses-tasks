package com.ivashchenko.practice5.task55;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/** This class contains executors pool and starting directory
 * to handle. */
public class CleanerThreadContainer {
    private ExecutorService pool;
    private File startDirectory;
    private String directoryToWrite;
    private static final int NUMBER_OF_THREADS = 4;

    public CleanerThreadContainer(){
        pool = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    }

    public void runSearch() {
        handleInput();
        DirectorySearchThread dst = new DirectorySearchThread(startDirectory, directoryToWrite, pool, true);
        pool.submit(dst);
        try{
            pool.awaitTermination(1, TimeUnit.SECONDS);
        }catch (InterruptedException e){

        }finally {
            pool.shutdown();
        }

    }

    /** Simply asking for a valid starting directory location and the destination path. */
    public void handleInput() {
        startDirectory = PathHandler.askForFile("Enter path of start directory: ");
        directoryToWrite = PathHandler.askForDirectory("Enter directory to write file to: ");
        directoryToWrite += startDirectory.getName() + "/";
    }
}