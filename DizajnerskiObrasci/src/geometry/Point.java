package geometry;

import java.awt.Color;
import java.awt.Graphics;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter @ToString
public class Point extends Shape implements Moveable, Cloneable{
	private static final long serialVersionUID = 1L;
	private int x;
	private int y;
	private boolean selected;
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Point(int x, int y, boolean selected, Color color) {
		this(x, y, selected);
		setColor(color);
	}
	
	public double distance(int x2, int y2) {
		double dx = this.x - x2;
		double dy = this.y - y2;
		double d = Math.sqrt(dx * dx + dy * dy);
		return d; 
	}
	
	public int compareTo(Object o) {
		if (o instanceof Point) {
			Point start = new Point(0, 0);
			return (int) (this.distance(start.getX(), start.getY()) - ((Point) o).distance(start.getX(), start.getY()));
		}
		return 0;
	}
	
	
	public void draw(Graphics g) {
		g.setColor(getColor());
		g.drawLine(this.x-3, this.y, this.x+2, this.y);
		g.drawLine(this.x, this.y-3, this.x, this.y+3);
		
		if(isSelected()) {
			g.setColor(Color.BLUE);
			markPoint(g, this.x, this.y);
		}
	}
	
	public boolean contains(int x, int y) {
		return this.distance(x, y) <= 3 ;
	}
	
	public void moveBy(int byX, int byY) {
		this.x += byX;
		this.y += byY;
	}
	
	public void moveOn(int onX, int onY){
		this.x=onX;
		this.y=onY;
	}

	public String toStringPoint() {
		return "(" + x + ", " + y + ")";
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point other = (Point) obj;
		return selected == other.selected && x == other.x && y == other.y;
	}
	
	public Point clone (Point p) throws CloneNotSupportedException {

		p.setX(this.getX());
		p.setY(this.getY());
		p.setColor(this.getColor());

		p.setSelected(this.isSelected());
		
		return p;
	}
}
