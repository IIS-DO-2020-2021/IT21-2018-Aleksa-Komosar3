package command;

import geometry.Shape;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.NoArgsConstructor;

import mvc.DrawingModel;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter @ToString
public class CmdAddShape implements Command {
	
	private DrawingModel model;
	private Shape shape;

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		model.addShapeToList(shape);

	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		model.deleteShapeFromList(shape);

	}

}
