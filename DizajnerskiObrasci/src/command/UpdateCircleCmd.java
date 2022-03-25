package command;

import geometry.Circle;
import mvc.DrawingFrame;

public class UpdateCircleCmd implements Command {
	private Circle oldState;
	private Circle newState;
	private Circle original = new Circle();
	
	public UpdateCircleCmd(Circle oldState, Circle newState) {
		
		this.oldState = oldState;
		this.newState = newState;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		/*original.getCenter().setX(oldState.getCenter().getX());
		original.getCenter().setY(oldState.getCenter().getY());
		original.setRadius(oldState.getRadius());
		original.setColor(oldState.getColor());
		original.setInnerColor(oldState.getInnerColor());*/
		original = oldState.clone();

		oldState.getCenter().setX(newState.getCenter().getX());
		oldState.getCenter().setY(newState.getCenter().getY());
		oldState.setRadius(newState.getRadius());
		oldState.setColor(newState.getColor());
		oldState.setInnerColor(newState.getInnerColor());
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		oldState.getCenter().setX(original.getCenter().getX());
		oldState.getCenter().setY(original.getCenter().getY());
		oldState.setRadius(original.getRadius());
		oldState.setColor(original.getColor());
		oldState.setInnerColor(original.getInnerColor());
	}

}
