package command;

import geometry.Rectangle;
import mvc.DrawingFrame;

public class UpdateRectangleCmd implements Command {

	private Rectangle oldState;
	private Rectangle newState;
	private Rectangle original=new Rectangle();
	
	public UpdateRectangleCmd(Rectangle oldState, Rectangle newState) {
		super();
		this.oldState = oldState;
		this.newState = newState;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		/*original.getUpperLeft().setX(oldState.getUpperLeft().getX());
		original.getUpperLeft().setY(oldState.getUpperLeft().getY());
		original.setHeight(oldState.getHeight());
		original.setWidth(oldState.getWidth());
		original.setColor(oldState.getColor());
		original.setInnerColor(oldState.getInnerColor());*/
		
		original = oldState.clone();

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
		oldState.getUpperLeft().setX(original.getUpperLeft().getX());
		oldState.getUpperLeft().setY(original.getUpperLeft().getY());
		oldState.setHeight(original.getHeight());
		oldState.setWidth(original.getWidth());
		oldState.setColor(original.getColor());
		oldState.setInnerColor(original.getInnerColor());
		
	}

}
