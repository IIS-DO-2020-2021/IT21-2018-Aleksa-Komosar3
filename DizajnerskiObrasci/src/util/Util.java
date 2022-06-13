package util;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

import command.CmdAddShape;
import command.CmdBringToBack;
import command.CmdBringToFront;
import command.CmdDeleteOneShape;
import command.CmdDeselectShape;
import command.CmdModifyCircle;
import command.CmdModifyDonut;
import command.CmdModifyHexagon;
import command.CmdModifyLine;
import command.CmdModifyPoint;
import command.CmdModifyRectangle;
import command.CmdSelectShape;
import command.CmdToBackByOne;
import command.CmdToFrontByOne;
import command.Command;
import geometry.Circle;
import geometry.Donut;
import geometry.HexagonAdapter;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import mvc.DrawingController;
import mvc.DrawingFrame;
import mvc.DrawingModel;
import observer.BtnUpdate;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter @ToString
public class Util implements LogUtil {
	
	DrawingModel model;
	DrawingController controller;
	DrawingFrame frame;
	BtnUpdate btnRedo;
	
	@Override
	public Shape makeShapeFromLog(String lineLog, String stringShape, Boolean second) {
		
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
			int x = numbersFromString.get(0 + position), 
					y = numbersFromString.get(1 + position),
					r = numbersFromString.get(2 + position),
					g = numbersFromString.get(3 + position), 
					b = numbersFromString.get(4 + position);
			Point p = new Point(x, y);
			p.setColor(new Color(r, g, b));
			shapeFromLog = p;

		} else if (stringShape.equals("Line")) {
			int x1 = numbersFromString.get(0 + position), 
					y1 = numbersFromString.get(1 + position), 
					x2 = numbersFromString.get(2 + position), 
					y2 = numbersFromString.get(3 + position), 
					r = numbersFromString.get(4 + position), 
					g = numbersFromString.get(5 + position), 
					b = numbersFromString.get(6 + position);
			Line l = new Line(new Point(x1, y1), new Point(x2, y2));
			l.setColor(new Color(r, g, b));
			shapeFromLog = l;

		} else if (stringShape.equals("Rectangle")) {
			int x1 = numbersFromString.get(0 + position), 
					y1 = numbersFromString.get(1 + position), 
					w = numbersFromString.get(2 + position), 
					h = numbersFromString.get(3 + position), 
					r1 = numbersFromString.get(4 + position), 
					g1 = numbersFromString.get(5 + position),
					b1 = numbersFromString.get(6 + position), 
					r2 = numbersFromString.get(7 + position), 
					g2 = numbersFromString.get(8 + position), 
					b2 = numbersFromString.get(9 + position);
			Rectangle r = new Rectangle(new Point(x1, y1), w, h);
			r.setColor(new Color(r1, g1, b1));
			r.setInnerColor(new Color(r2, g2, b2));
			shapeFromLog = r;

		} else if (stringShape.equals("Circle")) {
			int x1 = numbersFromString.get(0 + position), 
					y1 = numbersFromString.get(1 + position),
					r = numbersFromString.get(2 + position),
					r1 = numbersFromString.get(3 + position), 
					g1 = numbersFromString.get(4 + position), 
					b1 = numbersFromString.get(5 + position), 
					r2 = numbersFromString.get(6 + position),
					g2 = numbersFromString.get(7 + position), 
					b2 = numbersFromString.get(8 + position);
			Circle c = new Circle(new Point(x1, y1), r);
			c.setColor(new Color(r1, g1, b1));
			c.setInnerColor(new Color(r2, g2, b2));
			shapeFromLog = c;

		} else if (stringShape.equals("Donut")) {
			int x1 = numbersFromString.get(0 + position), 
					y1 = numbersFromString.get(1 + position),
					a1 = numbersFromString.get(2 + position), 
					a2 = numbersFromString.get(3 + position), 
					r1 = numbersFromString.get(4 + position), 
					g1 = numbersFromString.get(5 + position),
					b1 = numbersFromString.get(6 + position), 
					r2 = numbersFromString.get(7 + position), 
					g2 = numbersFromString.get(8 + position), 
					b2 = numbersFromString.get(9 + position);
			Donut d = new Donut(new Point(x1, y1), a1, a2);
			d.setColor(new Color(r1, g1, b1));
			d.setInnerColor(new Color(r2, g2, b2));
			shapeFromLog = d;

		} else if (stringShape.equals("Hexagon")) {
			int x1 = numbersFromString.get(0 + position), 
					y1 = numbersFromString.get(1 + position),
					a = numbersFromString.get(2 + position),
					r1 = numbersFromString.get(3 + position),
					g1 = numbersFromString.get(4 + position),
					b1 = numbersFromString.get(5 + position),
					r2 = numbersFromString.get(6 + position), 
					g2 = numbersFromString.get(7 + position), 
					b2 = numbersFromString.get(8 + position);
			HexagonAdapter h = new HexagonAdapter(new Point(x1, y1), a);
			h.setHexagonBorderColor(new Color(r1, g1, b1));
			h.setHexagonInnerColor(new Color(r2, g2, b2));
			shapeFromLog = h;
		}
		
		return shapeFromLog;
	}


	@Override
	public void executeLineLog(String lineLog) {
		
		Command cmd = null;
		String[] splitLine = lineLog.split(" ");

		String command = splitLine[0];
		try {
			switch(command){
				case "Select:":
					this.executeSelect(lineLog, splitLine, cmd);
					break;
				case "Deselect:":
					this.executeDeselect(lineLog, splitLine, cmd);
					break;
				case "Adding:":
					this.executeAdding(lineLog, splitLine, cmd);
					break;
				case "Deleting:": 
					this.executeDeleting(lineLog, splitLine, cmd);
					break;
				case "Modifing:":
					this.executeModifying(lineLog, splitLine, cmd);
					break;
				case "BringToFront:":
					this.executeFullFront(lineLog);
					//controller.fullBringToFront();
					break;
				case "ToFront:":
					this.executeToFront(lineLog);
					//controller.bringToFrontByOne();
					break;
				case "BringToBack:":
					this.executeToBack(lineLog);
					//controller.fullBringToBack();
					break;
				case "ToBack:":
					this.executeToBackByOne(lineLog);
					//controller.bringToBackByOne();
					break;
				case "Undo:":
					this.executeUndo(lineLog);
					break;
				case "Redo:":
					this.executeRedo(lineLog);
					break;
			}
		} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(frame, 
							"File is corrupted!", "Error", JOptionPane.ERROR_MESSAGE);
				model.getRedo().clear();
				model.getRedo().clear();
				btnRedo.setBtnRedoAct(false);
				
				frame.repaint();
			}
	}
	
	
	@Override
	public void loadFileByLoadingType(int type, Scanner logFile) {
		
		try {
			if(logFile.hasNextLine()){
				btnRedo.setBtnUndoAct(true);
				btnRedo.setBtnRedoAct(true);
				if (type == 0){
					while (logFile.hasNextLine()) {
						String lineLog = logFile.nextLine();
						executeLineLog(lineLog);
					}
					JOptionPane.showMessageDialog(frame,
							"Log is loaded!", "OK", JOptionPane.OK_OPTION);
					logFile.close();
				} else if (type == 1) {
					frame.getTextArea().append("Click next for the first step of drawing!\n");
					frame.getBtnNext().setEnabled(true);
					
					frame.getBtnRedo().addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent a) {
								frame.getBtnNext().setEnabled(false);
								logFile.close();
							}
						});
					
					frame.getBtnUndo().addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent a) {
							frame.getBtnNext().setEnabled(false);
							logFile.close();
						}
					});
					
					frame.getBtnBringToBack().addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent a) {
							frame.getBtnNext().setEnabled(false);
							logFile.close();
						}
					});
					
					frame.getBtnBringToFront().addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent a) {
							frame.getBtnNext().setEnabled(false);
							logFile.close();
						}
					});
					
					frame.getBtnToBack().addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent a) {
							frame.getBtnNext().setEnabled(false);
							logFile.close();
						}
					});
					
					frame.getBtnToFront().addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent a) {
							frame.getBtnNext().setEnabled(false);
							logFile.close();
						}
					});
					
					frame.getBtnPoint().addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent a) {
							frame.getBtnNext().setEnabled(false);
							logFile.close();
						}
					});
					
					frame.getBtnRectangle().addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent a) {
							frame.getBtnNext().setEnabled(false);
							logFile.close();
						}
					});
					
					frame.getBtnCircle().addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent a) {
							frame.getBtnNext().setEnabled(false);
							logFile.close();
						}
					});
					
					frame.getBtnHexagon().addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent a) {
							frame.getBtnNext().setEnabled(false);
							logFile.close();
						}
					});
					
					frame.getBtnDonut().addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent a) {
							frame.getBtnNext().setEnabled(false);
							logFile.close();
						}
					});
					
					frame.getBtnLine().addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent a) {
							frame.getBtnNext().setEnabled(false);
							logFile.close();
						}
					});
					
					frame.getBtnNext().addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent a) {
							String lineLog = logFile.nextLine();
							String[] splited = lineLog.split(" ");
							
							executeLineLog(lineLog);
							
							if(splited[0].equals("Deleting:")) {
								int selectedSize = model.getSelectedShapes().size() - 1;
								while(selectedSize > 0) {
									lineLog = logFile.nextLine();
									splited = lineLog.split(" ");
									selectedSize -= 1;
								}
							}
							
							if (!logFile.hasNextLine()) {
								frame.getBtnNext().setEnabled(false);
								JOptionPane.showMessageDialog(frame,
										"Log is loaded!", "OK", JOptionPane.OK_OPTION);
								logFile.close();
							}
						}
					});
				}
			} else {
				JOptionPane.showMessageDialog(frame, 
						"Try again! Something is not good.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		} catch (Exception exception) {
			exception.printStackTrace();
			JOptionPane.showMessageDialog(frame, 
					"Try again! Something is not good.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void executeFullFront(String command) {
		command=command.replace("BringToFront: ", "");
		frame.getTextArea().append("BringToFront: ");
		
		Shape shape = model.getSelectedShapes().get(0);
		int index= model.getShapes().indexOf(shape);
		Command command1 = new CmdBringToFront(model, shape, index);
		command1.execute();
		model.getUndo().add(command1);

		frame.getTextArea().append(command + "\n");
		frame.repaint();
	}
	
	private void executeToFront(String command) {
		command=command.replace("ToFront: ", "");
		frame.getTextArea().append("ToFront: ");
		
		Shape shape = model.getSelectedShapes().get(0);
		int index= model.getShapes().indexOf(shape);
		Command command1 = new CmdToFrontByOne(model, shape, index);
		command1.execute();
		model.getUndo().add(command1);

		frame.getTextArea().append(command + "\n");
		frame.repaint();
	}
	
	private void executeToBack(String command) {
		command=command.replace("BringToBack: ", "");
		frame.getTextArea().append("BringToBack: ");
		
		Shape shape = model.getSelectedShapes().get(0);
		int index= model.getShapes().indexOf(shape);
		Command command1 = new CmdBringToBack(model, shape, index);
		command1.execute();
		model.getUndo().add(command1);

		frame.getTextArea().append(command + "\n");
		frame.repaint();
	}
	
	private void executeToBackByOne(String command) {
		command=command.replace("ToBack: ", "");
		frame.getTextArea().append("ToBack: ");
		
		Shape shape = model.getSelectedShapes().get(0);
		int index= model.getShapes().indexOf(shape);
		Command command1 = new CmdToBackByOne(model, shape, index);
		command1.execute();
		model.getUndo().add(command1);

		frame.getTextArea().append(command + "\n");
		frame.repaint();
	}
	
	
	
	private void executeUndo(String command) {
		command = command.replace("Undo: ", "");
		frame.getTextArea().append("Undo: ");

		if(model.getUndo().size()!=0){
			Command command1 = model.getUndo().pop();
			command1.unexecute();
			model.getRedo().add(command1);
		}
		
		frame.getTextArea().append(command + "\n");
		frame.repaint();
	}
	
	private void executeRedo(String command) {
		command = command.replace("Redo: ", "");
		frame.getTextArea().append("Redo: ");
				
		if(model.getRedo().size()!=0){
			Command command1 = model.getRedo().pop();
			command1.execute();
			model.getUndo().add(command1);
		}
		
		frame.getTextArea().append(command + "\n");
		frame.repaint();
	}

	private void executeAdding(String lineLog, String[] splitLineLog, Command cmd) {
		
		Shape shape = makeShapeFromLog(lineLog, splitLineLog[1], false);
		if (shape != null){
			cmd = new CmdAddShape(model, shape);
			cmd.execute();


			model.getUndo().add(cmd);
			
			frame.getTextArea().append("Adding: " + shape.toString() + "\n");
		}
		
		controller.checkBtnState();
		// model.getUndo().clear();
		btnRedo.setBtnRedoAct(false);
		
		frame.repaint();
	}

	private void executeDeleting(String lineLog, String[] splitLineLog, Command cmd) {		
		// Shape shape = makeShapeFromLog(lineLog, splitLineLog[1], false);
		List<Shape> shapesToDelete = new ArrayList<Shape>();
		for(Shape s: model.getSelectedShapes()) {
			shapesToDelete.add(s);
		}
		
		for (Shape s : shapesToDelete) {
			frame.getTextArea().append("Deleting: " + s.toString() + "\n");
			cmd = new CmdDeleteOneShape(model, s);
			cmd.execute();
			model.getUndo().add(cmd);
		}
		
		controller.checkBtnState();
		// model.getRedo().clear();
		
		btnRedo.setBtnRedoAct(false);
		frame.repaint();
	}

	private void executeModifying(String lineLog, String[] splitLineLog, Command cmd) {
		Shape newShape = makeShapeFromLog(lineLog, splitLineLog[1], true);
		Shape oldShape = model.getSelectedShapes().get(0);
		
		if (splitLineLog[1].equals("Point"))
			cmd = new CmdModifyPoint((Point) oldShape, (Point) newShape);
		else if (splitLineLog[1].equals("Line"))
			cmd = new CmdModifyLine((Line) oldShape, (Line) newShape);
		else if (splitLineLog[1].equals("Rectangle"))
			cmd = new CmdModifyRectangle((Rectangle) oldShape, (Rectangle) newShape);
		else if (splitLineLog[1].equals("Circle"))
			cmd = new CmdModifyCircle((Circle) oldShape, (Circle) newShape);
		else if (splitLineLog[1].equals("Donut"))
			cmd = new CmdModifyDonut((Donut) oldShape, (Donut) newShape);
		else if (splitLineLog[1].equals("Hexagon"))
			cmd = new CmdModifyHexagon((HexagonAdapter) oldShape, (HexagonAdapter) newShape);
			
		frame.getTextArea().append("Modifing: " + oldShape.toString() + " To: " + newShape.toString() + "\n");
		
		cmd.execute();
		model.getUndo().add(cmd);
		
		// model.getRedo().clear();
		btnRedo.setBtnRedoAct(false);
		
		
		frame.repaint();
	}

	private void executeSelect(String lineLog, String[] splitLineLog, Command cmd) {
		
		Shape shape = makeShapeFromLog(lineLog, splitLineLog[1], false);
		for (Shape s : model.getShapes())
			if (shape.equals(s)) {
				shape = s;
			}
		cmd = new CmdSelectShape(model, shape);
		cmd.execute();
		
		frame.getTextArea().append("Select: " + shape.toString() + "\n");
		model.getUndo().add(cmd);
		
		controller.checkBtnState();

		// model.getRedo().clear();
		
		btnRedo.setBtnRedoAct(false);
		frame.repaint();
	}

	private void executeDeselect(String lineLog, String[] splitLineLog, Command cmd) {
		Shape shape = makeShapeFromLog(lineLog, splitLineLog[1], false);
		shape.setSelected(true);

		for (Shape s : model.getShapes())
			if (shape.equals(s)) {
				shape = s;
			}
		cmd = new CmdDeselectShape(model, shape);
		cmd.execute();

		frame.getTextArea().append("Deselect: " + shape.toString() + "\n");
		
		model.getUndo().add(cmd);
		
		controller.checkBtnState();
		// model.getRedo().clear();
		
		btnRedo.setBtnRedoAct(false);
		frame.repaint();
	}

}
