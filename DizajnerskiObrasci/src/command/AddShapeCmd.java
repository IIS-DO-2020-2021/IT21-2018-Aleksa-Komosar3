package command;

import geometry.Shape;
import mvc.DrawingFrame;
import mvc.DrwingModel;

public class AddShapeCmd implements Command {
	private Shape shape;
	private DrwingModel drawingModel;
	private DrawingFrame frame;
	
	public AddShapeCmd(Shape shape, DrwingModel drawingModel, DrawingFrame frame) {
		
		this.shape = shape;
		this.drawingModel = drawingModel;
		this.frame=frame;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		drawingModel.add(shape);
		
		frame.getTxtAreaLog().append("Add: " + shape.toString() + "\n");
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		drawingModel.remove(shape);
		
		frame.getTxtAreaLog().append("Undo-> Add: " + shape.toString() + "\n");
	}
	
}
