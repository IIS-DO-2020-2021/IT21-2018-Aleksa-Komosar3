package command;

import geometry.Shape;
import mvc.DrawingFrame;
import mvc.DrwingModel;

public class UnselectShapesCmd implements Command {
	
	private Shape shapes;
	private DrwingModel drawingModel;

	public UnselectShapesCmd(Shape shapes, DrwingModel drawingModel) {
		super();
		this.shapes = shapes;
		this.drawingModel = drawingModel;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		shapes.setSelected(false);
		drawingModel.removeSelected(shapes);

	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		shapes.setSelected(true);
		drawingModel.addSelected(shapes);
	}

}
