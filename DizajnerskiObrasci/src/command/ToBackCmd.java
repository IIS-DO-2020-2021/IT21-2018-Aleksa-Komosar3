package command;

import geometry.Shape;
import mvc.DrwingModel;

public class ToBackCmd implements ICommand {
	private Shape shapes;
	private DrwingModel drawingModel;
	private int index;
	
	
	public ToBackCmd(Shape shapes, DrwingModel drawingModel) {
		
		this.drawingModel = drawingModel;
		this.shapes = shapes;
		this.index = drawingModel.getShapes().indexOf(shapes);
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		drawingModel.getShapes().remove(index);
		drawingModel.getShapes().add(index - 1, shapes);

	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		
		drawingModel.getShapes().remove(index - 1);
		drawingModel.getShapes().add(index, shapes);

	}

}
