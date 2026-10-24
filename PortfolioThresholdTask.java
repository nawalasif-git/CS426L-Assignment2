import java.util.concurrent.RecursiveAction;
public class PortfolioThresholdTask extends RecursiveAction {
    private double[] risks;
    private int start;
    private int end;
    private int threshold;

    public PortfolioThresholdTask(double[] risks, int start, int end, int threshold) {
        this.risks = risks;
        this.start = start;
        this.end = end;
        this.threshold = threshold;
    }
    @Override
    protected void compute() {
        if (end - start <= threshold) {
            for (int i = start; i < end; i++) {
                if (risks[i] > 70) {
                    risks[i] = risks[i] * 1.05;
                }
            }
        } else {
            int middle = (start + end) / 2;

            PortfolioThresholdTask task1 =
                    new PortfolioThresholdTask(risks, start, middle, threshold);
            PortfolioThresholdTask task2 =
                    new PortfolioThresholdTask(risks, middle, end, threshold);
            task1.fork();
            task2.compute();
            task1.join();
        }
    }
}
