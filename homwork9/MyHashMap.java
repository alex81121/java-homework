package homwork9;
public class MyHashMap {

    private Node[] table;
    private int size;

    private static class Node {
        Object key;
        Object value;
        Node next;

        Node(Object key, Object value) {
            this.key = key;
            this.value = value;
        }
    }

    public MyHashMap() {
        table = new Node[10];
        size = 0;
    }

    // Додає пару ключ + значення
    public void put(Object key, Object value) {

        int index = getIndex(key);

        Node current = table[index];

        // Перевіряємо, чи існує такий ключ
        while (current != null) {
            if (keysEqual(current.key, key)) {
                current.value = value;
                return;
            }

            current = current.next;
        }

        // Додаємо нову Node на початок списку
        Node newNode = new Node(key, value);
        newNode.next = table[index];
        table[index] = newNode;

        size++;
    }

    // Видаляє пару по ключу
    public void remove(Object key) {

        int index = getIndex(key);

        Node current = table[index];
        Node previous = null;

        while (current != null) {

            if (keysEqual(current.key, key)) {

                if (previous == null) {
                    table[index] = current.next;
                } else {
                    previous.next = current.next;
                }

                size--;
                return;
            }

            previous = current;
            current = current.next;
        }
    }

    // Очищає колекцію
    public void clear() {
        table = new Node[10];
        size = 0;
    }

    // Повертає розмір
    public int size() {
        return size;
    }

    // Повертає значення по ключу
    public Object get(Object key) {

        int index = getIndex(key);

        Node current = table[index];

        while (current != null) {

            if (keysEqual(current.key, key)) {
                return current.value;
            }

            current = current.next;
        }

        return null;
    }

    // Визначає індекс для ключа
    private int getIndex(Object key) {

        if (key == null) {
            return 0;
        }

        return Math.abs(key.hashCode()) % table.length;
    }

    // Порівнює ключі
    private boolean keysEqual(Object key1, Object key2) {

        if (key1 == key2) {
            return true;
        }

        if (key1 == null || key2 == null) {
            return false;
        }

        return key1.equals(key2);
    }
}

