package command;

import geometry.Line;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter @ToString
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
		// TODO Auto-generated method stub
		try {
			original = oldLine.clone(original);
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			oldLine = newLine.clone(oldLine);
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		try {
			oldLine = original.clone(oldLine);
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
