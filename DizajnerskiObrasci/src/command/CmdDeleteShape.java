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

	public CmdDeleteShape( ArrayList<Shape> shape, DrawingModel model) {
		super();
		this.shape = new ArrayList<Shape>(shape);
		this.model = model;
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
		model.getShapes().addAll(temp);
		for (Shape s : shape) {
			model.addShapeToListOfSelected(s);
		}
	}
}
