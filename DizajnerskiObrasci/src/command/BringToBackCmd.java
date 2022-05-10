package command;

import geometry.Shape;
import mvc.DrwingModel;

public class BringToBackCmd implements ICommand {
	
	private Shape shapes;
	private DrwingModel drawingModel;
	private int i;
	

	public BringToBackCmd(Shape shapes, DrwingModel drawingModel) {
		super();
		this.shapes = shapes;
		this.drawingModel = drawingModel;
		this.i = drawingModel.getShapes().indexOf(shapes);
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		drawingModel.getShapes().remove(i);
		drawingModel.getShapes().add(0, shapes);

	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		drawingModel.getShapes().remove(0);
		drawingModel.getShapes().add(i, shapes);
		
	}

}
