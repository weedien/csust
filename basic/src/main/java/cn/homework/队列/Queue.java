package cn.homework.队列;

class Queue {
    private int[] element;
    private int size;
    private int capacity;

    public Queue() {
        this.capacity = 8;
        this.element = new int[capacity];
        this.size = 0;
    }

    public int deQueue() {
        if (size == 0) {
            throw new RuntimeException("队列为空");
        }
        int value = element[0];
        for (int i = 1; i < size; i++) {
            element[i - 1] = element[i];
        }
        size--;
        return value;
    }

    public void enQueue(int v) {
        // 队列长度等于容量时，进行扩容
        if (size == capacity) {
            int[] newElement = new int[capacity * 2];
            System.arraycopy(element, 0, newElement, 0, size);
            element = newElement;
            capacity *= 2;
        }
        element[size] = v;
        size++;
    }

    public boolean empty() {
        return size == 0;
    }

    public int getSize() {
        return size;
    }

    public static void main(String[] args) {
        Queue queue = new Queue();
        System.out.println("队列是否为空 " + queue.empty());
        System.out.println("队列长度: " + queue.getSize());

        queue.enQueue(1);
        queue.enQueue(2);
        queue.enQueue(3);

        System.out.println("移除队首元素: " + queue.deQueue());
        System.out.println("队列长度: " + queue.getSize());

        queue.enQueue(4);
        queue.enQueue(5);
        queue.enQueue(6);
        queue.enQueue(7);
        queue.enQueue(8);
        queue.enQueue(9);
        queue.enQueue(10);

        System.out.println("移除队首元素: " + queue.deQueue());
        System.out.println("队列长度: " + queue.getSize());
        System.out.println("队列是否为空" + queue.empty());
    }
}
