package command;

import geometry.Shape;
import mvc.DrwingModel;

public class RemoveShapeCmd implements Command{
	
	//ovde ce trebati lista za undo i redo!!!
	private Shape shape;
	private DrwingModel drawingModel;
	
	public RemoveShapeCmd(Shape shape, DrwingModel drawingModel) {
		
		this.shape = shape;
		this.drawingModel = drawingModel;
	}
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		drawingModel.remove(shape);
	}
	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		drawingModel.add(shape);
	}

}
