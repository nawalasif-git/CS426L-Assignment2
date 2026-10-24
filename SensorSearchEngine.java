public abstract class SensorSearchEngine extends Thread {
    protected long[] readings;
    protected int start;
    protected int end;
    protected long result;

    public SensorSearchEngine(long[] readings, int start, int end,
                              String threadName) {
        this.readings = readings;
        this.start = start;
        this.end = end;
        setName(threadName);
    }

    protected abstract long search();

    @Override
    public void run() {
        result = search();
    }

    public long getResult() {
        return result;
    }
}
