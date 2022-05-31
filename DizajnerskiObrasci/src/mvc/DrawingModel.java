package mvc;

import java.util.ArrayList;

import geometry.Shape;

public class DrawingModel {

	private ArrayList<Shape> shapes = new ArrayList<Shape>();
	private ArrayList<Shape> selectedShapes = new ArrayList<Shape>();
	
	public ArrayList<Shape> getShapes() {
		return shapes;
	}
	public void setShapes(ArrayList<Shape> shapes) {
		this.shapes = shapes;
	}
	public ArrayList<Shape> getSelectedShapes() {
		return selectedShapes;
	}
	public void setSelectedShapes(ArrayList<Shape> selectedShapes) {
		this.selectedShapes = selectedShapes;
	}
	public void addShapeToListOfSelected (Shape shape){
		selectedShapes.add(shape);
	}
	public void deleteShapeFromSelectedList(Shape shape){
		selectedShapes.remove(shape);
	}
	
	public void addShapeToList (Shape shape) {
		shapes.add(shape);
	}
	
	public void deleteShapeFromList(Shape shape){
		shapes.remove(shape);
	}
	
}