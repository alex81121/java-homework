package homwork9;

public class MyStack<E> {

    private Node<E> head;
    private int size;

    // Node - елемент стеку
    private static class Node<E> {
        E value;
        Node<E> next;

        Node(E value) {
            this.value = value;
        }
    }

    // Додає елемент на вершину стеку
    public void push(E value) {
        Node<E> newNode = new Node<>(value);

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

        // Видалення першого елемента
        if (index == 0) {
            head = head.next;
            size--;
            return;
        }

        Node<E> current = head;

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
    public E peek() {
        if (head == null) {
            return null;
        }

        return head.value;
    }

    // Повертає перший елемент і видаляє його
    public E pop() {
        if (head == null) {
            return null;
        }

        E value = head.value;

        head = head.next;
        size--;

        return value;
    }
}



