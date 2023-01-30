package se.liu.chrer268.shapes;

import java.awt.*;

public class Text extends AbstractShape
{
    private int size;
    private String text;

    public Text(int x, int y, Color color, String text) {
	super(x, y, color);
	this.size = 50;
	this.text = text;
    }

    @Override public String toString() {
	return "Text{" + "x=" + getX() + ", y=" + getY() + ", size=" +
	       		size + ", color=" + getColor() + ", text='" +
	       						text + '\'' + '}';
    }

    @Override public void draw(final Graphics g) {
	//System.out.println("Ritar: " + this);
	g.setColor(color);
	g.setFont(new Font("serif", Font.PLAIN, size));
	g.drawString(text, x, y);
    }
}
