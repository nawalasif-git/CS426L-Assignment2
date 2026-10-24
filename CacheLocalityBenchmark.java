public class CacheLocalityBenchmark {

    private static final int N = 2000;
    private static final int ITERATIONS = 10;

    private static double[][] matrix = new double[N][N];
    public static double traverseRowMajor() {
    double sum = 0;

    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            sum += matrix[i][j];
        }
    }

    return sum;
}
public static double traverseColumnMajor() {
    double sum = 0;

    for (int j = 0; j < N; j++) {
        for (int i = 0; i < N; i++) {
            sum += matrix[i][j];
        }
    }

    return sum;
}
public static void main(String[] args) {

    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            matrix[i][j] = i + j;
        }
    }

    double rowSum = 0;
    double columnSum = 0;

    long rowStart = System.nanoTime();

    for (int i = 0; i < ITERATIONS; i++) {
        rowSum = traverseRowMajor();
    }

    long rowEnd = System.nanoTime();

    long columnStart = System.nanoTime();

    for (int i = 0; i < ITERATIONS; i++) {
        columnSum = traverseColumnMajor();
    }

    long columnEnd = System.nanoTime();
}
}