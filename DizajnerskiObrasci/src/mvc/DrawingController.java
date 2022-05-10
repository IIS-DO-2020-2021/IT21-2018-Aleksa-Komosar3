package mvc;
import command.*;

import geometry.*;
import observer.SizeOfSelected;
import strategy.DrawingFile;
import strategy.FileStrategy;
import strategy.LogFile;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import drawing.Drawing;
import javax.swing.JFileChooser;

public class DrawingController {
	private DrwingModel model;
	private DrawingFrame frame;
	private Point point;
	private Drawing drawing;
	
	private Stack<ICommand> historyForUndo = new Stack<ICommand>();
	private Stack<ICommand> historyForRedo = new Stack<ICommand>();
	
	
	public DrawingController(DrwingModel model, DrawingFrame frame)  {
		this.model = model;
		this.frame = frame;
		this.drawing = new Drawing();
		SizeOfSelected sizeOfSelected = new SizeOfSelected(frame);
		model.addPropertyChangeListener(sizeOfSelected);
	}

	public DrwingModel getModel() {
		return model;
	}

	public void setModel(DrwingModel model) {
		this.model = model;
	}

	public DrawingFrame getFrame() {
		return frame;
	}

	public void setFrame(DrawingFrame frame) {
		this.frame = frame;
	}

	public Point getStartPoint() {
		return point;
	}

	public void setStartPoint(Point point) {
		this.point = point;
	}

	public Drawing getDrawing() {
		return drawing;
	}

	public void setDrawing(Drawing drawing) {
		this.drawing = drawing;
	}

	public void setCmdHistory(Stack<ICommand> historyForUndo) {
		this.historyForUndo = historyForUndo;
	}

	public void setCmdUndoHistory(Stack<ICommand> historyForRedo) {
		this.historyForRedo = historyForRedo;
	}

	protected void delete() {
		this.drawing.delete(this);
	}
	
	protected void modify() {
		this.drawing.modify(this);
	}
	
	public void mouseClickSelected(MouseEvent e, Shape newShape) {
		this.drawing.mouseClickSelected(this, e, newShape);
	}
	
	public void mouseClicked(MouseEvent e) {
		Shape newSh=null;
		mouseClickSelected(e, newSh);
		
		if(!getHistoryForUndo().isEmpty()) {
			frame.getBtnUndo().setEnabled(true);
		}
		positionOfObject();
		getHistoryForRedo().clear();
		frame.getBtnRedo().setEnabled(false);
		frame.repaint();
	}
	
	public void undo() {
		if(!getHistoryForUndo().isEmpty()) {
			frame.getTxtAreaLog().append("Undo-> ");
			ICommand command = popHistoryForUndo();
			command.unexecute();
			printCommand(command);
			pushHistoryForRedo(command);
			frame.getBtnRedo().setEnabled(true);
			if(getHistoryForUndo().isEmpty()) {
				frame.getBtnUndo().setEnabled(false);
			}
		}
		positionOfObject();
		frame.repaint();
	}
	
	public void redo() {
		if(!getHistoryForRedo().isEmpty()) {
			frame.getTxtAreaLog().append("Redo-> ");
			ICommand command = popHistoryForRedo();
			command.execute();
			printCommand(command);
			pushHistoryForUndo(command);
			frame.getBtnUndo().setEnabled(true);
			if(getHistoryForRedo().isEmpty()) {
				frame.getBtnRedo().setEnabled(false);
			}
		}
		positionOfObject();
		frame.repaint();
	}
	
	public void printCommand(ICommand command) {
		Map<Class<?>, String> types = new HashMap<>();
		types.put(AddShapeCmd.class, "Add\n");
		types.put(RemoveShapesCmd.class, "Delete\n");
		types.put(SelectShapesCmd.class, "Select\n");
		types.put(UnselectShapesCmd.class, "Unselect\n");
		types.put(BringToBackCmd.class, "BringToBack\n");
		types.put(BringToFrontCmd.class, "BringToFront\n");
		types.put(ToBackCmd.class, "ToBack\n");
		types.put(ToFrontCmd.class, "ToFront\n");
		
		boolean found = false;
		for (Map.Entry<Class<?>, String> entry : types.entrySet()) {
			if(entry.getKey().isInstance(command)){
				found = true;
				frame.getTxtAreaLog().append(entry.getValue());
			}
	    }
		if(!found) {
			frame.getTxtAreaLog().append("Modify\n");
		}
	}
	
	public void positionOfObject() {
		if(model.getSelectedShapes().size()==1) {
			int i = model.getShapes().indexOf(model.getSelectedShapes().get(0));
			if (model.getShapes().size()==1) {
				frame.getBtnToFront().setEnabled(false);
				frame.getBtnToBack().setEnabled(false);
				frame.getBtnBringToFront().setEnabled(false);
				frame.getBtnBringToBack().setEnabled(false);
			} else if(i+1 == model.getShapes().size()) {
				frame.getBtnToFront().setEnabled(false);
				frame.getBtnToBack().setEnabled(true);
				frame.getBtnBringToFront().setEnabled(false);
				frame.getBtnBringToBack().setEnabled(true);
			} else if (i == 0) {
				frame.getBtnToFront().setEnabled(true);
				frame.getBtnToBack().setEnabled(false);
				frame.getBtnBringToFront().setEnabled(true);
				frame.getBtnBringToBack().setEnabled(false);
			} else if (i>0 && i+1<model.getShapes().size()) {
				frame.getBtnToFront().setEnabled(true);
				frame.getBtnToBack().setEnabled(true);
				frame.getBtnBringToFront().setEnabled(true);
				frame.getBtnBringToBack().setEnabled(true);
			}
		} else {
			frame.getBtnToFront().setEnabled(false);
			frame.getBtnToBack().setEnabled(false);
			frame.getBtnBringToFront().setEnabled(false);
			frame.getBtnBringToBack().setEnabled(false);
		}
	}
	
	public void toFront() {
		Shape shape = model.getSelectedShapes().get(0);
		ICommand command = new ToFrontCmd(shape, model);
		command.execute();
		pushHistoryForUndo(command);
		frame.getTxtAreaLog().append("ToFront: " + shape.toString() + "\n");
		positionOfObject();
		getHistoryForRedo().clear();
		frame.getBtnRedo().setEnabled(false);
		frame.repaint();
	}
	
	public void toBack() {
		Shape shape = model.getSelectedShapes().get(0);
		ICommand command = new ToBackCmd(shape, model);
		command.execute();
		pushHistoryForUndo(command);
		frame.getTxtAreaLog().append("ToBack: " + shape.toString() + "\n");
		positionOfObject();
		getHistoryForRedo().clear();
		frame.getBtnRedo().setEnabled(false);
		frame.repaint();
	}
	
	public void bringToFront() {
		Shape shape = model.getSelectedShapes().get(0);
		ICommand command = new BringToFrontCmd(shape, model);
		command.execute();
		pushHistoryForUndo(command);
		frame.getTxtAreaLog().append("BringToFront: " + shape.toString() + "\n");
		positionOfObject();
		getHistoryForRedo().clear();
		frame.getBtnRedo().setEnabled(false);
		frame.repaint();
	}
	
	public void bringToBack () {
		Shape shape = model.getSelectedShapes().get(0);
		ICommand command = new BringToBackCmd(shape, model);
		command.execute();
		pushHistoryForUndo(command);
		frame.getTxtAreaLog().append("BringToBack: " + shape.toString() + "\n");
		positionOfObject();
		getHistoryForRedo().clear();
		frame.getBtnRedo().setEnabled(false);
		frame.repaint();
	}

	public void saveDrawing() {
		JFileChooser jfileCSaveDrw = new JFileChooser();
		int selection = jfileCSaveDrw.showSaveDialog(frame);
		
		if(selection == JFileChooser.APPROVE_OPTION) {
			String path = jfileCSaveDrw.getSelectedFile().getPath();
			FileStrategy str = new DrawingFile(model, frame);
			str.save(path);
		}
	}

	public void loadDrawing() {
		JFileChooser jfcLoadDrawing = new JFileChooser();
		int selection = jfcLoadDrawing.showOpenDialog(frame);
		
		if(selection == JFileChooser.APPROVE_OPTION) {
			frame.getTxtAreaLog().setText("");
			model.getShapes().clear();
			model.getSelectedShapes().clear();
			getHistoryForUndo().clear();
			getHistoryForRedo().clear();
			String path = jfcLoadDrawing.getSelectedFile().getPath();
			FileStrategy strategy = new DrawingFile(model, frame);
			strategy.load(path);
			frame.repaint();
		}		
	}

	public void saveLog() {
		JFileChooser jfcSaveLog = new JFileChooser();
		int selection = jfcSaveLog.showSaveDialog(frame);
		
		if(selection == JFileChooser.APPROVE_OPTION) {
			String path = jfcSaveLog.getSelectedFile().getPath();
			FileStrategy strategy = new LogFile(model, frame, this);
			strategy.save(path);
		}
		
	}

	public void loadLog() {
		JFileChooser jfcLoadLog = new JFileChooser();
		int selection = jfcLoadLog.showOpenDialog(frame);
		
		if(selection == JFileChooser.APPROVE_OPTION) {
			frame.getTxtAreaLog().setText("");
			model.getShapes().clear();
			model.getSelectedShapes().clear();
			getHistoryForUndo().clear();
			getHistoryForRedo().clear();
			String path = jfcLoadLog.getSelectedFile().getPath();
			FileStrategy strategy = new LogFile(model, frame, this);
			strategy.load(path);
			frame.repaint();
		}
	}
	
	
	public ICommand popHistoryForUndo() {
		return historyForUndo.pop();
	}
	
	public void pushHistoryForUndo(ICommand c) {
		historyForUndo.add(c);
	}
	
	public Stack<ICommand> getHistoryForUndo() {
		return historyForUndo;
	}
	
	public ICommand popHistoryForRedo() {
		return historyForRedo.pop();
	}
	
	public void pushHistoryForRedo(ICommand c) {
		historyForRedo.add(c);
	}
	
	public Stack<ICommand> getHistoryForRedo() {
		return historyForRedo;
	}
}
