package command;

import geometry.Shape;
import mvc.DrawingFrame;
import mvc.DrwingModel;

public class UnselectShapesCmd implements Command {
	
	private Shape shapes;
	private DrwingModel drawingModel;
	private DrawingFrame frame;

	public UnselectShapesCmd(Shape shapes, DrwingModel drawingModel, DrawingFrame frame) {
		super();
		this.shapes = shapes;
		this.drawingModel = drawingModel;
		this.frame=frame;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		shapes.setSelected(false);
		drawingModel.removeSelected(shapes);
		
		frame.getTxtAreaLog().append("Unselect: " + shapes.toString() + "\n");
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		shapes.setSelected(true);
		drawingModel.addSelected(shapes);
		
		frame.getTxtAreaLog().append("Undo-> Unselect: " + shapes.toString() + "\n");
	}

}
