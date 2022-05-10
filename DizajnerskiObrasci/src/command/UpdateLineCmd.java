package command;

import geometry.Line;

public class UpdateLineCmd implements ICommand {

	private Line oldState;
	private Line newState;
	private Line originalLine=new Line();
	
	public UpdateLineCmd(Line oldState, Line newState) {
		//super();
		this.oldState = oldState;
		this.newState = newState;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		originalLine = oldState.clone();

		oldState.setStartPoint(newState.getStartPoint());
		oldState.setEndPoint(newState.getEndPoint());
		oldState.setColor(newState.getColor());
		
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		oldState.setStartPoint(originalLine.getStartPoint());
		oldState.setEndPoint(originalLine.getEndPoint());
		oldState.setColor(originalLine.getColor());
	}

}
