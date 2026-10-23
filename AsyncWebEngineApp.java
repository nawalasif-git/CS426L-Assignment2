public class AsyncWebEngineApp {

    public static void main(String[] args) {

        System.out.println("Main Thread: "
                + Thread.currentThread().getName());

        LogProcessorTask logTask = new LogProcessorTask();
        Thread logThread = new Thread(logTask, "Log-Worker");

        TelemetryThread telemetryThread = new TelemetryThread();
        telemetryThread.setName("Telemetry-Worker");

        logThread.start();
        telemetryThread.start();

        System.out.println("Log Thread Active: "
                + logThread.isAlive());

        System.out.println("Telemetry Thread Active: "
                + telemetryThread.isAlive());
    }
}
