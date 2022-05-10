package util;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.event.ActionEvent;
import adapter.HexagonAdapter;
import command.AddShapeCmd;
import command.ICommand;
import command.RemoveShapesCmd;
import command.SelectShapesCmd;
import command.UnselectShapesCmd;
import command.UpdateCircleCmd;
import command.UpdateDonutCmd;
import command.UpdateHexagonCmd;
import command.UpdateLineCmd;
import command.UpdatePointCmd;
import command.UpdateRectangleCmd;
import geometry.Circle;
import geometry.Donut;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;
import mvc.DrawingController;
import mvc.DrawingFrame;
import mvc.DrwingModel;

public class LogUtil implements ILogUtil {
	DrawingFrame frame;
	DrawingController controller;
	DrwingModel model;
	
	public LogUtil(DrwingModel model, DrawingFrame frame, DrawingController controller) {
		this.model = model;
		this.frame = frame;
		this.controller = controller;
	}

	@Override
	public void loadFileByLoadingType(int type, Scanner logFile) {
		// TODO Auto-generated method stub
		try {
			if(logFile.hasNextLine()){
				if (type == 0) {
					while (logFile.hasNextLine()) {
						String lineLog = logFile.nextLine();
						executeLineLog(lineLog);
					}
					ImageIcon img=new ImageIcon("C:/Users/EC/git/IT21-2018-Aleksa-Komosar3/DizajnerskiObrasci/icons/succ.png");
					JOptionPane.showMessageDialog(frame, "Log is loaded!", "OK", JOptionPane.OK_OPTION, img);
					logFile.close();
				} else if (type == 1) {
					frame.getTxtAreaLog().append("Click next for the first step of drawing.\n");
					frame.getBtnNext().setEnabled(true);
					frame.getBtnNext().addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent a) {
							executeLineLog(logFile.nextLine());
							if (!logFile.hasNextLine()) {
								frame.getBtnNext().setEnabled(false);
								ImageIcon img=new ImageIcon("C:/Users/EC/git/IT21-2018-Aleksa-Komosar3/DizajnerskiObrasci/icons/succ.png");
								JOptionPane.showMessageDialog(frame, "Log is loaded!", "OK", JOptionPane.OK_OPTION, img);
								logFile.close();
							}
						}
					});
				}
			} else {
				JOptionPane.showMessageDialog(frame, "Try again! Something is not good.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		catch(Exception exception) {
			exception.printStackTrace();
			JOptionPane.showMessageDialog(frame, "Try again! Something is not good.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	@Override
	public void executeLineLog(String lineLog) {
		// TODO Auto-generated method stub
		String[] splitLine = lineLog.split(" ");
		ICommand cmd = null;

		String command = splitLine[0];
		try {
			switch(command) {
			case "Add:":
				this.executeAdding(lineLog, splitLine, cmd);
				break;
			case "Modify:":
				this.executeModifying(lineLog, splitLine, cmd);
				break;
			case "Delete:": 
				this.executeDeleting(lineLog, splitLine, cmd);
				break;
			case "Select:":
				this.executeSelect(lineLog, splitLine, cmd);
				break;
			case "Unselect:":
				this.executeUnselect(lineLog, splitLine, cmd);
				break;
			case "BringToBack:":
				controller.bringToBack();
				break;
			case "BringToFront:":
				controller.bringToFront();
				break;
			case "ToBack:":
				controller.toBack();
				break;
			case "ToFront:":
				controller.toFront();
				break;
			case "Undo->":
				controller.undo();
				break;
			case "Redo->":
				controller.redo();
				break;
			}
		}
			catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(frame, "File is corrupted!", "Error", JOptionPane.ERROR_MESSAGE);
				controller.getHistoryForUndo().clear();
				controller.getHistoryForRedo().clear();
				frame.getBtnRedo().setEnabled(false);
				frame.repaint();
			}
	}

	private void executeAdding(String lineLog, String[] splitLineLog, ICommand cmd) {
		Shape shape = getShapesFromLog(lineLog, splitLineLog[1], false);
		if (shape != null) {
			cmd = new AddShapeCmd(shape, model);
			cmd.execute();
			controller.pushHistoryForUndo(cmd);
			frame.getTxtAreaLog().append("Add: " + shape.toString() + "\n");
		}
		controller.positionOfObject();
		controller.getHistoryForRedo().clear();
		frame.getBtnRedo().setEnabled(false);
		frame.repaint();
	}

	private void executeDeleting(String lineLog, String[] splitLineLog, ICommand cmd) {
		frame.getTxtAreaLog().append("Delete: " + model.getSelectedShapes().toString() + "\n");
		cmd = new RemoveShapesCmd(model.getSelectedShapes(), model);
		cmd.execute();
		controller.pushHistoryForUndo(cmd);
		controller.positionOfObject();
		controller.getHistoryForRedo().clear();
		frame.getBtnRedo().setEnabled(false);
		frame.repaint();
	}

	private void executeModifying(String lineLog, String[] splitLineLog, ICommand cmd) {
		Shape oldShape = getShapesFromLog(lineLog, splitLineLog[1], false);
		oldShape.setSelected(true);
		Shape newShape = getShapesFromLog(lineLog, splitLineLog[1], true);
		for (Shape s : model.getShapes())
			if (oldShape.equals(s))
				oldShape = s;

		if (splitLineLog[1].equals("Point"))
			cmd = new UpdatePointCmd((Point) oldShape, (Point) newShape);
		else if (splitLineLog[1].equals("Line"))
			cmd = new UpdateLineCmd((Line) oldShape, (Line) newShape);
		else if (splitLineLog[1].equals("Rectangle"))
			cmd = new UpdateRectangleCmd((Rectangle) oldShape, (Rectangle) newShape);
		else if (splitLineLog[1].equals("Circle"))
			cmd = new UpdateCircleCmd((Circle) oldShape, (Circle) newShape);
		else if (splitLineLog[1].equals("Donut"))
			cmd = new UpdateDonutCmd((Donut) oldShape, (Donut) newShape);
		else if (splitLineLog[1].equals("Hexagon"))
			cmd = new UpdateHexagonCmd((HexagonAdapter) oldShape, (HexagonAdapter) newShape);
		frame.getTxtAreaLog().append("Modify: " + oldShape.toString() + " To: " + newShape.toString() + "\n");
		cmd.execute();
		controller.pushHistoryForUndo(cmd);
		controller.getHistoryForRedo().clear();
		frame.getBtnRedo().setEnabled(false);
		frame.repaint();
	}

	private void executeSelect(String lineLog, String[] splitLineLog, ICommand cmd) {
		Shape shape = getShapesFromLog(lineLog, splitLineLog[1], false);
		for (Shape s : model.getShapes())
			if (shape.equals(s)) {
				shape = s;
			}
		cmd = new SelectShapesCmd(shape, model);
		cmd.execute();
		frame.getTxtAreaLog().append("Select: " + shape.toString() + "\n");
		controller.pushHistoryForUndo(cmd);
		controller.positionOfObject();
		controller.getHistoryForRedo().clear();
		frame.getBtnRedo().setEnabled(false);
		frame.repaint();
	}

	private void executeUnselect(String lineLog, String[] splitLineLog, ICommand cmd) {
		Shape shape = getShapesFromLog(lineLog, splitLineLog[1], false);
		shape.setSelected(true);
									
		for (Shape s : model.getShapes())
			if (shape.equals(s))
				shape = s;
		cmd = new UnselectShapesCmd(shape, model);
		cmd.execute();

		frame.getTxtAreaLog().append("Unselect: " + shape.toString() + "\n");
		controller.pushHistoryForUndo(cmd);
		controller.positionOfObject();
		controller.getHistoryForRedo().clear();
		frame.getBtnRedo().setEnabled(false);
		frame.repaint();
	}

	@Override
	public Shape getShapesFromLog(String lineLog, String stringShape, Boolean second) {
		// TODO Auto-generated method stub
		String[] numbersString = lineLog.replaceAll("[^0-9]", " ").split(" ");
		int position = 0;
		ArrayList<Integer> numbersFromString = new ArrayList<Integer>();
		for (String s : numbersString) {
			if (s==null || s.trim().isEmpty())
				continue;
			numbersFromString.add(Integer.parseInt(s));
		}
		Shape shapeFromLog = null;
		if (second) {
			if (stringShape.equals("Point"))
				position = 5;
			else if (stringShape.equals("Line"))
				position = 7;
			else if (stringShape.equals("Circle") || stringShape.equals("Hexagon"))
				position = 9;
			else if (stringShape.equals("Rectangle") || stringShape.equals("Donut"))
				position = 10;
				
		}
		if (stringShape.equals("Point")) {
			int x = numbersFromString.get(0 + position), y = numbersFromString.get(1 + position), r = numbersFromString.get(2 + position), g = numbersFromString.get(3 + position), b = numbersFromString.get(4 + position);
			Point p = new Point(x, y);
			p.setColor(new Color(r, g, b));
			shapeFromLog = p;

		} else if (stringShape.equals("Line")) {
			int x1 = numbersFromString.get(0 + position),  y1 = numbersFromString.get(1 + position), x2 = numbersFromString.get(2 + position), y2 = numbersFromString.get(3 + position), r = numbersFromString.get(4 + position), g = numbersFromString.get(5 + position), b = numbersFromString.get(6 + position);
			Line l = new Line(new Point(x1, y1), new Point(x2, y2));
			l.setColor(new Color(r, g, b));
			shapeFromLog = l;

		} else if (stringShape.equals("Rectangle")) {
			int x1 = numbersFromString.get(0 + position), y1 = numbersFromString.get(1 + position), w = numbersFromString.get(2 + position), h = numbersFromString.get(3 + position), r1 = numbersFromString.get(4 + position), g1 = numbersFromString.get(5 + position), b1 = numbersFromString.get(6 + position), r2 = numbersFromString.get(7 + position), g2 = numbersFromString.get(8 + position), b2 = numbersFromString.get(9 + position);
			Rectangle r = new Rectangle(new Point(x1, y1), w, h);
			r.setColor(new Color(r1, g1, b1));
			r.setInnerColor(new Color(r2, g2, b2));
			shapeFromLog = r;

		} else if (stringShape.equals("Circle")) {
			int x1 = numbersFromString.get(0 + position), y1 = numbersFromString.get(1 + position), r = numbersFromString.get(2 + position), r1 = numbersFromString.get(3 + position), g1 = numbersFromString.get(4 + position), b1 = numbersFromString.get(5 + position), r2 = numbersFromString.get(6 + position), g2 = numbersFromString.get(7 + position), b2 = numbersFromString.get(8 + position);
			Circle c = new Circle(new Point(x1, y1), r);
			c.setColor(new Color(r1, g1, b1));
			c.setInnerColor(new Color(r2, g2, b2));
			shapeFromLog = c;

		} else if (stringShape.equals("Donut")) {
			int x1 = numbersFromString.get(0 + position), y1 = numbersFromString.get(1 + position), a1 = numbersFromString.get(2 + position), a2 = numbersFromString.get(3 + position), r1 = numbersFromString.get(4 + position), g1 = numbersFromString.get(5 + position), b1 = numbersFromString.get(6 + position), r2 = numbersFromString.get(7 + position), g2 = numbersFromString.get(8 + position), b2 = numbersFromString.get(9 + position);
			Donut d = new Donut(new Point(x1, y1), a1, a2);
			d.setColor(new Color(r1, g1, b1));
			d.setInnerColor(new Color(r2, g2, b2));
			shapeFromLog = d;

		} else if (stringShape.equals("Hexagon")) {
			int x1 = numbersFromString.get(0 + position), y1 = numbersFromString.get(1 + position), a = numbersFromString.get(2 + position), r1 = numbersFromString.get(3 + position), g1 = numbersFromString.get(4 + position), b1 = numbersFromString.get(5 + position), r2 = numbersFromString.get(6 + position), g2 = numbersFromString.get(7 + position), b2 = numbersFromString.get(8 + position);
			HexagonAdapter h = new HexagonAdapter(new Point(x1, y1), a);
			h.getHexagon().setBorderColor(new Color(r1, g1, b1));
			h.getHexagon().setAreaColor(new Color(r2, g2, b2));
			shapeFromLog = h;
		}
		return shapeFromLog;
	}
}
