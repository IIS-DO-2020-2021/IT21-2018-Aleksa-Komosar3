package command;

import geometry.Shape;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import mvc.DrawingModel;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter @ToString
public class CmdBringToFront implements Command {
	
	private DrawingModel model;
	private Shape shape;
	private int index;

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		try{
		model.deleteAtIndex(index);
		model.getShapes().add(shape);
	} catch (IndexOutOfBoundsException e) {
		e.printStackTrace();
		}
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
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
