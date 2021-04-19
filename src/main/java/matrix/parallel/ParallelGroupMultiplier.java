package matrix.parallel;

import matrix.parallel.task.GroupMultiplierTask;

import java.util.ArrayList;
import java.util.List;

public class ParallelGroupMultiplier {

    public static void multiply(double[][] matrix1, double[][] matrix2, double[][] result) {
        List<Thread> threads = new ArrayList<>();
        int numThreads = Runtime.getRuntime().availableProcessors();
        int rows = matrix1.length;

        int startIndex;
        int endIndex;
        int step;
        step = rows / numThreads;
        startIndex = 0;
        endIndex = step;

        for (int n = 0; n < numThreads; ++n) {
            GroupMultiplierTask task = new GroupMultiplierTask(matrix1, matrix2, result, startIndex, endIndex);
            Thread thread = new Thread(task);
            thread.start();
            threads.add(thread);
            startIndex = endIndex;
            endIndex = n == numThreads - 2 ? rows : endIndex + step;
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
