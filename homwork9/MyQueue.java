package homwork9;
public class MyQueue {

    private Node head;
    private Node tail;
    private int size;

    private static class Node {
        Object value;
        Node next;

        Node(Object value) {
            this.value = value;
        }
    }

    // Додає елемент в кінець черги
    public void add(Object value) {
        Node newNode = new Node(value);

        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }

        size++;
    }

    // Очищає чергу
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    // Повертає розмір черги
    public int size() {
        return size;
    }

    // Повертає перший елемент, але не видаляє його
    public Object peek() {
        if (head == null) {
            return null;
        }

        return head.value;
    }

    // Повертає перший елемент і видаляє його
    public Object poll() {
        if (head == null) {
            return null;
        }

        Object value = head.value;

        head = head.next;
        size--;

        if (head == null) {
            tail = null;
        }

        return value;
    }
}

