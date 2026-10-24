import java.util.Random;

public class SensorSearchApp {
    public static void main(String[] args) throws InterruptedException {
        int size = 50000;
        int workers = 4;
        long[] readings = new long[size];

        Random random = new Random(42);

        for (int i = 0; i < size; i++) {
            readings[i] = random.nextInt(100000);
        }
        SensorSearchEngine[] minTasks =
                new SensorSearchEngine[workers];
        SensorSearchEngine[] maxTasks =
                new SensorSearchEngine[workers];

        int chunkSize = (size + workers - 1) / workers;

        long startTime = System.currentTimeMillis();

        for (int i = 0; i < workers; i++) {
            int start = i * chunkSize;
            int end = Math.min(start + chunkSize, size);

            minTasks[i] = new MinSensorSearch(
                    readings, start, end, "Min-Worker-" + (i + 1));

            maxTasks[i] = new MaxSensorSearch(
                    readings, start, end, "Max-Worker-" + (i + 1));

            minTasks[i].start();
            maxTasks[i].start();
        }

        long minimum = Long.MAX_VALUE;
        long maximum = Long.MIN_VALUE;

        for (int i = 0; i < workers; i++) {
            minTasks[i].join();
            maxTasks[i].join();

            if (minTasks[i].getResult() < minimum) {
                minimum = minTasks[i].getResult();
            }

            if (maxTasks[i].getResult() > maximum) {
                maximum = maxTasks[i].getResult();
            }
        }

        long endTime = System.currentTimeMillis();

        System.out.println("\nTotal Sensor Readings: " + size);
        System.out.println("Overall Minimum: " + minimum);
        System.out.println("Overall Maximum: " + maximum);
        System.out.println("Total Worker Threads: " + (workers * 2));
        System.out.println("Execution Time: "
                + (endTime - startTime) + " ms");
    }
}

