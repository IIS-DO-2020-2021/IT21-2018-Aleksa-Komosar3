package command;

import adapter.HexagonAdapter;

public class UpdateHexagonCmd implements ICommand {

	private HexagonAdapter oldState;
	private HexagonAdapter newState;
	private HexagonAdapter originalHex=new HexagonAdapter();

	public UpdateHexagonCmd(HexagonAdapter oldState, HexagonAdapter newState) {
		this.oldState = oldState;
		this.newState = newState;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		originalHex = oldState.clone();

		oldState.getHexagon().setX(newState.getHexagon().getX());
		oldState.getHexagon().setY(newState.getHexagon().getY());
		oldState.getHexagon().setR(newState.getHexagon().getR());
		oldState.getHexagon().setBorderColor(newState.getHexagon().getBorderColor());
		oldState.getHexagon().setAreaColor(newState.getHexagon().getAreaColor());
		oldState.getHexagon().setSelected(newState.getHexagon().isSelected());
		
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		oldState.getHexagon().setX(originalHex.getHexagon().getX());
		oldState.getHexagon().setY(originalHex.getHexagon().getY());
		oldState.getHexagon().setR(originalHex.getHexagon().getR());
		oldState.getHexagon().setBorderColor(originalHex.getHexagon().getBorderColor());
		oldState.getHexagon().setAreaColor(originalHex.getHexagon().getAreaColor());
		oldState.getHexagon().setSelected(originalHex.getHexagon().isSelected());
		
	}

}
