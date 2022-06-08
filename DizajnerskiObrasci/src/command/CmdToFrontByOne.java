package command;

import geometry.Shape;
import mvc.DrawingModel;

public class CmdToFrontByOne implements Command {
	
	private DrawingModel model;
	private Shape shape;
	private int index;

	public CmdToFrontByOne(DrawingModel model, Shape shape, int index) {
		super();
		this.model = model;
		this.shape = shape;
		this.index = index;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		model.deleteAtIndex(index+1);
		model.addOnIndex(shape, index);
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
		}
	}

}
