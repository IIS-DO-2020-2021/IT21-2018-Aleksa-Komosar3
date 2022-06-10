package command;

import geometry.Circle;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter @ToString
public class CmdModifyCircle implements Command {
	
	private Circle oldCircle, newCircle;
	private Circle original=new Circle();
	
	public CmdModifyCircle(Circle oldCircle, Circle newCircle) {
		super();
		this.oldCircle = oldCircle;
		this.newCircle = newCircle;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		try {
			original=oldCircle.clone(original);
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			oldCircle=newCircle.clone(oldCircle);
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		try {
			oldCircle=original.clone(oldCircle);
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
