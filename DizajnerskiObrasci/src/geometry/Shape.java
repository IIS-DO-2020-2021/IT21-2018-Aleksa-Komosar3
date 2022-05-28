package geometry;

import java.awt.Graphics;
import java.io.Serializable;

import java.awt.Color;

public abstract class Shape implements Moveable, Comparable<Object>, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean selected;
	private Color color=Color.BLACK;
	
	public abstract void draw(Graphics g);
	public abstract boolean contains(int x, int y);
	
	public boolean isSelected() {
			return selected;
	}

	public void setSelected(boolean selected) {
			this.selected = selected;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
	public void markPoint(Graphics g, int x, int y) {
		g.drawRect(x-3, y-3, 6, 6);
    }
	
	public String getColorRGB() {
		int red = this.color.getRed();
		int green = this.color.getGreen();
		int blue = this.color.getBlue();
		return "RGB(" + red + ", " + green + ", " + blue + ")";
	}
}
