package geometry;

import java.awt.Graphics;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.awt.Color;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter @ToString
public abstract class Shape implements Moveable, Comparable<Object>, Serializable{
	private static final long serialVersionUID = 1L;
	private boolean selected;
	private Color color=Color.BLACK;
	
	public abstract void draw(Graphics g);
	public abstract boolean contains(int x, int y);
	
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
