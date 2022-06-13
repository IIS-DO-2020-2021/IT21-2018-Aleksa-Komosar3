package geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Objects;

import hexagon.Hexagon;

public class HexagonAdapter extends Shape {

	private static final long serialVersionUID = 1L;
	private  Hexagon hexagon = new Hexagon(0,0,0);
	
	public Hexagon getHexagon() {
		return hexagon;
	}

	public void setHexagon(Hexagon hexagon) {
		this.hexagon = hexagon;
	}
	
	public Point getHexagonCenter() {
		return new Point(this.hexagon.getX(), this.hexagon.getY());
	}

	public void setHexagonCenter(Point center) {
		this.hexagon.setX(center.getX());
		this.hexagon.setY(center.getY());
	}

	public void setHexagonRadius(int radius) {
		this.hexagon.setR(radius);
	}

	public int getHexagonRadius() {
		return this.hexagon.getR();
	}
	
	public void setHexagonBorderColor(Color edgeColor) {
		this.hexagon.setBorderColor(edgeColor);
	}
	
	public Color getHexagonBorderColor() {
		return this.hexagon.getBorderColor();
	}

	public void setHexagonInnerColor(Color innerColor) {
		this.hexagon.setAreaColor(innerColor);
	}
	
	public Color getHexagonInnerColor() {
		return this.hexagon.getAreaColor();
	}
	
	public String getInnerColorRGB() {
		int red = hexagon.getAreaColor().getRed();
		int green = hexagon.getAreaColor().getGreen();
		int blue = hexagon.getAreaColor().getBlue();
		return "(" + red + ", " + green + ", " + blue + ")";
	}
	
	public HexagonAdapter()
	{
		
	}
	
	public HexagonAdapter(Point center)
	{
		hexagon.setX(center.getX());
		hexagon.setY(center.getY());	
	}
	
	public HexagonAdapter(Point center, int radius)
	{
		this(center);
		hexagon.setR(radius);
	}
	
	public HexagonAdapter(int x, int y, int radius) {
		
		hexagon = new Hexagon(x, y, radius);
		
	}
	
	public HexagonAdapter(Point center, int radius, boolean selected) {
		this(center, radius);
		hexagon.setSelected(selected);
	}
	
	public HexagonAdapter(Point center, int radius, boolean selected, Color color) {
		this(center, radius, selected);
		hexagon.setBorderColor(color);
	}
	
	public HexagonAdapter(Point center, int radius, boolean selected, Color color, Color innerColor) {
		this(center, radius, selected, color);
		hexagon.setAreaColor(innerColor);
	}
	
	@Override
	public void moveOn(int onX, int onY) {
		hexagon.setX(onX);
		hexagon.setY(onY);
	}

	@Override
	public void draw(Graphics g) {
		hexagon.paint(g);
		
		if (isSelected()) {
			g.setColor(Color.BLUE);
			markPoint(g, this.hexagon.getX(), this.hexagon.getY());
			
			markPoint(g, this.hexagon.getX()-this.hexagon.getR(),
					this.hexagon.getY());
			markPoint(g, this.hexagon.getX()+this.hexagon.getR(),
					this.hexagon.getY());
			
			markPoint(g, this.hexagon.getX()-this.hexagon.getR()/2,
					this.hexagon.getY()-(int)(this.hexagon.getR()*Math.sqrt(3)/2));
			markPoint(g, this.hexagon.getX()+this.hexagon.getR()/2, 
					this.hexagon.getY()-(int)(this.hexagon.getR()*Math.sqrt(3)/2));
			
			markPoint(g, this.hexagon.getX()-this.hexagon.getR()/2, 
					this.hexagon.getY()+(int)(this.hexagon.getR()*Math.sqrt(3)/2));
			markPoint(g, this.hexagon.getX()+this.hexagon.getR()/2, 
					this.hexagon.getY()+(int)(this.hexagon.getR()*Math.sqrt(3)/2));
		}
	}
	
	@Override
	public int compareTo(Object o) {
		if (o instanceof HexagonAdapter) {
			return this.hexagon.getR() - ((HexagonAdapter)o).hexagon.getR();
		}
		return 0;
	}
	
	@Override
	public void moveBy(int byX, int byY) {
		hexagon.setX(hexagon.getX() + byX);
		hexagon.setY(hexagon.getY() + byY);
	}

	@Override
	public boolean contains(int x, int y) {
		return hexagon.doesContain(x, y);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HexagonAdapter other = (HexagonAdapter) obj;
		return Objects.equals(hexagon.getX(), 
				other.getHexagon().getX()) && 
				hexagon.getY() == other.getHexagon().getY() 
				&& hexagon.getR() == other.getHexagon().getR() &&
				hexagon.isSelected() == other.getHexagon().isSelected();
	}
	
	public HexagonAdapter clone(HexagonAdapter hexagon) throws CloneNotSupportedException {
		hexagon.setHexagonCenter(this.getHexagonCenter());
		hexagon.setHexagonRadius(this.getHexagonRadius());
		hexagon.setHexagonBorderColor(this.getHexagonBorderColor());
		hexagon.setHexagonInnerColor(this.getHexagonInnerColor());
		hexagon.setSelected(this.hexagon.isSelected());

		return hexagon;
	}
	
	@Override
	public String toString() {
		Point center = new Point(hexagon.getX(), hexagon.getY());
		return "Hexagon [center=" + center.toStringPoint() + ", radius=" + hexagon.getR() + ", Color= " + this.getColorRGB() + ", Color= " + this.getInnerColorRGB() +  "]";
	}
}
