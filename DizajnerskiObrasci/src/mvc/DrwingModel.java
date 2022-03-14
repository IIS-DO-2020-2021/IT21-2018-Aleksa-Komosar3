package mvc;

import java.util.ArrayList;
import geometry.Shape;

public class DrwingModel {
	private ArrayList<Shape> shapes = new ArrayList<Shape>();
	private ArrayList<Shape> selected = new ArrayList<Shape>();

	public void add(Shape s) {
		shapes.add(s);
	}
	public void remove(Shape s) {
		shapes.remove(s);
	}

	public Shape getSelectedShape(int i) {
		return selected.get(i);
	}

	public ArrayList<Shape> getShapes() {
		return shapes;
	}

	public ArrayList<Shape> getSelected() {
		return selected;
	}

	public void setSelected(ArrayList<Shape> selected) {
		this.selected = selected;
	}
}
