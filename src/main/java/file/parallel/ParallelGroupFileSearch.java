package file.parallel;

import comm.file.Result;
import file.parallel.task.ParallelGroupFileTask;

import java.io.File;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ParallelGroupFileSearch {
    public static void searchFiles(File file, String fileName, Result parallelResult) {
        ConcurrentLinkedQueue<File> directories = new ConcurrentLinkedQueue<>();
        File[] contents = file.listFiles();

        if ((contents == null) || (contents.length == 0)) {
            return;
        }
        for (File content : contents) {
            if (content.isDirectory()) {
                directories.offer(content);
            }
        }

        int numThreads = Runtime.getRuntime().availableProcessors();
        ParallelGroupFileTask[] tasks = new ParallelGroupFileTask[numThreads];
        Thread[] threads = new Thread[tasks.length];

        for (int index = 0; index < numThreads; ++index) {
            tasks[index] = new ParallelGroupFileTask(fileName, directories, parallelResult);
            threads[index] = new Thread(tasks[index]);
            threads[index].start();
        }

        boolean finish = false;
        int numFinished = 0;
        while (!finish) {
            numFinished = 0;
            for (int index = 0; index < numThreads; ++index) {
                if (threads[index].getState() == Thread.State.TERMINATED) {
                    ++numFinished;
                    if (tasks[index].isFound()) {
                        finish = true;
                    }
                }
            }
            if (numFinished == numThreads) {
                finish = true;
            }
        }

        if (numFinished != numThreads) {
            for (Thread thread : threads) {
                thread.interrupt();
            }
        }
    }
}
