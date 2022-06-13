package command;

import geometry.Shape;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import mvc.DrawingModel;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter @ToString
public class CmdDeleteOneShape implements Command {
	
	private DrawingModel model;
	private Shape shape;

	@Override
	public void execute() {
		model.deleteShapeFromList(shape);
		model.deleteShapeFromSelectedList(shape);
	}

	@Override
	public void unexecute() {
			model.addShapeToList(shape);
			model.addShapeToListOfSelected(shape);
	}
	
	public String toString() {
		return "Deleting: "  + shape.toString() + "\n" ;
	}
}
