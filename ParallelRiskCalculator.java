import java.util.concurrent.RecursiveTask;

public class ParallelRiskCalculator extends RecursiveTask<Double> {
    private double[] risks;
    private int start;
    private int end;
    private int threshold;
}

