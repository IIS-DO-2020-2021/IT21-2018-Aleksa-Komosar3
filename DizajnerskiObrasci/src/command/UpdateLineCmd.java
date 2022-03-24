package command;

import geometry.Line;
import mvc.DrawingFrame;

public class UpdateLineCmd implements Command {

	private Line oldState;
	private Line newState;
	private Line original=new Line();
	private DrawingFrame frame;
	
	public UpdateLineCmd(Line oldState, Line newState, DrawingFrame frame) {
		//super();
		this.oldState = oldState;
		this.newState = newState;
		this.frame=frame;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		/*original.setStartPoint(oldState.getStartPoint());
		original.setEndPoint(oldState.getEndPoint());
		original.setColor(oldState.getColor());*/
		original = oldState.clone();

		oldState.setStartPoint(newState.getStartPoint());
		oldState.setEndPoint(newState.getEndPoint());
		oldState.setColor(newState.getColor());
		
		frame.getTxtAreaLog().append("Modify: " + original.toString() + " To: " + newState.toString() + "\n");
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		oldState.setStartPoint(original.getStartPoint());
		oldState.setEndPoint(original.getEndPoint());
		oldState.setColor(original.getColor());
		
		frame.getTxtAreaLog().append("Undo-> Modify: " + oldState.toString() + " To: " + newState.toString() + "\n");
	}

}
