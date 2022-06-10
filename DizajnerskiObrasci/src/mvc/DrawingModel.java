package mvc;

import java.util.ArrayList;
import java.util.Stack;

import command.Command;
import geometry.Shape;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter @ToString
public class DrawingModel {

	private ArrayList<Shape> shapes = new ArrayList<Shape>();
	private ArrayList<Shape> selectedShapes = new ArrayList<Shape>();
	private Stack<Command> undo=new Stack<Command>();
	private Stack<Command> redo=new Stack<Command>();
	
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
	public void getShapeFromIndex (int index){
		shapes.get(index);
	}
}
