package homwork9;
public class main2 {

    public static void main(String[] args) {

        MyLinkedList list = new MyLinkedList();

        list.add("Hello");
        list.add("World");
        list.add(123);
        list.add("Test");

        System.out.println(list.get(0));
        System.out.println(list.get(1));
        System.out.println(list.get(2));
        System.out.println(list.get(3));

        System.out.println("Size: " + list.size());

        list.remove(1);

        System.out.println("After remove:");
        System.out.println(list.get(0));
        System.out.println(list.get(1));
        System.out.println(list.get(2));

        System.out.println("Size: " + list.size());

        list.clear();

        System.out.println("Size after clear: " + list.size());
    }
}

