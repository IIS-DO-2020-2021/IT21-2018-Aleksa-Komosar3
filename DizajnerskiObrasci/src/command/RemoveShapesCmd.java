package command;

import java.util.ArrayList;

import geometry.Shape;
import mvc.DrawingFrame;
import mvc.DrwingModel;

public class RemoveShapesCmd implements Command {
	//ovde ce trebati lista za undo i redo!!!
	private ArrayList <Shape> shapes;
	private DrwingModel drawingModel;
	private DrawingFrame frame;
	
	private ArrayList<Shape> tempListObj = new ArrayList<Shape>();
	
	

	//obrati paznju na konstruktor ovde i listu!!! Puca ako se ne inicijalizuje tj nece odmah da obrise
	public RemoveShapesCmd(ArrayList<Shape> shapes, DrwingModel drawingModel, DrawingFrame frame) {
		super();
		this.shapes = new ArrayList<Shape>(shapes);
		this.drawingModel = drawingModel;
		this.frame=frame;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		//tempListObj.addAll(drawingModel.getShapes());
		frame.getTxtAreaLog().append("Delete: " + drawingModel.getSelectedShapes().toString() + "\n");
		for(Shape s : shapes) {
			drawingModel.remove(s);
			drawingModel.removeSelected(s);
		}
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		drawingModel.getShapes().clear();
		drawingModel.getShapes().addAll(tempListObj);
		for(Shape s : shapes) {
			drawingModel.add(s);
			drawingModel.addSelected(s);
		}
		frame.getTxtAreaLog().append("Undo-> Delete: " + drawingModel.getSelectedShapes().toString()+ "\n");
	}

}
