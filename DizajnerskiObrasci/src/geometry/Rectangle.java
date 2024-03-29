package geometry;

import java.awt.Color;
import java.awt.Graphics;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class Rectangle extends SurfaceShape implements Cloneable{
	private static final long serialVersionUID = 1L;
	private Point upperLeft=new Point();
	private int height;
	private int width;
	private boolean selected;

	public Rectangle(Point upperLeftPoint, int height, int width) {
		this.upperLeft = upperLeftPoint;
		this.height = height;
		this.width = width;
	}
	
	public Rectangle(Point upperLeftPoint, int height, int width, boolean selected, Color color) {
		this(upperLeftPoint, height, width, selected);
		setColor(color);
	}
	
	public Rectangle(Point upperLeftPoint, int height, int width, boolean selected, Color color, Color innerColor) {
		this(upperLeftPoint, height, width, selected, color);
		setInnerColor(innerColor);
	}
	
	public double area() {
		return width * height;
	}
	
	
	public int compareTo(Object o) {
		if (o instanceof Rectangle) {
			return (int) (this.area() - ((Rectangle) o).area());
		}
		return 0;
	}

	public void draw(Graphics g) {
		g.setColor(getColor());
		g.drawRect(this.upperLeft.getX(), this.upperLeft.getY(), this.width, this.height);
		
		this.fill(g);
		
		if (isSelected()) {
			g.setColor(Color.BLUE);
			markPoint(g, this.upperLeft.getX(), this.upperLeft.getY());
			markPoint(g, this.upperLeft.getX() + this.width, this.upperLeft.getY());
			markPoint(g, this.upperLeft.getX(), this.upperLeft.getY() + this.height);
			markPoint(g, this.upperLeft.getX() + this.width, this.upperLeft.getY() + this.height);
		}
	}
	
	@Override
	public void fill(Graphics g) {
		g.setColor(getInnerColor());
		g.fillRect(this.upperLeft.getX()+1, this.getUpperLeft().getY()+1, width-1, height-1);
	}
	
	public boolean contains(Point p) {
		if (upperLeft.getX() <= p.getX() &&
				this.getUpperLeft().getY() <= p.getY() &&
				p.getX() <= upperLeft.getX() + width &&
				p.getY() <= upperLeft.getY() + height) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public boolean contains(int x, int y) {
		if (upperLeft.getX() <= x &&
				this.getUpperLeft().getY() <= y &&
				x <= upperLeft.getX() + width &&
				y <= upperLeft.getY() + height) {
			return true;
		} else {
			return false;
		}
	}
	
	public void moveBy(int byX, int byY) {
		upperLeft.moveBy(byX, byY);
	}
	
	public void moveOn(int onX, int onY){
		upperLeft.moveOn(onX, onY);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Rectangle) {
			Rectangle r = (Rectangle) obj;
			if (this.upperLeft.equals(r.getUpperLeft()) && this.height == r.getHeight()
					&& this.width == r.getWidth()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	public Rectangle clone(Rectangle rectangle) throws CloneNotSupportedException {
		rectangle.setUpperLeft(this.getUpperLeft());
		rectangle.setHeight(this.getHeight());
		rectangle.setWidth(this.getWidth());

		rectangle.setColor(this.getColor());
		rectangle.setInnerColor(this.getInnerColor());
		rectangle.setSelected(this.isSelected());

		return rectangle;
	}
	
	@Override
	public String toString() {
		return "Rectangle [upperLeft=" + upperLeft.toStringPoint() 
		+ ", height=" + height + ", width=" + width + ", Color= " 
				+ this.getColorRGB() + ", Color= " 
		+ this.getInnerColorRGB() + "]";
	}
}
