public class RequestQueue {
    private int[] queue;
    private int front;
    private int rear;
    private int size;
    private int capacity;

    public RequestQueue(int capacity) {
        this.capacity = capacity;
        queue = new int[capacity];
        front = 0;
        rear = -1;
        size = 0;
    }

    public void enqueue(int requestId) {
        if (size == capacity) {
            System.out.println("Queue is full");
            return;
        }

        rear = (rear + 1) % capacity;
        queue[rear] = requestId;
        size++;
    }

    public int dequeue() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return -1;
        }

        int requestId = queue[front];
        front = (front + 1) % capacity;
        size--;
        return requestId;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
