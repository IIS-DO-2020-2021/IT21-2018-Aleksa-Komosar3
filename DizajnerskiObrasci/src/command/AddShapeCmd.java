package command;

import geometry.Shape;
import mvc.DrwingModel;

public class AddShapeCmd implements Command {
	private Shape shape;
	private DrwingModel drawingModel;
	
	
	public AddShapeCmd(Shape shape, DrwingModel drawingModel) {
		
		this.shape = shape;
		this.drawingModel = drawingModel;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		drawingModel.add(shape);
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		drawingModel.remove(shape);
	}
	
}
