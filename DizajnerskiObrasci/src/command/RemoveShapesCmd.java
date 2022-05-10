package command;

import java.util.ArrayList;

import geometry.Shape;
import mvc.DrwingModel;

public class RemoveShapesCmd implements ICommand {
	//ovde ce trebati lista za undo i redo!!!
	private ArrayList <Shape> shapes;
	private DrwingModel drawingModel;
	
	private ArrayList<Shape> temp = new ArrayList<Shape>();
	
	//obrati paznju na konstruktor ovde i listu!!! Puca ako se ne inicijalizuje tj nece odmah da obrise
	public RemoveShapesCmd(ArrayList<Shape> shapes, DrwingModel drawingModel) {
		this.shapes = new ArrayList<Shape>(shapes);
		this.drawingModel = drawingModel;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		temp.addAll(drawingModel.getShapes());
		for (Shape s : shapes) {
			drawingModel.remove(s);
			drawingModel.removeSelected(s);
		}
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		drawingModel.getShapes().clear();
		drawingModel.getShapes().addAll(temp);
		for (Shape s : shapes) {
			drawingModel.addSelected(s);
		}
	}

}
