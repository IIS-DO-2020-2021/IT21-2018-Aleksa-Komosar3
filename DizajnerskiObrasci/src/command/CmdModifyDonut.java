package command;

import geometry.Donut;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter @ToString
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
		// TODO Auto-generated method stub
		try {
			original=oldDonut.clone(original);
			original.setSelected(true);
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			oldDonut=newDonut.clone(oldDonut);
			oldDonut.setSelected(true);
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		try {
			oldDonut=original.clone(oldDonut);
			//newDonut.setSelected(true);
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String toString() {
		return "Modifing: "  + original.toString() + "\n" ;
	}

}
