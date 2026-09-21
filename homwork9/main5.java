package homwork9;

public class main5 {

    public static void main(String[] args) {

        MyHashMap<String, Object> map = new MyHashMap<>();

        map.put("name", "Oleksandr");
        map.put("age", 19);
        map.put("city", "Plzen");

        System.out.println(map.get("name"));
        System.out.println(map.get("age"));
        System.out.println(map.get("city"));

        System.out.println("Size: " + map.size());

        // Змінюємо значення існуючого ключа
        map.put("age", 20);

        System.out.println("New age: " + map.get("age"));
        System.out.println("Size: " + map.size());

        // Видаляємо
        map.remove("city");

        System.out.println("City: " + map.get("city"));
        System.out.println("Size after remove: " + map.size());

        // Очищаємо
        map.clear();

        System.out.println("Size after clear: " + map.size());
    }
}



