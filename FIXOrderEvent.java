public class FIXOrderEvent extends TradeEvent {
    private String clOrdId;

    public FIXOrderEvent(String senderId, long timestamp,
                         double basePrice, String clOrdId) {
        super(senderId, timestamp, basePrice);
        this.clOrdId = clOrdId;
    }

    @Override
    public void dispatch() {
        double fee = basePrice * 0.0015;
        double totalCost = basePrice + fee;

        System.out.println("\nFIX Stock Purchase Order");
        System.out.println("Sender ID: " + senderId);
        System.out.println("Order ID: " + clOrdId);
        System.out.printf("Stock Price: %.2f%n", basePrice);
        System.out.printf("Transaction Fee (0.15%%): %.4f%n", fee);
        System.out.printf("Total Cost: %.4f%n", totalCost);
    }
}

