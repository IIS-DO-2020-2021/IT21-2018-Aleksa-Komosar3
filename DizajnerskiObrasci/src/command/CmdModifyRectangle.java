package command;

import geometry.Rectangle;

public class CmdModifyRectangle implements Command {
	
	private Rectangle oldRectangle, newRectangle;
	private Rectangle original=new Rectangle();

	public CmdModifyRectangle(Rectangle oldRectangle, Rectangle newRectangle) {
		super();
		this.oldRectangle = oldRectangle;
		this.newRectangle = newRectangle;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		try {
			original = oldRectangle.clone(original);
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			oldRectangle = newRectangle.clone(oldRectangle);
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		try {
			oldRectangle = original.clone(oldRectangle);
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
