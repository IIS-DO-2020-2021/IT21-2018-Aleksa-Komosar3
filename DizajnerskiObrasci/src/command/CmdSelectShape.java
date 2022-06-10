package command;

import geometry.Shape;
import mvc.DrawingModel;

public class CmdSelectShape implements Command {
	
	private DrawingModel model;
	private Shape shape;

	public CmdSelectShape(DrawingModel model, Shape shape) {
		super();
		this.model = model;
		this.shape = shape;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		shape.setSelected(true);
		model.addShapeToListOfSelected(shape);

	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		shape.setSelected(false);
		model.deleteShapeFromSelectedList(shape);
	}

}
