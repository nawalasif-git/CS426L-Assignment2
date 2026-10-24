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
        double rowTimeMs =
                (rowEnd - rowStart) / 1_000_000.0;

        double columnTimeMs =
                (columnEnd - columnStart) / 1_000_000.0;

        double speedup = columnTimeMs / rowTimeMs;

        System.out.println("CACHE LOCALITY BENCHMARK");
        System.out.println("Matrix Size: " + N + " x " + N);
        System.out.println("Iterations: " + ITERATIONS);
        System.out.println();

        System.out.printf("Row-Major Time: %.3f ms%n", rowTimeMs);
        System.out.printf("Column-Major Time: %.3f ms%n",columnTimeMs);
        System.out.printf("Row-Major Sum: %.2f%n", rowSum);
        System.out.printf("Column-Major Sum: %.2f%n", columnSum);
        System.out.printf("Row-Major Speedup: %.2fx%n", speedup);
    }
}

