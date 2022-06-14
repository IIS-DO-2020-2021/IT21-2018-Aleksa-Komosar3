package command;

import geometry.Rectangle;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor @NoArgsConstructor @Getter @Setter
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
		
		try {
			original = oldRectangle.clone(original);
			original.setSelected(true);
		} catch (CloneNotSupportedException e) {
			
			e.printStackTrace();
		}
		try {
			oldRectangle = newRectangle.clone(oldRectangle);
			oldRectangle.setSelected(true);
		} catch (CloneNotSupportedException e) {
			
			e.printStackTrace();
		}
	}

	@Override
	public void unexecute() {
		
		try {
			oldRectangle = original.clone(oldRectangle);
			oldRectangle.setSelected(true);
		} catch (CloneNotSupportedException e) {
			
			e.printStackTrace();
		}
	}
	
	public String toString() {
		return "Modifing: "  + original.toString() + "\n" ;
	}

}
