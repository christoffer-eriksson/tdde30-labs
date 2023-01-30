package se.liu.chrer268.shapes;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CircleTest
{
    public static void main(String[] args) {
	final List<Circle> circles = new ArrayList<>();
	Circle c1 = new Circle(1, 2, 3, Color.BLUE);
	Circle c2 = new Circle(3, 4, 3, Color.GRAY);
	Circle c3 = new Circle(5, 6, 3, Color.GREEN);
	circles.add(c1);
	circles.add(c2);
	circles.add(c3);
	for (Circle circle : circles) {
	    int x = circle.getX();
	    int y = circle.getY();
	    System.out.println("X: " + x + ", " + "Y: " + y);
	}
    }
}
