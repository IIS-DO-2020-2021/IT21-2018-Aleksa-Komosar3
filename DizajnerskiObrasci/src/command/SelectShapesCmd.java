package command;

import geometry.Shape;
import mvc.DrawingFrame;
import mvc.DrwingModel;

public class SelectShapesCmd implements Command {
	private Shape shapes;
	private DrwingModel drawingModel;
	private DrawingFrame frame;
	
	public SelectShapesCmd(Shape shapes, DrwingModel drawingModel, DrawingFrame frame) {
		//super();
		this.shapes = shapes;
		this.drawingModel = drawingModel;
		this.frame=frame;
	}
	
	@Override
	public void execute() {
		shapes.setSelected(true);
		drawingModel.addSelected(shapes);
		
		frame.getTxtAreaLog().append("Select: " + shapes.toString() + "\n");
	}

	@Override
	public void unexecute() {
		shapes.setSelected(false);
		drawingModel.removeSelected(shapes);
		
		frame.getTxtAreaLog().append("Undo-> Select: " + shapes.toString() + "\n");
	}
	
}
