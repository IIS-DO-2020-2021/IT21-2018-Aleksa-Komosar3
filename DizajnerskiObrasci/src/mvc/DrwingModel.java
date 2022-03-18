package mvc;

import java.util.ArrayList;
import java.util.Stack;

import command.Command;
import geometry.Shape;

public class DrwingModel {
	private ArrayList<Shape> shapes = new ArrayList<Shape>();
	private ArrayList<Shape> selected = new ArrayList<Shape>();
	
	private Stack<Command> cmdHistory = new Stack<Command>();
	private Stack<Command> cmdUndoHistory = new Stack<Command>();

	public void add(Shape s) {
		shapes.add(s);
	}
	public void remove(Shape s) {
		shapes.remove(s);
	}

	public Shape getSelectedShape(int i) {
		return selected.get(i);
	}

	public ArrayList<Shape> getShapes() {
		return shapes;
	}

	public ArrayList<Shape> getSelected() {
		return selected;
	}

	public ArrayList<Shape> getSelectedShapes() {
		return selected;
	}
	
	public Shape getShape(int i) {
		return shapes.get(i);
	}

	/*Selected*/
	public void addSelected(Shape s) {
		selected.add(s);
	}

	public void removeSelected(Shape s) {
		selected.remove(s);
	}
	
	public Command popCmdHistory() {
		return cmdHistory.pop();
	}

	public void pushCmdHistory(Command c) {
		cmdHistory.add(c);
	}

	public Stack<Command> getCmdHistory() {
		return cmdHistory;
	}


	public Command popCmdUndoHistory() {
		return cmdUndoHistory.pop();
	}

	public void pushCmdUndoHistory(Command c) {
		cmdUndoHistory.add(c);
	}

	public Stack<Command> getCmdUndoHistory() {
		return cmdUndoHistory;
	}
}
