package command;

import geometry.Shape;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mvc.DrawingModel;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class CmdBringToBack implements Command {
	
	private DrawingModel model;
	private Shape shape;
	private int index;

	@Override
	public void execute() {
		try {
			model.deleteAtIndex(index);
			model.addOnIndex(shape, 0);
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void unexecute() {
		
		try {
			model.deleteAtIndex(0);
			model.addOnIndex(shape, index);
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
		}
	}
	
	public String toString() {
		return "BringToBack: "  + shape.toString() + "\n" ;
	}

}
