package command;

import geometry.HexagonAdapter;
import geometry.Point;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter
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
		
		try {
			original=oldHex.clone(original);
			//original.setSelected(true);
		} catch (CloneNotSupportedException e) {
			
			e.printStackTrace();
		}
		try {
			oldHex=newHex.clone(oldHex);
			oldHex.setSelected(true);
		} catch (CloneNotSupportedException e) {
			
			e.printStackTrace();
		}
	}

	@Override
	public void unexecute() {
		
		try {
			oldHex=original.clone(oldHex);
			oldHex.setSelected(true);
		} catch (CloneNotSupportedException e) {
			
			e.printStackTrace();
		}
	}
	
	public String toString() {
		return "Modifing: "  + original.toString() + "\n" ;
	}

}
