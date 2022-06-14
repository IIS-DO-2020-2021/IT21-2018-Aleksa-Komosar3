package command;

import geometry.Donut;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class CmdModifyDonut implements Command {
	
	private Donut oldDonut,newDonut;
	private Donut original=new Donut();
	
	public CmdModifyDonut(Donut oldDonut, Donut newDonut) {
		super();
		this.oldDonut = oldDonut;
		this.newDonut = newDonut;
	}

	@Override
	public void execute() {
		try {
			original=oldDonut.clone(original);
			original.setSelected(true);
		} catch (CloneNotSupportedException e) {
			
			e.printStackTrace();
		}
		try {
			oldDonut=newDonut.clone(oldDonut);
			oldDonut.setSelected(true);
		} catch (CloneNotSupportedException e) {
			
			e.printStackTrace();
		}
	}

	@Override
	public void unexecute() {
		
		try {
			oldDonut=original.clone(oldDonut);
			//newDonut.setSelected(true);
		} catch (CloneNotSupportedException e) {
			
			e.printStackTrace();
		}
	}
	
	public String toString() {
		return "Modifing: "  + original.toString() + "\n" ;
	}

}
