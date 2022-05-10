package command;

import geometry.Rectangle;

public class UpdateRectangleCmd implements ICommand {

	private Rectangle oldState;
	private Rectangle newState;
	private Rectangle originalRec=new Rectangle();
	
	public UpdateRectangleCmd(Rectangle oldState, Rectangle newState) {
		super();
		this.oldState = oldState;
		this.newState = newState;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub		
		originalRec = oldState.clone();

		oldState.getUpperLeft().setX(newState.getUpperLeft().getX());
		oldState.getUpperLeft().setY(newState.getUpperLeft().getY());
		oldState.setHeight(newState.getHeight());
		oldState.setWidth(newState.getWidth());
		oldState.setColor(newState.getColor());
		oldState.setInnerColor(newState.getInnerColor());
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		oldState.getUpperLeft().setX(originalRec.getUpperLeft().getX());
		oldState.getUpperLeft().setY(originalRec.getUpperLeft().getY());
		oldState.setHeight(originalRec.getHeight());
		oldState.setWidth(originalRec.getWidth());
		oldState.setColor(originalRec.getColor());
		oldState.setInnerColor(originalRec.getInnerColor());
	}

}
