package command;

import java.util.ArrayList;

import geometry.Shape;
import mvc.DrawingModel;

public class CmdDeleteShape implements Command {
	
	private DrawingModel model;
	private ArrayList<Shape> shapes;
	private ArrayList<Shape> shape;
	
	public CmdDeleteShape(ArrayList<Shape> shapes,ArrayList<Shape> shape, DrawingModel model) {
		super();
		this.shapes =new ArrayList<Shape>(shapes);
		this.shape=shape;
		this.model=model;
	}

	@Override
	public void execute() {
			for (Shape sh : shapes) {
				this.model.deleteShapeFromSelectedList(sh);
				this.model.deleteShapeFromList(sh);
		}
	}

	@Override
	public void unexecute() {

		for (Shape shape : shapes) {
			model.addShapeToList(shape);
			model.addShapeToListOfSelected(shape);
		}
	}
}
