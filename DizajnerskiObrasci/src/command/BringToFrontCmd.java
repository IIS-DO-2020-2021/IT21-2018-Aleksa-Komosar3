package command;

import geometry.Shape;
import mvc.DrawingFrame;
import mvc.DrwingModel;

public class BringToFrontCmd implements Command {
	
	private DrwingModel drawingModel;
	private int i;
	private Shape shapes;
	private DrawingFrame frame;

	
	public BringToFrontCmd(Shape shapes, DrwingModel drawingModel, DrawingFrame frame) {
		super();
		this.drawingModel = drawingModel;
		this.shapes = shapes;
		this.i = drawingModel.getShapes().indexOf(shapes);
		this.frame=frame;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		drawingModel.getShapes().remove(i);
		drawingModel.getShapes().add(shapes);
		
		frame.getTxtAreaLog().append("BringToFront: " + shapes.toString() + "\n");

	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		
		drawingModel.getShapes().remove(drawingModel.getShapes().size()-1);
		drawingModel.getShapes().add(i, shapes);
		
		frame.getTxtAreaLog().append("Undo-> BringToFront: " + shapes.toString() + "\n");

	}

}
