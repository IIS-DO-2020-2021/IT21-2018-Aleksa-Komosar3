package command;

import geometry.Rectangle;
import mvc.DrawingFrame;

public class UpdateRectangleCmd implements Command {

	private Rectangle oldState;
	private Rectangle newState;
	private Rectangle original=new Rectangle();
	private DrawingFrame frame;
	
	public UpdateRectangleCmd(Rectangle oldState, Rectangle newState, DrawingFrame frame) {
		super();
		this.oldState = oldState;
		this.newState = newState;
		this.frame=frame;
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
		
		frame.getTxtAreaLog().append("Modify: " + original.toString() + " To: " + newState.toString() + "\n");
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
		
		frame.getTxtAreaLog().append("Undo-> Modify: " + oldState.toString() + " To: " + newState.toString() + "\n");
	}

}
