package command;

import geometry.Circle;

public class UpdateCircleCmd implements ICommand {
	private Circle oldState;
	private Circle newState;
	private Circle originalCircle = new Circle();
	
	public UpdateCircleCmd(Circle oldState, Circle newState) {
		
		this.oldState = oldState;
		this.newState = newState;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		originalCircle = oldState.clone();

		oldState.getCenter().setX(newState.getCenter().getX());
		oldState.getCenter().setY(newState.getCenter().getY());
		oldState.setRadius(newState.getRadius());
		oldState.setColor(newState.getColor());
		oldState.setInnerColor(newState.getInnerColor());
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		oldState.getCenter().setX(originalCircle.getCenter().getX());
		oldState.getCenter().setY(originalCircle.getCenter().getY());
		oldState.setRadius(originalCircle.getRadius());
		oldState.setColor(originalCircle.getColor());
		oldState.setInnerColor(originalCircle.getInnerColor());
	}

}
