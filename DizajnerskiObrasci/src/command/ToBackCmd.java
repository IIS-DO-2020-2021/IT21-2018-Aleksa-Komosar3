package command;

import geometry.Shape;
import mvc.DrawingFrame;
import mvc.DrwingModel;

public class ToBackCmd implements Command {

	private Shape shapes;
	private DrwingModel drawingModel;
	private int i;
	private DrawingFrame frame;
	
	
	public ToBackCmd(Shape shapes, DrwingModel drawingModel, DrawingFrame frame) {
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
		drawingModel.getShapes().add(i - 1, shapes);
		
		frame.getTxtAreaLog().append("ToBack: " + shapes.toString() + "\n");

	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		
		drawingModel.getShapes().remove(i - 1);
		drawingModel.getShapes().add(i, shapes);
		
		frame.getTxtAreaLog().append("Undo-> ToBack: " + shapes.toString() + "\n");

	}

}
