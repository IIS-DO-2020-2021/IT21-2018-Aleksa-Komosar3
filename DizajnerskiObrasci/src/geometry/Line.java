package geometry;

import java.awt.Color;
import java.awt.Graphics;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class Line extends Shape implements Cloneable{
	private static final long serialVersionUID = 1L;
	private Point startPoint=new Point();
	private Point endPoint=new Point();
	private boolean selected;
	
	public Line(Point startPoint, Point endPoint) {
		this.startPoint = startPoint;
		this.endPoint = endPoint;
	}
	
	public Line(Point startPoint, Point endPoint, boolean selected, Color color) {
		this(startPoint, endPoint, selected);
		setColor(color);
	}
	
	public double length() {
		return startPoint.distance(endPoint.getX(), endPoint.getY());
	}
	
	public int compareTo(Object o) {
		if (o instanceof Line) {
			return (int) (this.length() - ((Line) o).length() );
		} 
		return 0;
	}
	
	public void draw(Graphics g) {
		g.setColor(getColor());
		g.drawLine(this.startPoint.getX(), this.startPoint.getY(), this.endPoint.getX(), this.endPoint.getY());
		
		if(isSelected()) {
			g.setColor(Color.BLUE);
			g.drawRect(this.startPoint.getX() - 3 , this.startPoint.getY() - 3, 6, 6);
			g.drawRect(this.endPoint.getX() - 3, this.endPoint.getY() - 3, 6, 6);
		}	
	}
	
	public boolean contains(int x, int y) {
		if ((startPoint.distance(x, y) + endPoint.distance(x, y)) - length() <= 0.05) {
			return true;
		} else {
			return false;
		}
	}
	
	public void moveBy(int byX, int byY) {
		startPoint.moveBy(byX, byY);
		endPoint.moveBy(byX, byY);
	}
	
	public void moveOn(int onX, int onY){
		startPoint.moveOn(onX, onY);
		endPoint.moveOn(onX, onY);		
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Line) {
			Line l = (Line) obj;
			if (this.startPoint.equals(l.getStartPoint()) && this.endPoint.equals(l.getEndPoint())) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	public Line clone(Line line) throws CloneNotSupportedException {
		line.getStartPoint().setX(this.getStartPoint().getX());
		line.getStartPoint().setY(this.getStartPoint().getY());
		line.getEndPoint().setX(this.getEndPoint().getX());
		line.getEndPoint().setY(this.getEndPoint().getY());
		
		line.setColor(this.getColor());
		line.setSelected(this.isSelected());
		
		return line;
	}
	
	@Override
	public String toString() {
		return "Line [startPoint=" + startPoint.toStringPoint() 
		+ ", endPoint=" + endPoint.toStringPoint() 
		+ ", Color= " + this.getColorRGB() + "]";
	}
	
}
