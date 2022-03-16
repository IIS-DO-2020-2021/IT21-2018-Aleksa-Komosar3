package command;

import geometry.Point;
import mvc.DrwingModel;

public class RemovePointCmd implements Command {
	
	private Point point;
	private DrwingModel drawingModel;
	
	
	public RemovePointCmd(Point point, DrwingModel drawingModel){
		this.point=point;
		this.drawingModel=drawingModel;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub

		drawingModel.remove(point);
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub

		drawingModel.add(point);
	}

}
