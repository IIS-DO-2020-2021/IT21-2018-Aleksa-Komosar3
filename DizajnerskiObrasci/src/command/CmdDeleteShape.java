package command;

import geometry.Shape;
import mvc.DrawingModel;

public class CmdDeleteShape implements Command {
	
	private DrawingModel model;
	private Shape shape;

	public DrawingModel getModel() {
		return model;
	}

	public void setModel(DrawingModel model) {
		this.model = model;
	}

	public Shape getShape() {
		return shape;
	}

	public void setShape(Shape shape) {
		this.shape = shape;
	}

	public CmdDeleteShape(DrawingModel model, Shape shape) {
		super();
		this.model = model;
		this.shape = shape;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		model.deleteShapeFromList(shape);
		model.deleteShapeFromSelectedList(shape);
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		model.addShapeToList(shape);
		model.addShapeToListOfSelected(shape);
	}

}
