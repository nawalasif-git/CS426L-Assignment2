public class TelemetryThread extends Thread {

    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println(
                Thread.currentThread().getName()
                + " - System Health Update: " + i
            );

            try {
                Thread.sleep(800);
            } catch (InterruptedException e) {
                System.out.println("Telemetry thread interrupted.");
                Thread.currentThread().interrupt();
                return;
            }
        }
    }
}

