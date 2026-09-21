package homwork9;

public class MyQueue<E> {

    private Node<E> head;
    private Node<E> tail;
    private int size;

    // Node - елемент черги
    private static class Node<E> {
        E value;
        Node<E> next;

        Node(E value) {
            this.value = value;
        }
    }

    // Додає елемент в кінець черги
    public void add(E value) {
        Node<E> newNode = new Node<>(value);

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
    public E peek() {
        if (head == null) {
            return null;
        }

        return head.value;
    }

    // Повертає перший елемент і видаляє його
    public E poll() {
        if (head == null) {
            return null;
        }

        E value = head.value;

        head = head.next;
        size--;

        if (head == null) {
            tail = null;
        }

        return value;
    }
}


