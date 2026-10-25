import java.util.concurrent.RecursiveTask;

public class ParallelRiskCalculator extends RecursiveTask<Double> {
    private double[] risks;
    private int start;
    private int end;
    private int threshold;
    
    public ParallelRiskCalculator(double[] risks, int start, int end, int threshold) {
        this.risks = risks;
        this.start = start;
        this.end = end;
        this.threshold = threshold;
    }

    @Override
    protected Double compute() {
        if (end - start <= threshold) {
            double total = 0;
            for (int i = start; i < end; i++) {
                total += risks[i];
            }
            return total;
        }
    }

        int middle = (start + end) / 2;

        ParallelRiskCalculator task1 =
                new ParallelRiskCalculator(risks, start, middle, threshold);
        ParallelRiskCalculator task2 =
                new ParallelRiskCalculator(risks, middle, end, threshold);

        task1.fork();
        double result2 = task2.compute();
        double result1 = task1.join();

        return result1 + result2;

}

