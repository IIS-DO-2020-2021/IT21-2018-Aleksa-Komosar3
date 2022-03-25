package command;

import geometry.Shape;
import mvc.DrawingFrame;
import mvc.DrwingModel;

public class ToFrontCmd implements Command {
	
	private Shape shapes;
	private DrwingModel drawingModel;
	private int i;
	

	public ToFrontCmd(Shape shapes, DrwingModel drawingModel) {
		this.shapes = shapes;
		this.drawingModel = drawingModel;
		this.i = drawingModel.getShapes().indexOf(shapes);
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
		drawingModel.getShapes().remove(i);
		drawingModel.getShapes().add(i+1, shapes);

	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		
		drawingModel.getShapes().remove(i + 1);
		drawingModel.getShapes().add(i, shapes);

	}

}
