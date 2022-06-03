package command;

import geometry.Shape;
import mvc.DrawingModel;

public class CmdDeleteOneShape implements Command {
	
	private DrawingModel model;
	private Shape shape;

	public CmdDeleteOneShape(DrawingModel model, Shape shape) {
		super();
		this.model = model;
		this.shape = shape;
	}

	@Override
	public void execute() {
		model.deleteShapeFromList(shape);
		model.deleteShapeFromSelectedList(shape);
	}

	@Override
	public void unexecute() {
			model.addShapeToList(shape);
			model.addShapeToListOfSelected(shape);
	}
}
