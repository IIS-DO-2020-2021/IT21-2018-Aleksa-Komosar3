package command;

import geometry.Point;
import mvc.DrwingModel;

public class AddPointCmd implements Command {

	//izvrsavanje dodavanja tacke logicno, treba nam tacka model
	//treba nam lista da cuvamo komande za undo i redo i tjt
	
	private Point point;
	private DrwingModel drawingModel;
	
	
	public AddPointCmd(Point point, DrwingModel drawingModel)
	{
		this.point=point;
		this.drawingModel=drawingModel;
		
	}
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		drawingModel.add(point);
		
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		drawingModel.remove(point);
		
	}
	

}
