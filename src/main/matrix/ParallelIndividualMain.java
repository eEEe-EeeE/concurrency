package main.matrix;

import comm.matrix.MatrixGenerator;
import matrix.parallel.ParallelIndividualMultiplier;

import java.util.Date;

public class ParallelIndividualMain {
    public static void main(String[] args) {
        double[][] matrix1 = MatrixGenerator.generate(2000, 2000);
        double[][] matrix2 = MatrixGenerator.generate(2000, 2000);
        double[][] resultParallelIndividual = new double[matrix1.length][matrix2.length];
        Date start = new Date();
        ParallelIndividualMultiplier.multiply(matrix1, matrix2, resultParallelIndividual);
        Date end = new Date();
        System.out.printf("ParallelIndividual: %d%n", end.getTime() - start.getTime());
    }
}
