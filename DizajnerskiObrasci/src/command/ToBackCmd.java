package command;

import geometry.Shape;
import mvc.DrwingModel;

public class ToBackCmd implements Command {

	private DrwingModel drawingModel;
	private int i;
	private Shape shapes;
	
	
	public ToBackCmd(Shape shapes, DrwingModel drawingModel) {
		super();
		this.drawingModel = drawingModel;
		this.shapes = shapes;
		this.i = drawingModel.getShapes().indexOf(shapes);
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		drawingModel.getShapes().remove(i);
		drawingModel.getShapes().add(i - 1, shapes);

	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		
		drawingModel.getShapes().remove(i - 1);
		drawingModel.getShapes().add(i, shapes);

	}

}
