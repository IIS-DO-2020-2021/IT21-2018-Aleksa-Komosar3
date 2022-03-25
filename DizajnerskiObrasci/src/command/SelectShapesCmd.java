package command;

import geometry.Shape;
import mvc.DrawingFrame;
import mvc.DrwingModel;

public class SelectShapesCmd implements Command {
	private Shape shapes;
	private DrwingModel drawingModel;
	
	public SelectShapesCmd(Shape shapes, DrwingModel drawingModel) {
		//super();
		this.shapes = shapes;
		this.drawingModel = drawingModel;
	}
	
	@Override
	public void execute() {
		shapes.setSelected(true);
		drawingModel.addSelected(shapes);

	}

	@Override
	public void unexecute() {
		shapes.setSelected(false);
		drawingModel.removeSelected(shapes);

	}
	
}
