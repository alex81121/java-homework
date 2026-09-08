package homework8;

public class Main {

    public static void main(String[] args) {

        ShapePrinter printer = new ShapePrinter();

        Shape circle = new Circle();
        Shape quad = new Quad();
        Shape rectangle = new Rectangle();
        Shape triangle = new Triangle();
        Shape line = new Line();

        printer.printName(circle);
        printer.printName(quad);
        printer.printName(rectangle);
        printer.printName(triangle);
        printer.printName(line);
    }
}

