package geometry;

import java.awt.Color;
import java.awt.Graphics;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class Circle extends SurfaceShape implements Cloneable{
	private static final long serialVersionUID = 1L;
	private Point center=new Point();
	private int radius;
	private boolean selected;

	
	public Circle(Point center, int radius) {
		this.center = center;
		this.radius = radius;
	}
	
	
	public Circle(Point center, int radius, boolean selected, Color color) {
		this(center, radius, selected);
		setColor(color);
	}
	
	public Circle(Point center, int radius, boolean selected, Color color, Color innerColor) {
		this(center, radius, selected, color);
		setInnerColor(innerColor);
	}
	
	public double area() {
		return radius * radius * Math.PI;
	}
	
	public int compareTo(Object o) {
		if (o instanceof Circle) {
			return this.radius - ((Circle)o).radius;
		}
		return 0;
	}
	
	public void draw(Graphics g) {
		g.setColor(getColor());
		g.drawOval(this.center.getX() - this.radius, this.center.getY() - this.radius, 
				this.radius * 2, this.radius * 2);
		
		fill(g);
		
		putCirclePoint(g, this.radius);
	}
	
	public void putCirclePoint(Graphics g, int r) {
		if (isSelected()) {
			g.setColor(Color.BLUE);
			markPoint(g, this.center.getX(), this.center.getY());
			markPoint(g, this.center.getX() - r, this.center.getY());
			markPoint(g, this.center.getX() + r, this.center.getY());
			markPoint(g, this.center.getX(), this.center.getY() - r);
			markPoint(g, this.center.getX(), this.center.getY() + r);
		}
	}
	@Override
	public void fill(Graphics g) {
		g.setColor(getInnerColor());
		g.fillOval(this.center.getX() - radius + 1, this.center.getY() - radius + 1,
				(radius * 2) - 2, (radius * 2) - 2);	
	}
	
	public boolean contains(Point p) {
		return this.getCenter().distance(p.getX(), p.getY()) <= radius;
	}
	
	@Override
	public boolean contains(int x, int y) {
		return this.getCenter().distance(x, y) <= radius;
	}
	
	public void moveBy(int byX, int byY) {
		center.moveBy(byX, byY);
	}
	
	public void moveOn(int onX, int onY){
		center.moveOn(onX, onY);
	}
	public void setCenter(Point center) {
		this.center = center;
	}

	public int getRadius() {
		return radius;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Circle) {
			Circle c = (Circle) obj;
			if (this.center.equals(c.getCenter()) && this.radius == c.getRadius()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	public Circle clone(Circle circle) throws CloneNotSupportedException {
		
		circle.setCenter(this.getCenter());
		circle.setRadius(this.getRadius());

		circle.setColor(this.getColor());
		circle.setInnerColor(this.getInnerColor());
		circle.setSelected(this.isSelected());

		return circle;
	}
	
	@Override
	public String toString() {
		return "Circle [center=" + center.toStringPoint() + ", radius=" + radius +  ", Color= " + this.getColorRGB() + ", Color= " + this.getInnerColorRGB() + "]";
	}

}
