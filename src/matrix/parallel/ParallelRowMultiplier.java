package matrix.parallel;

import matrix.parallel.task.RowMultiplierTask;

import java.util.ArrayList;
import java.util.List;

public class ParallelRowMultiplier {
    public static void multiply(double[][] matrix1, double[][] matrix2, double[][] result) {
        int rows = matrix1.length;
        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < rows; ++i) {
            RowMultiplierTask task = new RowMultiplierTask(matrix1, matrix2, result, i);
            Thread thread = new Thread(task);
            thread.start();
            threads.add(thread);

            if (threads.size() % 10 == 0) {
                waitForThreads(threads);
            }
        }
    }

    private static void waitForThreads(List<Thread> threads) {
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        threads.clear();
    }
}
