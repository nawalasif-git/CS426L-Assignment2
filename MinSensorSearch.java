public class MinSensorSearch extends SensorSearchEngine {

    public MinSensorSearch(long[] readings, int start, int end,
                           String threadName) {
        super(readings, start, end, threadName);
    }

    @Override
    protected long search() {
        long minimum = readings[start];

        for (int i = start + 1; i < end; i++) {
            if (readings[i] < minimum) {
                minimum = readings[i];
            }
        }

        System.out.println(getName() + " minimum: " + minimum);
        return minimum;
    }
}

