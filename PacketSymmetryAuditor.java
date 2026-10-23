public class PacketSymmetryAuditor {
    public static void main(String[] args) {
        String packet = "RADAR";

        TransactionStack stack = new TransactionStack(packet.length());
        RequestQueue queue = new RequestQueue(packet.length());

        for (int i = 0; i < packet.length(); i++) {
            int value = packet.charAt(i);
            stack.push(value);
            queue.enqueue(value);
        }

        boolean isPalindrome = true;

        for (int i = 0; i < packet.length(); i++) {
            int stackValue = stack.pop();
            int queueValue = queue.dequeue();

            if (stackValue != queueValue) {
                isPalindrome = false;
                break;
            }
        }

        System.out.println("Packet: " + packet);

        if (isPalindrome) {
            System.out.println("The packet is a palindrome.");
        } else {
            System.out.println("The packet is not a palindrome.");
        }
    }
}
