package homwork9;
public class main3 {

    public static void main(String[] args) {

        MyQueue queue = new MyQueue();

        queue.add("First");
        queue.add("Second");
        queue.add("Third");

        System.out.println("Size: " + queue.size());

        System.out.println("Peek: " + queue.peek());
        System.out.println("Size after peek: " + queue.size());

        System.out.println("Poll: " + queue.poll());
        System.out.println("Poll: " + queue.poll());

        System.out.println("Peek: " + queue.peek());
        System.out.println("Size: " + queue.size());

        queue.clear();

        System.out.println("Size after clear: " + queue.size());
    }
}

