import java.util.concurrent.ForkJoinPool;

public class PortfolioRiskApp {
    public static void main(String[] args) {
        int size = 10000;
        int threshold = 100;
        double[] risks = new double[size];

        for (int i = 0; i < size; i++) {
            risks[i] = 1 + (i % 100);
        }
        ForkJoinPool pool = new ForkJoinPool();

        long startTime = System.currentTimeMillis();

        double totalRisk = pool.invoke(
                new ParallelRiskCalculator(risks, 0, size, threshold));

        long endTime = System.currentTimeMillis();

        System.out.println("Total Portfolio Risk: " + totalRisk);
        System.out.println("Parallel Calculation Time: " + (endTime - startTime) + " ms");
        long adjustmentStart = System.currentTimeMillis();
        pool.invoke(new PortfolioThresholdTask(risks, 0, size, threshold));

        long adjustmentEnd = System.currentTimeMillis();
        System.out.println("Risk Adjustment Completed Successfully.");
        System.out.println("Adjusted Portfolio Risk: "
                + pool.invoke(new ParallelRiskCalculator(
                        risks, 0, size, threshold)));
        System.out.println("Adjustment Time: "
                + (adjustmentEnd - adjustmentStart) + " ms");
        pool.shutdown();
    }
}
