package matrix.parallel.task;

public class RowMultiplierTask implements Runnable {
    private double[][] matrix1;
    private double[][] matrix2;
    private double[][] result;

    private int row;

    public RowMultiplierTask(double[][] matrix1, double[][] matrix2, double[][] result, int row) {
        this.matrix1 = matrix1;
        this.matrix2 = matrix2;
        this.result = result;
        this.row = row;
    }

    @Override
    public void run() {
        for (int j = 0; j < matrix2[0].length; ++j) {
            result[row][j] = 0;
            for (int k = 0; k < matrix2.length; ++k) {
                result[row][j] += matrix1[row][k] * matrix2[k][j];
            }
        }
    }
}
