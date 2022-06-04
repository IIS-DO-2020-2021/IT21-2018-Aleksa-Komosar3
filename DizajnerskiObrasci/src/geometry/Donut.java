package geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

public class Donut extends Circle implements Cloneable{
	
	private static final long serialVersionUID = 1L;
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
		Ellipse2D outsideCircle = new Ellipse2D.Double(this.getCenter().getX() - this.getRadius(),
				this.getCenter().getY() - this.getRadius(), this.getRadius() * 2, this.getRadius() * 2);
		Ellipse2D insideCircle = new Ellipse2D.Double(this.getCenter().getX() - this.getInnerRadius(),
				this.getCenter().getY() - this.getInnerRadius(), this.getInnerRadius() * 2, 
				this.getInnerRadius() * 2);
		Area bigArea = new Area(outsideCircle);
		bigArea.subtract(new Area(insideCircle));

		Graphics2D graph = (Graphics2D) g;
		graph.setColor(getInnerColor());
		graph.fill(bigArea);
		graph.setColor(getColor());
		graph.draw(bigArea);

		if (isSelected()) {
			super.putCirclePoint(g, this.getRadius());
			super.putCirclePoint(g, this.innerRadius);
		}
	}
	
	
	public void fill(Graphics g) {
		g.setColor(getInnerColor());
		super.fill(g);
		g.setColor(Color.CYAN);
		g.fillOval(getCenter().getX() - getInnerRadius(), getCenter().getY() - getInnerRadius(), getInnerRadius() * 2, getInnerRadius() * 2);
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
	
	public int getInnerRadius() {
		return innerRadius;
	}

	public void setInnerRadius(int innerRadius) {
		this.innerRadius=innerRadius;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Donut other = (Donut) obj;
		return innerRadius == other.innerRadius;
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
}
