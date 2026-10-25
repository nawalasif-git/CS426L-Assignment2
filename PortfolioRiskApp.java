import java.util.concurrent.ForkJoinPool;
public class PortfolioRiskApp {

    public static void main(String[] args) {

        double[] risks = {
            0.10, 0.15, 0.20, 0.25, 0.30,
            0.12, 0.18, 0.22, 0.28, 0.35
        };
        int threshold = 2;
        PortfolioThresholdTask task =
    new PortfolioThresholdTask(risks, 0, risks.length, threshold);

ForkJoinPool pool = new ForkJoinPool();
pool.invoke(task);
pool.shutdown();
    }
}


