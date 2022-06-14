package command;

import geometry.Shape;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mvc.DrawingModel;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class CmdBringToFront implements Command {
	
	private DrawingModel model;
	private Shape shape;
	private int index;

	@Override
	public void execute() {
		try{
			model.deleteAtIndex(index);
			model.getShapes().add(shape);
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void unexecute() {
		try {
			model.deleteAtIndex(model.getShapes().size()-1);
			model.addOnIndex(shape, index);
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
		}
	}
	
	public String toString() {
		return "BringToFront: "  + shape.toString() + "\n" ;
	}

}
