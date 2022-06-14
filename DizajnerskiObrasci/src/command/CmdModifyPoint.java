package command;

import geometry.Point;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor @NoArgsConstructor @Getter @Setter
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
		
		try {
			original = oldPoint.clone(original);
			original.setSelected(true);
		} catch (CloneNotSupportedException e) {
			
			e.printStackTrace();
		}
		try {
			oldPoint = newPoint.clone(oldPoint);
			oldPoint.setSelected(true);
		} catch (CloneNotSupportedException e) {
			
			e.printStackTrace();
		}
	}

	@Override
	public void unexecute() {
		
		try {
			oldPoint = original.clone(oldPoint);
			oldPoint.setSelected(true);
		} catch (CloneNotSupportedException e) {
			
			e.printStackTrace();
		}
	}
	
	public String toString() {
		return "Modifing: "  + original.toString() + "\n" ;
	}

}
