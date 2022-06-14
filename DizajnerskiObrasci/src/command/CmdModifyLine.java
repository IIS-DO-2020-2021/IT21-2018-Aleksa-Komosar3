package command;

import geometry.Line;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class CmdModifyLine implements Command {
	
	private Line oldLine, newLine;
	private Line original=new Line();

	public CmdModifyLine(Line oldLine, Line newLine) {
		super();
		this.oldLine = oldLine;
		this.newLine = newLine;
	}

	@Override
	public void execute() {
		
		try {
			original = oldLine.clone(original);
			original.setSelected(true);
		} catch (CloneNotSupportedException e) {
			
			e.printStackTrace();
		}
		try {
			oldLine = newLine.clone(oldLine);
			oldLine.setSelected(true);
		} catch (CloneNotSupportedException e) {
			
			e.printStackTrace();
		}

	}

	@Override
	public void unexecute() {
		
		try {
			oldLine = original.clone(oldLine);
			oldLine.setSelected(true);
		} catch (CloneNotSupportedException e) {
			
			e.printStackTrace();
		}
	}
	
	public String toString() {
		return "Modifing: "  + original.toString() + "\n" ;
	}

}
