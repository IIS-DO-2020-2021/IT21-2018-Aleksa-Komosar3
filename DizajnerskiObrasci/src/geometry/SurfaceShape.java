package geometry;

import java.awt.Color;
import java.awt.Graphics;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter @ToString
public abstract class SurfaceShape extends Shape{
	private static final long serialVersionUID = 1L;
	private Color innerColor=Color.BLACK;
	
	public abstract void fill(Graphics g);
	public abstract double area();
	
	public String getInnerColorRGB() {
		int red = this.innerColor.getRed();
		int green = this.innerColor.getGreen();
		int blue = this.innerColor.getBlue();
		return "RGB(" + red + ", " + green + ", " + blue + ")";
	}
}
