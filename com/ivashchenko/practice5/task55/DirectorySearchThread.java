package com.ivashchenko.practice5.task55;

import java.io.File;
import java.util.concurrent.ExecutorService;

/** Class that recursively proceed every sub directory.*/
public class DirectorySearchThread implements Runnable {
    private File currentDirectory;
    private ExecutorService pool;
    private File outputDirectory;

    public DirectorySearchThread(File currentDirectory, File outputDirectory, ExecutorService pool) {
        this.currentDirectory = currentDirectory;
        this.outputDirectory = outputDirectory;
        this.pool = pool;

        createOutputDirectory();
    }

    /** Creates new directory to write modified files to. */
    private void createOutputDirectory() {
        if(!outputDirectory.exists())
            outputDirectory.mkdir();
    }

    private void deleteDirectoryIfEmpty() {
        if(outputDirectory!= null && outputDirectory.listFiles().length == 0) {
            outputDirectory.delete();
        }
    }

    @Override
    public void run() {
        for (File currentFile: currentDirectory.listFiles()) {
            if (currentFile.isDirectory()) {
                File newOutputDirectory = new File(outputDirectory, currentFile.getName());

                // Recursively proceed sub directory
                pool.submit(new DirectorySearchThread(currentFile, newOutputDirectory, pool));
                continue;
            }

            System.out.println("Looking at file: " + currentFile.getAbsolutePath());
            if (currentFile.getName().endsWith(".java")) {
                File output = new File(outputDirectory, currentFile.getName());
                CommentCleaner.clean(currentFile, output);
            }
        }
        System.out.println("DONE dir: " + currentDirectory.getAbsolutePath());

        // removing output directory if no files were written
        deleteDirectoryIfEmpty();
    }
}
