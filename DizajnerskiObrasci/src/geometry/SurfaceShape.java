package geometry;

import java.awt.Color;
import java.awt.Graphics;

public abstract class SurfaceShape extends Shape{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Color innerColor=Color.BLACK;
	
	public abstract void fill(Graphics g);
	public abstract double area();

	public Color getInnerColor() {
		return innerColor;
	}

	public void setInnerColor(Color innerColor) {
		this.innerColor = innerColor;
	}
	
	public String getInnerColorRGB() {
		int red = this.innerColor.getRed();
		int green = this.innerColor.getGreen();
		int blue = this.innerColor.getBlue();
		return "RGB(" + red + ", " + green + ", " + blue + ")";
	}
}
