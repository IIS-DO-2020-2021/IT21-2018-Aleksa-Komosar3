package geometry;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.Graphics;

public class Donut extends Circle{
	private int innerRadius;
	
	public Donut() {
		
	}
	
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
		Graphics2D g2d = (Graphics2D) g; 

		//Ellipse2D outer = new Ellipse2D.Double(this.getCenter().getX() - this.getRadius(), this.getCenter().getY() - this.getRadius(), this.getRadius()*2, this.getRadius()*2); //jer je poluprecnik pa *2
		//Ellipse2D inner = new Ellipse2D.Double(this.getCenter().getX() - this.getInnerRadius() , this.getCenter().getY() - this.getInnerRadius(), this.innerRadius * 2, this.innerRadius * 2);

		/*Area area = new Area(outer);
		Area innerArea = new Area(inner);
		area.subtract(innerArea);

		g2d.setColor(getInnerColor());
		g2d.fill(area);
		g2d.setColor(getColor());
		g2d.draw(area);*/
		if (isSelected()) {
			g.setColor(Color.BLUE);
			super.putCirclePoint(g, this.getRadius());
			super.putCirclePoint(g, innerRadius);
		}
	}
	
	
	public void fill(Graphics g) {
		Graphics2D g2d = (Graphics2D) g; 

		Ellipse2D outer = new Ellipse2D.Double(this.getCenter().getX() - this.getRadius(), this.getCenter().getY() - this.getRadius(), this.getRadius()*2, this.getRadius()*2);
		Ellipse2D inner = new Ellipse2D.Double(this.getCenter().getX() - this.getInnerRadius() , this.getCenter().getY() - this.getInnerRadius(), this.innerRadius * 2, this.innerRadius * 2);

		Area area = new Area(outer);
		Area innerArea = new Area(inner);
		area.subtract(innerArea);

		g2d.setColor(getInnerColor());
		g2d.fill(area);
		g2d.setColor(getColor());
		g2d.draw(area);
	}
	
	
	/*public void fill(Graphics g) {
		g.setColor(getInnerColor());
		super.fill(g);
		g.setColor(Color.CYAN);
		g.fillOval(getCenter().getX() - getInnerRadius(), getCenter().getY() - getInnerRadius(), getInnerRadius() * 2, getInnerRadius() * 2);
	}*/
	
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
	
	public boolean equals(Object obj) {
		if (obj instanceof Donut) {
			Donut d = (Donut) obj;
			if (this.getCenter().equals(d.getCenter()) &&
					this.getRadius() == d.getRadius() &&
					this.innerRadius == d.getInnerRadius()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public void moveBy(int byX, int byY) {
		super.moveBy(byX, byY);
	}
	
	public void moveOn(int onX, int onY){
		super.moveOn(onX, onY);
	}
	
	public int getInnerRadius() {
		return innerRadius;
	}

	public void setInnerRadius(int innerRadius) {
		this.innerRadius=innerRadius;
	}
}
