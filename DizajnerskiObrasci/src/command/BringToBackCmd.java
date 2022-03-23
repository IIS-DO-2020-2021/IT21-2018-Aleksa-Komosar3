package command;

import geometry.Shape;
import mvc.DrawingFrame;
import mvc.DrwingModel;

public class BringToBackCmd implements Command {
	
	private Shape shapes;
	private DrwingModel drawingModel;
	private DrawingFrame frame;
	private int i;
	

	public BringToBackCmd(Shape shapes, DrwingModel drawingModel, DrawingFrame frame) {
		super();
		this.shapes = shapes;
		this.drawingModel = drawingModel;
		this.i = drawingModel.getShapes().indexOf(shapes);
		this.frame = frame;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		drawingModel.getShapes().remove(i);
		drawingModel.getShapes().add(0, shapes);
		
		frame.getTxtAreaLog().append("BringToBack: " + shapes.toString() + "\n");

	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub

		drawingModel.getShapes().remove(0);
		drawingModel.getShapes().add(i, shapes);
		
		frame.getTxtAreaLog().append("Undo-> BringToBack: " + shapes.toString() + "\n");
	}

}
