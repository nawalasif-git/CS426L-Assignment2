public class TransactionStack {
    private int[] stack;
    private int top;
    private int capacity;

    public TransactionStack(int capacity) {
        this.capacity = capacity;
        stack = new int[capacity];
        top = -1;
    }

    public void push(int transactionId) {
        if (top == capacity - 1) {
            System.out.println("Stack is full");
            return;
        }

        stack[++top] = transactionId;
    }

    public int pop() {
        if (isEmpty()) {
            System.out.println("Stack is empty");
            return -1;
        }

        return stack[top--];
    }

    public int peek() {
        if (isEmpty()) {
            System.out.println("Stack is empty");
            return -1;
        }

        return stack[top];
    }

    public boolean isEmpty() {
        return top == -1;
    }
}
