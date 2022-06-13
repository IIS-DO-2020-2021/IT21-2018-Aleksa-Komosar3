package geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class Donut extends Circle implements Cloneable{
	
	private static final long serialVersionUID = 1L;
	private int innerRadius;
	
	public Donut(Point center, int radius, int innerRadius) {
		super(center, radius);
		this.innerRadius = innerRadius;
	}
	
	public Donut(Point center, int radius, int innerRadius, boolean selected) {
		this(center, radius, innerRadius);
		setSelected(selected);
	}
	
	public Donut(Point center, int radius, int innerRadius, boolean selected, Color color) { 
		this(center, radius, innerRadius, selected);
		setColor(color);
	}
	
	public Donut(Point center, int radius, int innerRadius, boolean selected, Color color, Color innerColor) { 
		this(center, radius, innerRadius, selected, color);
		setInnerColor(innerColor);
	}
	
	public void draw(Graphics g) {		
		super.draw(g);
		g.setColor(getColor());
		g.drawOval(getCenter().getX() - this.innerRadius, getCenter().getY() - this.innerRadius, getInnerRadius() * 2, getInnerRadius() * 2);
		
		if (isSelected()) {
			g.setColor(Color.BLUE);
			super.putCirclePoint(g, this.getRadius());
			super.putCirclePoint(g, innerRadius);
		}
	}
	
	
	public void fill(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		g2d.setColor(getInnerColor());

		Ellipse2D whole = new Ellipse2D.Double(this.getCenter().getX()-this.getRadius(),
				this.getCenter().getY()-this.getRadius(), 
				2* this.getRadius(), 2* this.getRadius());
		Ellipse2D inner = new Ellipse2D.Double(this.getCenter().getX()-this.innerRadius, 
				this.getCenter().getY()-this.innerRadius,
				2* this.innerRadius, 2* this.innerRadius);
		
		Area area = new Area(whole);
		Area innerA = new Area(inner);
		area.subtract(innerA);
		
		g2d.fill(area);
	}
	
	public boolean contains(int x, int y) {
		double dFromCenter = this.getCenter().distance(x, y);
		return super.contains(x, y) && dFromCenter > innerRadius;
	}
	
	public boolean contains(Point p) {
		double dFromCenter = this.getCenter().distance(p.getX(), p.getY());
		return super.contains(p.getX(), p.getY()) && dFromCenter > innerRadius;
	}
	public double area() {
		return super.area() - innerRadius * innerRadius * Math.PI;
	}

	public void moveBy(int byX, int byY) {
		super.moveBy(byX, byY);
	}
	
	public void moveOn(int onX, int onY){
		super.moveOn(onX, onY);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Donut) {
			Donut d = (Donut) obj;
			if (this.getCenter().equals(d.getCenter()) && this.getRadius() == d.getRadius()
					&& this.innerRadius == d.getInnerRadius()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	public Donut clone(Donut donut) throws CloneNotSupportedException {
		
		donut.setCenter(this.getCenter());
		donut.setRadius(this.getRadius());
		donut.setInnerRadius(this.getInnerRadius());

		donut.setColor(this.getColor());
		donut.setInnerColor(this.getInnerColor());
		donut.setSelected(this.isSelected());

		return donut;
	}
	
	@Override
	public String toString() {
		return "Donut [center=" + this.getCenter().toStringPoint() 
				+ ", outerRadius=" + this.getRadius() 
				+ ", innerRadius=" + innerRadius + ", Color= " 
				+ this.getColorRGB() + ", innerColor= " 
				+ this.getInnerColorRGB() + "]";
	}
}
