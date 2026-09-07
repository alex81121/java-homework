package homwork9;
public class main4 {

    public static void main(String[] args) {

        MyStack stack = new MyStack();

        stack.push("First");
        stack.push("Second");
        stack.push("Third");

        System.out.println("Size: " + stack.size());

        System.out.println("Peek: " + stack.peek());
        System.out.println("Size after peek: " + stack.size());

        System.out.println("Pop: " + stack.pop());
        System.out.println("Pop: " + stack.pop());

        System.out.println("Peek: " + stack.peek());
        System.out.println("Size: " + stack.size());

        stack.remove(0);

        System.out.println("Size after remove: " + stack.size());

        stack.clear();

        System.out.println("Size after clear: " + stack.size());
    }
}

