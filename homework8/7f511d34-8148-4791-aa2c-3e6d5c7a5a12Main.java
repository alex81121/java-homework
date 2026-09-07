package graphiceditor;

import java.util.Arrays;
import java.util.List;

// Базовий абстрактний клас для всіх фігур
abstract class Shape {
    public abstract String getName();
}

// Коло
class Circle extends Shape {
    @Override
    public String getName() {
        return "Circle";
    }
}

// Чотирикутник
class Quad extends Shape {
    @Override
    public String getName() {
        return "Quad";
    }
}

// Трикутник
class Triangle extends Shape {
    @Override
    public String getName() {
        return "Triangle";
    }
}

// Прямокутник
class Rectangle extends Shape {
    @Override
    public String getName() {
        return "Rectangle";
    }
}

// П'ятикутник
class Pentagon extends Shape {
    @Override
    public String getName() {
        return "Pentagon";
    }
}

// Шестикутник
class Hexagon extends Shape {
    @Override
    public String getName() {
        return "Hexagon";
    }
}

// Клас графічного редактора.
// Працює тільки з базовим класом Shape.
class GraphicEditor {
    public void showShapeName(Shape shape) {
        System.out.println("Фігура: " + shape.getName());
    }

    public void showAllShapes(List<Shape> shapes) {
        for (Shape shape : shapes) {
            showShapeName(shape);
        }
    }
}

// Головний клас програми
public class Main {
    public static void main(String[] args) {
        GraphicEditor editor = new GraphicEditor();

        List<Shape> shapes = Arrays.asList(
                new Circle(),
                new Quad(),
                new Triangle(),
                new Rectangle(),
                new Pentagon(),
                new Hexagon()
        );

        editor.showAllShapes(shapes);
    }
}
