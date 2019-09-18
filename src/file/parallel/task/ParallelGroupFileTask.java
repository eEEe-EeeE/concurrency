package file.parallel.task;

import comm.file.Result;

import java.io.File;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ParallelGroupFileTask implements Runnable {

    private final String fileName;

    private final ConcurrentLinkedQueue<File> directories;

    private final Result parallelResult;

    private boolean found;

    public Boolean isFound() {
        return found;
    }

    public ParallelGroupFileTask(String fileName, ConcurrentLinkedQueue<File> directories, Result parallelResult) {
        this.fileName = fileName;
        this.directories = directories;
        this.parallelResult = parallelResult;
        this.found = false;
    }

    @Override
    public void run() {
        while (directories.size() > 0) {
            File file = directories.poll();
            try {
                processDirectory(file, fileName, parallelResult);
                if (found) {
                    System.out.printf("%s has found the file.\n", Thread.currentThread().getName());
                    System.out.printf("Parallel Search: Path: %s\n", parallelResult.getPath());
                    return;
                }
            } catch (InterruptedException e) {
                System.out.printf("%s has been interrupted\n", Thread.currentThread().getName());
            }
        }
    }

    private void processDirectory(File file, String fileName, Result parallelResult) throws InterruptedException {
        File[] contents;
        contents = file.listFiles();

        if ((contents == null) || (contents.length == 0)) {
            return;
        }

        for (File content : contents) {
            if (content.isDirectory()) {
                processDirectory(content, fileName, parallelResult);

            } else {
                processNormalFile(content, fileName, parallelResult);

            }
            if (Thread.currentThread().isInterrupted()) {
                throw new InterruptedException();
            }
            if (found) {
                return;
            }
        }
    }

    private void processNormalFile(File file, String fileName, Result parallelResult) {
        if (file.getName().equals(fileName)) {
            parallelResult.setPath(file.getAbsolutePath());
            parallelResult.setFound(true);
            found = true;
        }
    }
}
