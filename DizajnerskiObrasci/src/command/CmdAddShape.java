package command;

import geometry.Shape;
import mvc.DrawingModel;

public class CmdAddShape implements Command {
	
	private DrawingModel model;
	private Shape shape;

	public CmdAddShape(DrawingModel model, Shape shape) {
		super();
		this.model = model;
		this.shape = shape;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		model.addShapeToList(shape);

	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		model.deleteShapeFromList(shape);

	}

}
