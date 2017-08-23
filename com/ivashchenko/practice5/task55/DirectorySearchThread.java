package com.ivashchenko.practice5.task55;

import java.io.File;
import java.util.concurrent.ExecutorService;

/** Class that recursively proceed every sub directory.*/
public class DirectorySearchThread implements Runnable {
    private File fileToSearch;
    private ExecutorService pool;
    private String pathToWrite;
    private boolean isRootProcess;

    public DirectorySearchThread(File fileToSearch, String pathToWrite, ExecutorService pool, boolean isRoot) {
        this.fileToSearch = fileToSearch;
        this.pathToWrite = pathToWrite;
        this.pool = pool;
        this.isRootProcess = isRoot;
    }

    @Override
    public void run() {
        // Create new directory to write modified files.
        File dir = new File(pathToWrite);
        if(!dir.exists())
            dir.mkdir();

        for (File file: fileToSearch.listFiles()) {
            if (file.isDirectory()) {
                String newPath = pathToWrite + file.getName() + "\\";
                // Recursively proceed sub directory
                pool.submit(new DirectorySearchThread(file, newPath, pool, false));
                continue;
            }

            System.out.println("Looking at file: " + file.getAbsolutePath());
            if (file.getName().endsWith(".java")) {
                File output = new File(pathToWrite + file.getName());
                CommentCleaner.clean(file, output);
            }
        }
        System.out.println("DONE dir: " + fileToSearch.getAbsolutePath());
        if(isRootProcess) {
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//
//            }
//            pool.shutdown();
        }
    }
}
