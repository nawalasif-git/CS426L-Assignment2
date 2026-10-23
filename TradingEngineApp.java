public class TradingEngineApp {
    public static void main(String[] args) {
        TradeEvent event1 = new FIXOrderEvent(
            "FIX-Gateway",
            System.currentTimeMillis(),
            150.00,
            "ORD-1001"
        );

        TradeEvent event2 = new ReutersQuoteEvent(
            "Reuters-Gateway",
            System.currentTimeMillis(),
            150.00,
            149.80,
            150.20
        );

        event1.dispatch();
        event2.dispatch();
    }
}
