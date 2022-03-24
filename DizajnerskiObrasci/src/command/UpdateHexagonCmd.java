package command;

import adapter.HexagonAdapter;
import mvc.DrawingFrame;

public class UpdateHexagonCmd implements Command {

	private HexagonAdapter oldState;
	private HexagonAdapter newState;
	private HexagonAdapter original=new HexagonAdapter();
	private DrawingFrame frame;

	
	public UpdateHexagonCmd(HexagonAdapter oldState, HexagonAdapter newState, DrawingFrame frame) {
		super();
		this.oldState = oldState;
		this.newState = newState;
		this.frame = frame;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		original = oldState.clone();

		oldState.getHexagon().setX(newState.getHexagon().getX());
		oldState.getHexagon().setY(newState.getHexagon().getY());
		oldState.getHexagon().setR(newState.getHexagon().getR());
		oldState.getHexagon().setBorderColor(newState.getHexagon().getBorderColor());
		oldState.getHexagon().setAreaColor(newState.getHexagon().getAreaColor());
		oldState.getHexagon().setSelected(newState.getHexagon().isSelected());
		
		frame.getTxtAreaLog().append("Modify: " + original.toString() + " To: " + newState.toString() + "\n");
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		oldState.getHexagon().setX(original.getHexagon().getX());
		oldState.getHexagon().setY(original.getHexagon().getY());
		oldState.getHexagon().setR(original.getHexagon().getR());
		oldState.getHexagon().setBorderColor(original.getHexagon().getBorderColor());
		oldState.getHexagon().setAreaColor(original.getHexagon().getAreaColor());
		oldState.getHexagon().setSelected(original.getHexagon().isSelected());
		
		frame.getTxtAreaLog().append("Undo-> Modify: " + oldState.toString() + " To: " + newState.toString() + "\n");
	}

}
