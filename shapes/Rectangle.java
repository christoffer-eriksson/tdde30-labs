package se.liu.chrer268.shapes;

import java.awt.*;

public class Rectangle extends AbstractShape
{
    private int width;
    private int height;


    public Rectangle(int x, int y, int width, int height, Color color) {
	super (x, y, color);
	this.width = width;
	this.height = height;
    }

    @Override public String toString() {
	return "Rectangle{" + "x=" + getX() + ", y=" + getY()
	       		+ ", width=" + width + ", height=" + height
	       				+ ", color=" + getColor() + '}';
    }

    public int getWidth() {
	return width;
    }

    public int getHeight() {
	return height;
    }

    @Override public void draw(final Graphics g) {
	//System.out.println("Ritar: " + this);
	g.setColor(color);
	g.drawRect(x, y, width, height);
    }

}
