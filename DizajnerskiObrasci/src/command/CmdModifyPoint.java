package command;

import geometry.Point;

public class CmdModifyPoint implements Command {
	
	private Point oldPoint, newPoint;
	private Point original=new Point();
	
	public CmdModifyPoint(Point oldPoint, Point newPoint) {
		super();
		this.oldPoint = oldPoint;
		this.newPoint = newPoint;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		try {
			original = oldPoint.clone(original);
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			oldPoint = newPoint.clone(oldPoint);
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub

	}

}