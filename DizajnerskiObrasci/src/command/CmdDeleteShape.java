package command;

import java.util.ArrayList;

import geometry.Shape;
import mvc.DrawingModel;

public class CmdDeleteShape implements Command {
	
	private DrawingModel model;
	private ArrayList<Shape> shape;

	public CmdDeleteShape(DrawingModel model, ArrayList<Shape> shape) {
		super();
		this.model = model;
		this.shape = new ArrayList<Shape>(shape);
	}

	@Override
	public void execute() {
		for(Shape s : shape) {
			model.deleteShapeFromList(s);
			model.deleteShapeFromSelectedList(s);
		}
	}

	@Override
	public void unexecute() {
		model.addAll(shape);
	}
}
