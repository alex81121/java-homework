package homwork9;
public class MyStack {

    private Node head;
    private int size;

    private static class Node {
        Object value;
        Node next;

        Node(Object value) {
            this.value = value;
        }
    }

    // Додає елемент в кінець стеку
    public void push(Object value) {
        Node newNode = new Node(value);

        if (head == null) {
            head = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }

        size++;
    }

    // Видаляє елемент за індексом
    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Невірний індекс");
        }

        if (index == 0) {
            head = head.next;
            size--;
            return;
        }

        Node current = head;

        for (int i = 0; i < index - 1; i++) {
            current = current.next;
        }

        current.next = current.next.next;
        size--;
    }

    // Очищає стек
    public void clear() {
        head = null;
        size = 0;
    }

    // Повертає розмір стеку
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
    public Object pop() {
        if (head == null) {
            return null;
        }

        Object value = head.value;

        head = head.next;
        size--;

        return value;
    }
}

