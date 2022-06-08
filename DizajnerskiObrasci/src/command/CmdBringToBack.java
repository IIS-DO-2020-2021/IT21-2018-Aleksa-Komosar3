package command;

import geometry.Shape;
import mvc.DrawingModel;

public class CmdBringToBack implements Command {
	
	private DrawingModel model;
	private Shape shape;
	private int index;

	public CmdBringToBack(DrawingModel model, Shape shape, int index) {
		super();
		this.model = model;
		this.shape = shape;
		this.index=index;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		try {
		model.deleteAtIndex(index);
		model.addOnIndex(shape, 0);
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		try {
		model.deleteAtIndex(0);
		model.addOnIndex(shape, index);
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
		}
	}

}
