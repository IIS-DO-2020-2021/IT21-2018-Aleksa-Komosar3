package command;
import geometry.Shape;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mvc.DrawingModel;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class CmdToFrontByOne implements Command {
	
	private DrawingModel model;
	private Shape shape;
	private int index;

	@Override
	public void execute() {
		
		try{
			model.deleteAtIndex(index);
			model.addOnIndex(shape, index+1);
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void unexecute() {
		try{
			model.deleteAtIndex(index+1);
			model.addOnIndex(shape, index);
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
		}
	}
	
	public String toString() {
		return "ToFront: "  + shape.toString() + "\n" ;
	}

}
