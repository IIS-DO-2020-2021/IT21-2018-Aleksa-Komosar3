package command;

import geometry.Donut;
import mvc.DrawingFrame;

public class UpdateDonutCmd implements Command {
	
	private Donut oldState;
	private Donut newState;
	private Donut original=new Donut();
	private DrawingFrame frame;

	
	public UpdateDonutCmd(Donut oldState, Donut newState, DrawingFrame frame) {
		super();
		this.oldState = oldState;
		this.newState = newState;
		this.frame=frame;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		/*original.getCenter().setX(oldState.getCenter().getX());
		original.getCenter().setY(oldState.getCenter().getY());
		original.setRadius(oldState.getRadius());
		try {
			original.setInnerRadius(oldState.getInnerRadius());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		original.setColor(oldState.getColor());
		original.setInnerColor(oldState.getInnerColor());*/
		
		original = oldState.clone();

		oldState.getCenter().setX(newState.getCenter().getX());
		oldState.getCenter().setY(newState.getCenter().getY());
		oldState.setRadius(newState.getRadius());
		try {
			oldState.setInnerRadius(newState.getInnerRadius());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		oldState.setColor(newState.getColor());
		oldState.setInnerColor(newState.getInnerColor());
		
		frame.getTxtAreaLog().append("Modify: " + original.toString() + " To: " + newState.toString() + "\n");
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		oldState.getCenter().setX(original.getCenter().getX());
		oldState.getCenter().setY(original.getCenter().getY());
		oldState.setRadius(original.getRadius());
		try {
			oldState.setInnerRadius(original.getInnerRadius());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		oldState.setColor(original.getColor());
		oldState.setInnerColor(original.getInnerColor());
		
		frame.getTxtAreaLog().append("Undo-> Modify: " + oldState.toString() + " To: " + newState.toString() + "\n");
	}

}
