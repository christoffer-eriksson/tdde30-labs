package se.liu.chrer268.tetris;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

public class StartIcon extends JComponent
{
    private final ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("images/new-tetris-logo-35th-anniversary.jpg"));

    private final static double IMAGE_SCALE = 0.7;
    private int iconWidth = (int) (icon.getIconWidth() * IMAGE_SCALE);
    private int iconHeight = (int) (icon.getIconHeight() * IMAGE_SCALE);

    public int getIconWidth() {
	return iconWidth;
    }

    public int getIconHeight() {
	return iconHeight;
    }

    @Override public void paintComponent(final Graphics g) {
	final Graphics2D g2d = (Graphics2D) g;
	g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

	final AffineTransform old = g2d.getTransform();
	final AffineTransform at = AffineTransform.getScaleInstance(1, 1);

	at.translate(0, 0);
	at.scale(IMAGE_SCALE, IMAGE_SCALE);
	g2d.transform(at);
	icon.paintIcon(this, g, 0, 0);
	g2d.transform(old);
    }
}
