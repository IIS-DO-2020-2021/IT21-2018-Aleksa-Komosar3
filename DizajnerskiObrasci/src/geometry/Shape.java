package geometry;

import java.awt.Graphics;
import java.awt.Color;

public abstract class Shape implements Moveable, Comparable<Object>{
	private boolean selected;
	private Color color;
	
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
}
