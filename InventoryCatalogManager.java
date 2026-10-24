import java.util.HashMap;
import java.util.Map;
import java.util.Collections;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class InventoryCatalogManager {

    private static final int THREADS = 10;
    private static final int OPERATIONS = 50000;
    private static final int PRODUCTS = 1000;

    private Map<String, Integer> inventory;

    public InventoryCatalogManager() {
    inventory = new HashMap<>();
    initializeInventory();
}

private void initializeInventory() {
    for (int i = 0; i < PRODUCTS; i++) {
        inventory.put("P" + i, 100);
    }
}
private void resetInventory() {
    inventory.clear();
    initializeInventory();
}
private String generateProductId(int operation, int worker) {
    return "P" + ((operation + worker) % PRODUCTS);
}
    public void benchmarkSynchronizedMap() throws InterruptedException {
        inventory = Collections.synchronizedMap(new HashMap<>());

        for (int i = 0; i < PRODUCTS; i++) {
            inventory.put("P" + i, 100);
        }
        runBenchmark("Collections.synchronizedMap");
    }
    public void benchmarkConcurrentMap() throws InterruptedException {
        inventory = new ConcurrentHashMap<>();

        for (int i = 0; i < PRODUCTS; i++) {
            inventory.put("P" + i, 100);
        }
        runBenchmark("ConcurrentHashMap");
    }
    private void runBenchmark(String mapName) throws InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(THREADS);
        AtomicLong completedOperations = new AtomicLong(0);

        long startTime = System.nanoTime();

        for (int t = 0; t < THREADS; t++) {
            final int worker = t;
            pool.execute(() -> {
                for (int i = 0; i < OPERATIONS / THREADS; i++) {
                    String productId = generateProductId(i, worker);

                    Integer stock = inventory.get(productId);

                    if (stock != null) {
                        inventory.put(productId, stock - 1);
                    }
                    completedOperations.incrementAndGet();
                }
            });
        }
        pool.shutdown();
        pool.awaitTermination(1, TimeUnit.HOURS);
        long endTime = System.nanoTime();
        double seconds = (endTime - startTime) / 1_000_000_000.0;
        double throughput = completedOperations.get() / seconds;

        System.out.println("\nCollection: " + mapName);
        System.out.println("Worker Threads: " + THREADS);
        System.out.println("Completed Operations: "
                + completedOperations.get());
        System.out.printf("Execution Time: %.4f seconds%n", seconds);
        System.out.printf("Throughput: %.2f operations/second%n",
                throughput);
    }
    public static void main(String[] args) throws InterruptedException {
        InventoryCatalogManager manager = new InventoryCatalogManager();
        System.out.println("INVENTORY CATALOG CONCURRENCY BENCHMARK");
        manager.benchmarkSynchronizedMap();
        manager.benchmarkConcurrentMap();
    }
}
