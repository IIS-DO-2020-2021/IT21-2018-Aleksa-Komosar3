package command;

import java.util.ArrayList;

import geometry.Shape;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import mvc.DrawingModel;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter @ToString
public class CmdDeleteShape implements Command {
	
	private DrawingModel model;
	private ArrayList<Shape> shape;
	
	private ArrayList<Shape> temp = new ArrayList<Shape>();

	public CmdDeleteShape(DrawingModel model,  ArrayList<Shape> shape) {
		//super();
		this.model = model;
		this.shape = new ArrayList<Shape>(shape);
	}

	@Override
	public void execute() {
		temp.addAll(model.getShapes());
		for(Shape s : shape) {
			model.deleteShapeFromList(s);
			model.deleteShapeFromSelectedList(s);
		}
		
	}

	@Override
	public void unexecute() {
		//model.getSelectedShapes().clear();
		model.getShapes().clear();
		//model.getSelectedShapes().clear();
		model.getShapes().addAll(temp);
		for (Shape s : shape) {
			model.addShapeToListOfSelected(s);
		}

	}
}
