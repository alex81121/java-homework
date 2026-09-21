package homwork9;

public class MyHashMap<K, V> {

    private Node<K, V>[] table;
    private int size;

    // Node - елемент однозв'язного списку
    private static class Node<K, V> {
        K key;
        V value;
        Node<K, V> next;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    @SuppressWarnings("unchecked")
    public MyHashMap() {
        table = (Node<K, V>[]) new Node[10];
        size = 0;
    }

    // Додає пару ключ + значення
    public void put(K key, V value) {

        int index = getIndex(key);

        Node<K, V> current = table[index];

        // Перевіряємо, чи існує такий ключ
        while (current != null) {

            if (keysEqual(current.key, key)) {
                current.value = value;
                return;
            }

            current = current.next;
        }

        // Додаємо нову Node на початок списку
        Node<K, V> newNode = new Node<>(key, value);
        newNode.next = table[index];
        table[index] = newNode;

        size++;
    }

    // Видаляє пару по ключу
    public void remove(K key) {

        int index = getIndex(key);

        Node<K, V> current = table[index];
        Node<K, V> previous = null;

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
    @SuppressWarnings("unchecked")
    public void clear() {
        table = (Node<K, V>[]) new Node[10];
        size = 0;
    }

    // Повертає розмір
    public int size() {
        return size;
    }

    // Повертає значення по ключу
    public V get(K key) {

        int index = getIndex(key);

        Node<K, V> current = table[index];

        while (current != null) {

            if (keysEqual(current.key, key)) {
                return current.value;
            }

            current = current.next;
        }

        return null;
    }

    // Визначає індекс для ключа
    private int getIndex(K key) {

        if (key == null) {
            return 0;
        }

        return Math.floorMod(key.hashCode(), table.length);
    }

    // Порівнює ключі
    private boolean keysEqual(K key1, K key2) {

        if (key1 == key2) {
            return true;
        }

        if (key1 == null || key2 == null) {
            return false;
        }

        return key1.equals(key2);
    }
}

