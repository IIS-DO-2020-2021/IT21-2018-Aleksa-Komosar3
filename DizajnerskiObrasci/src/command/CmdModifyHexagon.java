package command;

import geometry.HexagonAdapter;
import geometry.Point;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter @ToString
public class CmdModifyHexagon implements Command {
	
	private HexagonAdapter oldHex,newHex;
	private HexagonAdapter original=new HexagonAdapter(new Point(0, 0), 0);

	public CmdModifyHexagon(HexagonAdapter oldHex, HexagonAdapter newHex) {
		super();
		this.oldHex = oldHex;
		this.newHex = newHex;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		try {
			original=oldHex.clone(original);
			//original.setSelected(true);
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			oldHex=newHex.clone(oldHex);
			oldHex.setSelected(true);
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		try {
			oldHex=original.clone(oldHex);
			oldHex.setSelected(true);
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String toString() {
		return "Modifing: "  + original.toString() + "\n" ;
	}

}
