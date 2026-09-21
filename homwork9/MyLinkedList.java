package homwork9;

public class MyLinkedList<E> {

    private Node<E> head;
    private Node<E> tail;
    private int size;

    // Node - елемент двозв'язного списку
    private static class Node<E> {
        E value;
        Node<E> previous;
        Node<E> next;

        Node(E value) {
            this.value = value;
        }
    }

    // Додає елемент в кінець
    public void add(E value) {
        Node<E> newNode = new Node<>(value);

        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.previous = tail;
            tail.next = newNode;
            tail = newNode;
        }

        size++;
    }

    // Видаляє елемент за індексом
    public void remove(int index) {
        Node<E> current = getNode(index);

        if (current.previous != null) {
            current.previous.next = current.next;
        } else {
            head = current.next;
        }

        if (current.next != null) {
            current.next.previous = current.previous;
        } else {
            tail = current.previous;
        }

        size--;
    }

    // Очищає список
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    // Повертає розмір
    public int size() {
        return size;
    }

    // Повертає елемент за індексом
    public E get(int index) {
        return getNode(index).value;
    }

    // Знаходить Node за індексом
    private Node<E> getNode(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Невірний індекс");
        }

        Node<E> current;

        // Якщо індекс ближче до початку
        if (index < size / 2) {
            current = head;

            for (int i = 0; i < index; i++) {
                current = current.next;
            }
        }
        // Якщо індекс ближче до кінця
        else {
            current = tail;

            for (int i = size - 1; i > index; i--) {
                current = current.previous;
            }
        }

        return current;
    }
}

