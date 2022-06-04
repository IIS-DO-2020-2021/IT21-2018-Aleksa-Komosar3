package command;

import geometry.HexagonAdapter;

public class CmdModifyHexagon implements Command {
	
	private HexagonAdapter oldHex,newHex;
	private HexagonAdapter original=new HexagonAdapter();

	public CmdModifyHexagon(HexagonAdapter oldHex, HexagonAdapter newHex) {
		super();
		this.oldHex = oldHex;
		this.newHex = newHex;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		try {
			original=oldHex.clone(newHex);
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			oldHex=newHex.clone(oldHex);
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		try {
			newHex=original.clone(newHex);
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
