package mvc;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import command.CmdAddShape;
import command.CmdDeleteOneShape;
import command.CmdDeleteShape;
import command.CmdDeselectShape;
import command.CmdModifyCircle;
import command.CmdModifyDonut;
import command.CmdModifyHexagon;
import command.CmdModifyLine;
import command.CmdModifyPoint;
import command.CmdModifyRectangle;
import command.CmdSelectShape;
import command.Command;
import geometry.Circle;
import geometry.Donut;
import geometry.HexagonAdapter;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;
import gui.DlgCircle;
import gui.DlgDonut;
import gui.DlgHexagon;
import gui.DlgLine;
import gui.DlgPoint;
import gui.DlgRectangle;

public class DrawingController {
	private DrawingModel model;
	private DrawingFrame frame;
	private Shape selShape;
	private Point startPoint;
	private Command command;
	
	public DrawingModel getModel() {
		return model;
	}

	public void setModel(DrawingModel model) {
		this.model = model;
	}

	public DrawingFrame getFrame() {
		return frame;
	}

	public void setFrame(DrawingFrame frame) {
		this.frame = frame;
	}

	public Shape getSelected() {
		return selShape;
	}

	public void setSelected(Shape selected) {
		this.selShape = selected;
	}

	public Point getStartPoint() {
		return startPoint;
	}

	public void setStartPoint(Point startPoint) {
		this.startPoint = startPoint;
	}

	public DrawingController(DrawingModel model, DrawingFrame frame) {
		this.model = model;
		this.frame = frame;
	}

	public void mouseClicked(MouseEvent e) {
		command=null;
		Shape newShape=null;
		if (frame.getBtnSelect().isSelected() && model.getShapes()!=null){
			Iterator<Shape> iterate=model.getShapes().iterator();
			while (iterate.hasNext()){
				Shape shape = iterate.next();
			if(shape.contains(e.getX(), e.getY())) {
					selShape = shape;
			
			if (!selShape.isSelected()) {
				command=new CmdSelectShape(model, selShape );
				command.execute();
				model.getUndo().add(command);
			} else if (selShape.isSelected()){
				command=new CmdDeselectShape(model, selShape);
				command.execute();
				model.getUndo().add(command);
			}
			
			break;
			}
		}

		} else if(frame.getBtnPoint().isSelected()){
				Point point = new Point(e.getX(), e.getY());
				point.setColor(Color.BLACK);
				newShape = point;
			} else if (frame.getBtnLine().isSelected()){
				if (startPoint==null) {
					startPoint = new Point (e.getX(), e.getY());
				}
				else {
					Line line = new Line(startPoint, new Point(e.getX(),e.getY()));
					line.setColor(Color.BLACK);
					newShape = line;
					startPoint=null;
				}
		} else if (frame.getBtnRectangle().isSelected()){
			DlgRectangle dlgRectangle = new DlgRectangle();
			Rectangle rectangle= new Rectangle();
			dlgRectangle.setModal(true);
			dlgRectangle.getTxtX().setText("" + e.getX());
			dlgRectangle.getTxtY().setText("" + e.getY());
			dlgRectangle.setTitle("Rectangle");
			dlgRectangle.setVisible(true);
			
			
			if(dlgRectangle.isOK()){
				rectangle = new Rectangle(new Point(e.getX(),e.getY()), 
						Integer.parseInt(dlgRectangle.getTxtHeight().getText()), 
						Integer.parseInt(dlgRectangle.getTxtWidth().getText()));
				if(dlgRectangle.isColorChosen())
					rectangle.setColor(dlgRectangle.getColor());
					rectangle.setInnerColor(dlgRectangle.getInnerColor());
				try {
					newShape = rectangle;
				} catch (Exception ex){
					JOptionPane.showMessageDialog(frame, "Wrong data", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		} else if (frame.getBtnCircle().isSelected()){
			DlgCircle dlgCircle= new DlgCircle();
			Circle circle= new Circle();
			dlgCircle.setModal(true);
			dlgCircle.getTxtX().setText("" + e.getX());
			dlgCircle.getTxtY().setText("" + e.getY());
			dlgCircle.setTitle("Circle");
			dlgCircle.setVisible(true);
			
			
			if(dlgCircle.isOK()){
				circle = new Circle(new Point(e.getX(),e.getY()),
						Integer.parseInt(dlgCircle.getTxtRadius().getText()));
				circle.setColor(dlgCircle.getColor());
				circle.setInnerColor(dlgCircle.getInnerC());
				try {
					newShape= circle;
				} catch (Exception ex){
					JOptionPane.showMessageDialog(frame, "Wrong data", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		} else if (frame.getBtnDonut().isSelected()){
			DlgDonut dlgDonut= new DlgDonut();
			Donut donut = new Donut();
			dlgDonut.setModal(true);
			dlgDonut.getTxtX().setText("" + e.getX());
			dlgDonut.getTxtY().setText("" + e.getY());
			dlgDonut.setTitle("Donut");
			dlgDonut.setVisible(true);
			
			
			if(dlgDonut.isOK()){
				if(Integer.parseInt(dlgDonut.getTxtRadius().getText())==
						Integer.parseInt(dlgDonut.getTxtInnerRadius().getText())){
					JOptionPane.showMessageDialog(frame, "Radius and inner radius cannot be the same!",
							"Error", JOptionPane.ERROR_MESSAGE);
				}
				donut = new Donut(new Point(e.getX(),e.getY()),
						Integer.parseInt(dlgDonut.getTxtRadius().getText()),
						Integer.parseInt(dlgDonut.getTxtInnerRadius().getText()));
				donut.setColor(dlgDonut.getColor());
				donut.setInnerColor(dlgDonut.getInnerColor());
				try {
					newShape= donut;
				} catch (Exception ex){
					JOptionPane.showMessageDialog(frame, "Wrong data", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		} else if (frame.getBtnHexagon().isSelected()){
			DlgHexagon dlgHexagon= new DlgHexagon();
			HexagonAdapter hexagon = new HexagonAdapter();
			dlgHexagon.setModal(true);
			dlgHexagon.getTxtX().setText("" + e.getX());
			dlgHexagon.getTxtY().setText("" + e.getY());
			dlgHexagon.setTitle("Hexagon");
			dlgHexagon.setVisible(true);
			
			if(dlgHexagon.isOK()){
				try {
					hexagon = new HexagonAdapter(new Point(e.getX(),e.getY()), 
						Integer.parseInt(dlgHexagon.getTxtRadius().getText()));
					hexagon.setHexagonBorderColor(dlgHexagon.getColor());
					hexagon.setHexagonInnerColor(dlgHexagon.getInnerC());
					newShape = hexagon;
				} catch (Exception ex){
					JOptionPane.showMessageDialog(frame, "Wrong data", "Error",
							JOptionPane.ERROR_MESSAGE);
					}
			}
		}
		if (newShape!=null) {
			command=new CmdAddShape(model, newShape);
			command.execute();
			model.getUndo().add(command);
		}
		frame.repaint();
		selShape.setSelected(true);
	}

	protected void editShape(){
		command=null;
		if (selShape != null && model.getSelectedShapes().size()>0) {
			if (selShape instanceof Point) {
				Point point = (Point) selShape;
				DlgPoint dlgPoint = new DlgPoint();
				dlgPoint.getTxtX().setText("" + point.getX());
				dlgPoint.getTxtY().setText("" + point.getY());
				dlgPoint.setPicked(point.getColor());
				dlgPoint.setModal(true);
				dlgPoint.setTitle("Edit point");
				dlgPoint.setVisible(true);
			
				
				if (dlgPoint.isOK()) {
					point = new Point(Integer.parseInt(dlgPoint.getTxtX().getText()),
							Integer.parseInt(dlgPoint.getTxtY().getText()));
					point.setColor(dlgPoint.getColor());			
					command=new CmdModifyPoint((Point)selShape, point);
					command.execute();
					model.getUndo().add(command);
					
					selShape.setSelected(true);
				}
			} else if(selShape instanceof Line){
				Line line= (Line)selShape;
				DlgLine dlgLine = new DlgLine();
				dlgLine.getTxtXs().setText("" + line.getStartPoint().getX());
				dlgLine.getTxtYs().setText("" + line.getStartPoint().getY());
				dlgLine.getTxtXe().setText("" + line.getEndPoint().getX());
				dlgLine.getTxtYe().setText("" + line.getEndPoint().getY());
				dlgLine.setPicked(line.getColor());
				dlgLine.setModal(true);
				dlgLine.setTitle("Edit line");
				dlgLine.setVisible(true);
				
				
				if(dlgLine.isOK()){
					Point startPoint = new Point(Integer.parseInt(dlgLine.getTxtXs().getText()), 
							Integer.parseInt(dlgLine.getTxtYs().getText()));
					Point endPoint= new Point(Integer.parseInt(dlgLine.getTxtXe().getText()),
							Integer.parseInt(dlgLine.getTxtYe().getText()));
					line = new Line(startPoint,endPoint);
					line.setColor(dlgLine.getColor());
					command=new CmdModifyLine((Line)selShape, line);
					command.execute();
					model.getUndo().add(command);
					
					selShape.setSelected(true);
				}
			} else if (selShape instanceof Rectangle){
				Rectangle rectangle= (Rectangle) selShape;
				DlgRectangle dlgRec=new DlgRectangle();
				dlgRec.getTxtX().setText("" + rectangle.getUpperLeft().getX());
				dlgRec.getTxtY().setText("" + rectangle.getUpperLeft().getY());
				dlgRec.getTxtHeight().setText("" + rectangle.getHeight());
				dlgRec.getTxtWidth().setText("" + rectangle.getWidth());
				dlgRec.setPicked(rectangle.getColor());
				dlgRec.setInnerPc(rectangle.getInnerColor());
				dlgRec.setModal(true);
				dlgRec.setTitle("Edit rectangle");
				dlgRec.setVisible(true);
				
				
				if(dlgRec.isOK()){
					Point point = new Point(Integer.parseInt(dlgRec.getTxtX().getText()),
							Integer.parseInt(dlgRec.getTxtY().getText()));
					rectangle = new Rectangle(point,Integer.parseInt(dlgRec.getTxtHeight().getText()),
							Integer.parseInt(dlgRec.getTxtWidth().getText()));
					if(dlgRec.isColorChosen()){
						rectangle.setColor(dlgRec.getColor());
					} else {
						rectangle.setColor(dlgRec.getPicked());
					}
					if(dlgRec.isInnerColorChosen()){
						rectangle.setInnerColor(dlgRec.getInnerColor());
					} else {
						rectangle.setInnerColor(dlgRec.getInnerPc());
					}
					command=new CmdModifyRectangle((Rectangle)selShape, rectangle);
					command.execute();
					model.getUndo().add(command);
					
					selShape.setSelected(true);
				}
			} else if (selShape instanceof Donut){
				Donut donut= (Donut) selShape;
				DlgDonut dlgDonut = new DlgDonut();
				dlgDonut.getTxtX().setText("" + donut.getCenter().getX());
				dlgDonut.getTxtY().setText("" + donut.getCenter().getY());
				dlgDonut.getTxtRadius().setText("" + donut.getRadius());
				dlgDonut.getTxtInnerRadius().setText("" + donut.getInnerRadius());
				dlgDonut.setPc(donut.getColor());
				dlgDonut.setInnerPc(donut.getInnerColor());
				dlgDonut.setModal(true);
				dlgDonut.setTitle("Edit donut");
				dlgDonut.setVisible(true);
				
				
				if(dlgDonut.isOK()){
					Point point=new Point(Integer.parseInt(dlgDonut.getTxtX().getText()),
							Integer.parseInt(dlgDonut.getTxtY().getText()));
					donut=new Donut(point,Integer.parseInt(dlgDonut.getTxtRadius().getText()),
							Integer.parseInt(dlgDonut.getTxtInnerRadius().getText()));
					if(dlgDonut.isColorChosen()){
						donut.setColor(dlgDonut.getColor());
					} else {
						donut.setColor(dlgDonut.getPc());
					}
					if(dlgDonut.isInnerColorChosen()){
						donut.setInnerColor(dlgDonut.getInnerColor());
					}else{
						donut.setInnerColor(dlgDonut.getInnerPc());
					}
					command=new CmdModifyDonut((Donut)selShape, donut);
					command.execute();
					model.getUndo().add(command);
					
					selShape.setSelected(true);
				}
			}
			else if(selShape instanceof Circle){
				Circle circle= (Circle) selShape;
				DlgCircle dlgCircle= new DlgCircle();
				dlgCircle.getTxtX().setText("" + circle.getCenter().getX());
				dlgCircle.getTxtY().setText("" + circle.getCenter().getY());
				dlgCircle.getTxtRadius().setText("" + circle.getRadius());
				dlgCircle.setPc(circle.getColor());
				dlgCircle.setInnerPc(circle.getInnerColor());
				dlgCircle.setModal(true);
				dlgCircle.setTitle("Edit circle");
				dlgCircle.setVisible(true);
				
				if(dlgCircle.isOK()){
					Point point=new Point(Integer.parseInt(dlgCircle.getTxtX().getText()),
							Integer.parseInt(dlgCircle.getTxtY().getText()));
					circle=new Circle(point,Integer.parseInt(dlgCircle.getTxtRadius().getText()));
					if(dlgCircle.isColorChosen()){
						circle.setColor(dlgCircle.getColor());
					}else
					{
						circle.setColor(dlgCircle.getPc());
					}
					if(dlgCircle.isInnerColorChosen()){
						circle.setInnerColor(dlgCircle.getInnerC());
					} else 
					{
						circle.setInnerColor(dlgCircle.getInnerPc());
					}
					command=new CmdModifyCircle((Circle)selShape, circle);
					command.execute();
					model.getUndo().add(command);
				}
			} 	
			else if(selShape instanceof HexagonAdapter){
				HexagonAdapter hexagon= (HexagonAdapter) selShape;
				DlgHexagon dlgHexagon= new DlgHexagon();
				dlgHexagon.getTxtX().setText("" + hexagon.getHexagon().getX());
				dlgHexagon.getTxtY().setText("" + hexagon.getHexagon().getY());
				dlgHexagon.getTxtRadius().setText("" + hexagon.getHexagon().getR());
				dlgHexagon.setPc(hexagon.getHexagonBorderColor());
				dlgHexagon.setInnerPc(hexagon.getHexagonBorderColor());
				dlgHexagon.setModal(true);
				dlgHexagon.setTitle("Edit hexagon");
				dlgHexagon.setVisible(true);
				
				if(dlgHexagon.isOK()){
					Point point=new Point(Integer.parseInt(dlgHexagon.getTxtX().getText()),
							Integer.parseInt(dlgHexagon.getTxtY().getText()));
					hexagon=new HexagonAdapter(point, 
							Integer.parseInt(dlgHexagon.getTxtRadius().getText()));
					if(dlgHexagon.isColorChosen()){
						hexagon.setHexagonBorderColor(dlgHexagon.getColor());
					}else{
						hexagon.setHexagonBorderColor(dlgHexagon.getPc());
					}
					if(dlgHexagon.isInnerColorChosen()){
						hexagon.setHexagonInnerColor(dlgHexagon.getInnerC());
					} else
					{
						hexagon.setHexagonInnerColor(dlgHexagon.getInnerPc());
					}
					command=new CmdModifyHexagon((HexagonAdapter)selShape, hexagon);
					command.execute();
					model.getUndo().add(command);
					
					selShape.setSelected(true);
				}
			} 	
			frame.repaint();
			
			selShape.setSelected(true);
		}
		else {
			ImageIcon icon=new ImageIcon("C:/Users/EC/git/IT21-2018-Aleksa-Komosar3/DizajnerskiObrasci/images/er.png");
			JOptionPane.showMessageDialog(null, "You did not select any shape to edit!","Error",
					JOptionPane.WARNING_MESSAGE, icon);
		}
		frame.repaint();
	}
	
	protected void deleteShape() {
		command=null;
		if (selShape !=null && model.getSelectedShapes().size()==1) {
			int selectedOption = JOptionPane.showConfirmDialog(null, "Are you sure to delete?",
					"Warning message", JOptionPane.YES_NO_OPTION);
			if (selectedOption == JOptionPane.YES_OPTION) {
				command=new CmdDeleteOneShape(model, selShape);
				command.execute();
				model.getUndo().add(command);
			}
		} else if (model.getSelectedShapes().size()>1){
			int selectedOption = JOptionPane.showConfirmDialog(null, "Are you sure to delete?",
					"Warning message", JOptionPane.YES_NO_OPTION);
			if (selectedOption == JOptionPane.YES_OPTION) {
				command=new CmdDeleteShape(model.getSelectedShapes(), model);
				command.execute();
				model.getUndo().add(command);
			}
			frame.repaint();
		}
		else {
			ImageIcon icon=new ImageIcon("C:/Users/EC/git/IT21-2018-Aleksa-Komosar3/DizajnerskiObrasci/images/er.png");
			JOptionPane.showMessageDialog(null, "You did not select any shape!","Error",
					JOptionPane.WARNING_MESSAGE, icon);
		}
		frame.repaint();
		
		frame.getBtnSelect().setSelected(false);
	}
	
	public void undo(){
		if(model.getUndo().size()!=0){
		Command command = model.getUndo().pop();
		command.unexecute();
		model.getRedo().add(command);
		frame.repaint();
		}
		frame.repaint();
		frame.getBtnRedo().setEnabled(true);
			if(model.getUndo().size()==0){
				frame.getBtnUndo().setEnabled(false);
			}
	}
	
	public void redo(){
		if(model.getRedo().size()!=0){
		Command command = model.getRedo().pop();
		command.execute();
		model.getUndo().add(command);
		frame.repaint();
		}
		frame.repaint();
		frame.getBtnUndo().setEnabled(true);
			if(model.getRedo().size()==0){
				frame.getBtnRedo().setEnabled(false);
			}
	}
}
