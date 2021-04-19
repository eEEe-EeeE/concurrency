package matrix.parallel.task;

public class GroupMultiplierTask implements Runnable {
    private double[][] matrix1;
    private double[][] matrix2;
    private double[][] result;

    private int startIndex;
    private int endIndex;

    public GroupMultiplierTask(double[][] matrix1, double[][] matrix2, double[][] result, int startIndex, int endIndex) {
        this.matrix1 = matrix1;
        this.matrix2 = matrix2;
        this.result = result;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }

    @Override
    public void run() {
        for (int i = startIndex; i < endIndex; ++i) {
            for (int j = 0; j < matrix2[0].length; ++j) {
                result[i][j] = 0;
                for (int k = 0; k < matrix2.length; ++k) {
                    result[i][j] += matrix1[i][k] * matrix2[k][j];
                }
            }
        }
    }
}
