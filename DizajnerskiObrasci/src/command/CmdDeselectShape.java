package command;

import geometry.Shape;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import mvc.DrawingModel;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter @ToString
public class CmdDeselectShape implements Command {
	
	private DrawingModel model;
	private Shape shape;

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		shape.setSelected(false);
		model.deleteShapeFromSelectedList(shape);

	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		shape.setSelected(true);
		model.addShapeToListOfSelected(shape);
	}
	
	public String toString() {
		return "Deselect: "  + shape.toString() + "\n" ;
	}

}
