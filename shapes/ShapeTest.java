package se.liu.chrer268.shapes;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ShapeTest
{
    public static void main(String[] args) {
	final List<Shape> shapes = new ArrayList<>();
	Shape c1 = new Circle(1, 2, 3, Color.BLUE);
	Shape c2 = new Circle(3, 4, 3, Color.GRAY);
	Shape c3 = new Circle(5, 6, 3, Color.GREEN);
	Shape rectangle = new Rectangle(5, 5, 2, 3, Color.PINK);
	Shape text = new Text(4, 6, Color.PINK, "Hej");
	shapes.add(c1);
	shapes.add(c2);
	shapes.add(c3);
	shapes.add(rectangle);
	shapes.add(text);
	for (Shape shape : shapes) {
	    //shape.draw();
	}
    }
}
