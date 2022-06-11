package command;

import geometry.Rectangle;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@AllArgsConstructor @NoArgsConstructor @Getter @Setter @ToString
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
			original.setSelected(true);
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			oldRectangle = newRectangle.clone(oldRectangle);
			oldRectangle.setSelected(true);
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
			oldRectangle.setSelected(true);
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
