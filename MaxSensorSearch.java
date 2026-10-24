public class MaxSensorSearch extends SensorSearchEngine {

    public MaxSensorSearch(long[] readings, int start, int end,
                           String threadName) {
        super(readings, start, end, threadName);
    }

    @Override
    protected long search() {
        long maximum = readings[start];

        for (int i = start + 1; i < end; i++) {
            if (readings[i] > maximum) {
                maximum = readings[i];
            }
        }

        System.out.println(getName() + " maximum: " + maximum);
        return maximum;
    }
}
