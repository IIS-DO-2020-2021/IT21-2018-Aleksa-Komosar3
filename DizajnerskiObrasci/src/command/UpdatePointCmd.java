package command;

import geometry.Point;
import mvc.DrawingFrame;

public class UpdatePointCmd implements Command {
	//ovde model ne treba!!! Tako se pravi cvrsta veza to nije ok!!!! Nema reference na model u updateu!!!

	//prave se dve reference na tacke
	private Point oldState;
	private Point newState;
	private Point original=new Point();
	
	
	public UpdatePointCmd(Point oldState, Point newState) {
		this.oldState=oldState;
		this.newState=newState;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
		//p1 ce pokazati na p2 tj novo i izgubimo p1 onda, tj pokazivac na oldState i onda caos
		//mora se promeniti vrednosti a ne reference
		/*original.setX(oldState.getX());
		original.setY(oldState.getY());
		original.setColor(oldState.getColor());*/
		original = oldState.clone();
		
		oldState.setX(newState.getX());
		oldState.setY(newState.getY());
		oldState.setColor(newState.getColor());

	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		oldState.setX(original.getX());
		oldState.setY(original.getY());
		oldState.setColor(original.getColor());
		
	}

}
