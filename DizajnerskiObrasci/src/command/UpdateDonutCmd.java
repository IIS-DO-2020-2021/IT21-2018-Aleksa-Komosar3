package command;

import javax.swing.JOptionPane;

import geometry.Donut;
import mvc.DrawingFrame;

public class UpdateDonutCmd implements ICommand {
	
	private Donut oldState;
	private Donut newState;
	private Donut originalDonut=new Donut();
	
	public UpdateDonutCmd(Donut oldState, Donut newState) {
		this.oldState = oldState;
		this.newState = newState;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		originalDonut = oldState.clone();

		oldState.getCenter().setX(newState.getCenter().getX());
		oldState.getCenter().setY(newState.getCenter().getY());
		oldState.setRadius(newState.getRadius());
		try {
			oldState.setInnerRadius(newState.getInnerRadius());
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		oldState.setColor(newState.getColor());
		oldState.setInnerColor(newState.getInnerColor());
		
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		oldState.getCenter().setX(originalDonut.getCenter().getX());
		oldState.getCenter().setY(originalDonut.getCenter().getY());
		oldState.setRadius(originalDonut.getRadius());
		try {
			oldState.setInnerRadius(originalDonut.getInnerRadius());
		} catch (Exception e) {
			e.printStackTrace();
		}
		oldState.setColor(originalDonut.getColor());
		oldState.setInnerColor(originalDonut.getInnerColor());
		
	}

}
