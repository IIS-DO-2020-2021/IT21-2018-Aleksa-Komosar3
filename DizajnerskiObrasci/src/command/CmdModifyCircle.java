package command;

import geometry.Circle;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class CmdModifyCircle implements Command {
	
	private Circle oldCircle, newCircle;
	private Circle original=new Circle();
	
	public CmdModifyCircle(Circle oldCircle, Circle newCircle) {
		this.oldCircle = oldCircle;
		this.newCircle = newCircle;
	}

	@Override
	public void execute() {
		
		try {
			original=oldCircle.clone(original);
			//original.setSelected(true);
		} catch (CloneNotSupportedException e) {
			
			e.printStackTrace();
		}
		try {
			oldCircle=newCircle.clone(oldCircle);
			oldCircle.setSelected(true);
		} catch (CloneNotSupportedException e) {
			
			e.printStackTrace();
		}
		
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		try {
			oldCircle=original.clone(oldCircle);
			//oldCircle.setSelected(true);
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String toString() {
		return "Modifing: "  + original.toString() + "\n" ;
	}
}
