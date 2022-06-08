package mvc;

import java.util.ArrayList;
import java.util.Stack;

import command.Command;
import geometry.Shape;

public class DrawingModel {

	private ArrayList<Shape> shapes = new ArrayList<Shape>();
	private ArrayList<Shape> selectedShapes = new ArrayList<Shape>();
	private Stack<Command> undo=new Stack<Command>();
	private Stack<Command> redo=new Stack<Command>();
	
	public DrawingModel() {
		
	}

	public Stack<Command> getUndo() {
		return undo;
	}
	public void setUndo(Stack<Command> undo) {
		this.undo = undo;
	}
	public Stack<Command> getRedo() {
		return redo;
	}
	public void setRedo(Stack<Command> redo) {
		this.redo = redo;
	}
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
	
	public void deleteAll(ArrayList<Shape> shapes){
		shapes.removeAll(shapes);
	}
	public void addAll(ArrayList<Shape> shapes){
		shapes.addAll(shapes);
	}
	public void deleteAllFromSelected(ArrayList<Shape> shapes){
		shapes.removeAll(shapes);
	}
	public int getIndexOfShape(Shape shape){
		return shapes.indexOf(shape);
	}
	public void deleteAtIndex(int index){
		shapes.remove(index);
	}
	public void addOnIndex(Shape shape, int index){
		shapes.add(index, shape);
	}
}
