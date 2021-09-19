package mvc;

import java.util.ArrayList;
import geometry.Shape;

public class DrwingModel {
	private ArrayList<Shape> shapes = new ArrayList<Shape>();
	
	public DrwingModel() {
		
	}

	
	public void add(Shape s) {
		shapes.add(s);
	}
	
	
	public void remove(Shape s) {
		shapes.remove(s);
	}
	
	public ArrayList<Shape> getShapes() {
		return shapes;
	}
	
	public Shape getShape(int i) {
		return shapes.get(i);
	}
	
	public void setShapes(ArrayList<Shape> shapes) {
		this.shapes = shapes;
	}
}
