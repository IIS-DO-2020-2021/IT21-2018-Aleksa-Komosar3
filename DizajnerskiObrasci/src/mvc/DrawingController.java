package mvc;

import java.awt.Color;

import java.awt.event.MouseEvent;


import java.util.Iterator;
import java.util.ListIterator;
import java.util.Stack;

import javax.swing.JOptionPane;

import adapter.HexagonAdapter;
import command.AddShapeCmd;
import command.BringToBackCmd;
import command.BringToFrontCmd;
import command.Command;
import command.RemoveShapesCmd;
import command.SelectShapesCmd;
import command.ToBackCmd;
import command.ToFrontCmd;
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
import gui.DlgCircle;
import gui.DlgDonut;
import gui.DlgLine;
import gui.DlgPoint;
import gui.DlgRectangle;

public class DrawingController {
	
	private DrwingModel model;
	private DrawingFrame frame;

	//nema kreiranj objr
	private Shape selected;
	private Point startPoint;
	
	private Stack<Command> cmdHistory = new Stack<Command>();
	private Stack<Command> cmdUndoHistory = new Stack<Command>();

	public DrawingController(DrwingModel model, DrawingFrame frame) {
		this.model = model;
		this.frame = frame;
	}

	public void mouseClicked(MouseEvent e) {
		Shape newShape=null;
		int v1=0,v2=0;
		if (frame.getTglbtnSelection().isSelected()){
			Iterator<Shape> it=model.getShapes().iterator();
			while (it.hasNext()){
				Shape shape = it.next();
				if(shape.contains(e.getX(), e.getY())) {
					if (!shape.isSelected()) {
						Command cmd = new SelectShapesCmd(shape, model);
						cmd.execute();
						model.pushCmdHistory(cmd);
					} else if (shape.isSelected()) {
						Command cmd = new UnselectShapesCmd(shape, model);
						cmd.execute();
						model.pushCmdHistory(cmd);
					}	
				}
			}
		}else if(frame.getTglbtnPoint().isSelected()){
				Point p = new Point(e.getX(), e.getY());
				p.setColor(frame.getBtnColor().getBackground());
				newShape = p;
			} /*else if (frame.getTglbtnLine().isSelected()){
				if (startPoint==null) {
					startPoint = new Point (e.getX(), e.getY());
				}
				else {
					Line l = new Line(startPoint, new Point(e.getX(),e.getY()));
					l.setColor(Color.BLACK);
					newShape = l;
					startPoint=null;
				}
		}*/else if (frame.getTglbtnLine().isSelected()){
			if (startPoint==null) {
				startPoint = new Point (e.getX(), e.getY());
			} else {
				Line l = new Line(startPoint, new Point(e.getX(),e.getY()));
				l.setColor(frame.getBtnColor().getBackground());
				newShape = l;
				startPoint=null;
			}
		}
			else if (frame.getTglbtnRectangle().isSelected()){
			DlgRectangle dlg = new DlgRectangle();
			Rectangle r= new Rectangle();
			dlg.setModal(true);
			dlg.getTxtX().setText("" + e.getX());
			dlg.getTxtY().setText("" + e.getY());
			dlg.setVisible(true);
			if(dlg.isOK()){
				v1=Integer.parseInt(dlg.getTxtHeight().getText());
				v2=Integer.parseInt(dlg.getTxtWidth().getText());
			}
			r = new Rectangle(new Point(e.getX(),e.getY()), v1, v2);
			if(dlg.isColorChosen())
				r.setColor(dlg.getC());
			else
				r.setColor(frame.getBtnColor().getBackground());
			if(dlg.isInnerColorChosen())
				r.setInnerColor(dlg.getInnerC());
			else
				r.setInnerColor(frame.getBtnInnerColor().getBackground());
			try {
				newShape = r;
			} catch (Exception ex){
				JOptionPane.showMessageDialog(frame, "Wrong data type.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		} else if (frame.getTglbtnCircle().isSelected()){
			DlgCircle dlg= new DlgCircle();
			Circle c= new Circle();
			dlg.setModal(true);
			dlg.getTxtX().setText("" + e.getX());
			dlg.getTxtY().setText("" + e.getY());
			dlg.setVisible(true);
			if(dlg.isOK()){
				v1=Integer.parseInt(dlg.getTxtRadius().getText());
			}
			//ovo je za onu boju treba uzei od prvog
			c = new Circle(new Point(e.getX(),e.getY()), v1);
			if(dlg.isColorChosen())
				c.setColor(dlg.getC());
			else
				c.setColor(frame.getBtnColor().getBackground());
			if(dlg.isInnerColorChosen())
				c.setInnerColor(dlg.getInnerC());
			else
				c.setInnerColor(frame.getBtnInnerColor().getBackground());
			try {
				newShape= c;
			} catch (Exception ex){
				JOptionPane.showMessageDialog(frame, "Wrong data type.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		} else if (frame.getTglbtnDonut().isSelected()){
			DlgDonut dlg= new DlgDonut();
			Donut don = new Donut();
			dlg.setModal(true);
			dlg.getTxtX().setText("" + e.getX());
			dlg.getTxtY().setText("" + e.getY());
			dlg.setVisible(true);
			if(dlg.isOK()){
				v1=Integer.parseInt(dlg.getTxtRadius().getText());
				v2=Integer.parseInt(dlg.getTxtInnerRadius().getText());
			}
			don = new Donut(new Point(e.getX(),e.getY()), v1,v2);
			if(dlg.isColorChosen())
				don.setColor(dlg.getC());
			else
				don.setColor(frame.getBtnColor().getBackground());
			if(dlg.isInnerColorChosen())
				don.setInnerColor(dlg.getInnerC());
			else
				don.setInnerColor(frame.getBtnInnerColor().getBackground());
			try {
				newShape= don;
			} catch (Exception ex){
				JOptionPane.showMessageDialog(frame, "Wrong data type.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		} else if (frame.getTglbtnHexagon().isSelected()) {
			DlgCircle dlg= new DlgCircle();
			HexagonAdapter hex= new HexagonAdapter();
			dlg.setModal(true);
			dlg.getTxtX().setText("" + e.getX());
			dlg.getTxtY().setText("" + e.getY());
			dlg.setVisible(true);
			if(dlg.isOK()){
				v1=Integer.parseInt(dlg.getTxtRadius().getText());
			}
			hex = new HexagonAdapter(new Point(e.getX(),e.getY()), v1);
			if(dlg.isColorChosen())
				hex.getHexagon().setBorderColor(dlg.getC());
			else
				hex.getHexagon().setBorderColor(frame.getBtnColor().getBackground());
			if(dlg.isInnerColorChosen())
				hex.getHexagon().setAreaColor(dlg.getInnerC());
			else
				hex.getHexagon().setAreaColor(frame.getBtnInnerColor().getBackground());
			try {
				newShape= hex;
			} catch (Exception ex){
				JOptionPane.showMessageDialog(frame, "Wrong data type.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		if (newShape!=null){
			Command cmd = new AddShapeCmd(newShape, model);
			cmd.execute();
			model.pushCmdHistory(cmd);
			//newShape=null;
			}
		if(!model.getCmdHistory().isEmpty()) {
			frame.getBtnUndo().setEnabled(true);
		}
		if(model.getSelectedShapes().size()==1 && model.getShapes().size()>1) {
			checkPosition();
		} else {
			frame.getBtnToFront().setEnabled(false);
			frame.getBtnToBack().setEnabled(false);
			frame.getBtnBringToFront().setEnabled(false);
			frame.getBtnBringToBack().setEnabled(false);
		}
		
		disableRedo();
			
			
		frame.repaint();
	}

	protected void delete() {
		if (model.getSelectedShapes().size() != 0) {
			int selectedOption = JOptionPane.showConfirmDialog(null, "Are you sure to delete?", "Warning message",
					JOptionPane.YES_NO_OPTION);
			if (selectedOption == JOptionPane.YES_OPTION) {
				Command cmd = new RemoveShapesCmd(model.getSelectedShapes(), model);
				cmd.execute();
				model.pushCmdHistory(cmd);
			}
		} else {
			JOptionPane.showMessageDialog(null, "You have not selected any shape!","Error", JOptionPane.WARNING_MESSAGE);
		}
		//this.selected=null;
		disableRedo();
		frame.repaint();
	}

	protected void modify(){
		int x=0,y=0;
		Command cmd;
		if (!model.getSelectedShapes().isEmpty()) {
			Shape selected = model.getSelectedShapes().get(0);
			if (selected instanceof Point) {
				//Shape selected = model.getSelectedShapes().get(0);
			
				Point p = (Point) selected;
				DlgPoint dlg = new DlgPoint();
				dlg.getTxtX().setText("" + p.getX());
				dlg.getTxtY().setText("" + p.getY());
				dlg.setPc(p.getColor());
				dlg.setModal(true);
				dlg.setVisible(true);
				if (dlg.isOK()) {
					x=Integer.parseInt(dlg.getTxtX().getText());
					y=Integer.parseInt(dlg.getTxtY().getText());
					p = new Point(x, y);
					if(dlg.isColorChosen()){
						p.setColor(dlg.getC());
					}else{
						p.setColor(dlg.getPc());
					}		
					//model.getShapes().set(model.getShapes().indexOf(selected), p);
					cmd = new UpdatePointCmd((Point)selected,p);
					cmd.execute();
					
					model.pushCmdHistory(cmd);
				}
			} else if(selected instanceof Line){
				Line l= (Line)selected;
				DlgLine dlg=new DlgLine();
				dlg.getTxtXs().setText("" + l.getStartPoint().getX());
				dlg.getTxtYs().setText("" + l.getStartPoint().getY());
				dlg.getTxtXe().setText("" + l.getEndPoint().getX());
				dlg.getTxtYe().setText("" + l.getEndPoint().getY());
				dlg.setPc(l.getColor());
				dlg.setModal(true);
				dlg.setVisible(true);
				if(dlg.isOK()){
					x=Integer.parseInt(dlg.getTxtXs().getText());
					y=Integer.parseInt(dlg.getTxtYs().getText());
					int xe=Integer.parseInt(dlg.getTxtXe().getText());
					int ye=Integer.parseInt(dlg.getTxtYe().getText());
					Point p1 = new Point(x, y);
					Point p2= new Point(xe,ye);
					l=new Line(p1,p2);
					if(dlg.isColorChosen()){
						l.setColor(dlg.getC());
					}else{
						l.setColor(dlg.getPc());
					}
					cmd = new UpdateLineCmd((Line)selected,l);
					cmd.execute();
					//model.getShapes().set(model.getShapes().indexOf(selected), l);
					
					model.pushCmdHistory(cmd);
				}
			} else if (selected instanceof Rectangle){
				Rectangle r= (Rectangle) selected;
				DlgRectangle dlg=new DlgRectangle();
				dlg.getTxtX().setText("" + r.getUpperLeft().getX());
				dlg.getTxtY().setText("" + r.getUpperLeft().getY());
				dlg.getTxtHeight().setText(""+r.getHeight());
				dlg.getTxtWidth().setText(""+r.getWidth());
				dlg.setPc(r.getColor());
				dlg.setInnerPc(r.getInnerColor());
				dlg.setModal(true);
				dlg.setVisible(true);
				if(dlg.isOK()){
					x=Integer.parseInt(dlg.getTxtX().getText());
					y=Integer.parseInt(dlg.getTxtY().getText());
					int h=Integer.parseInt(dlg.getTxtHeight().getText());
					int w=Integer.parseInt(dlg.getTxtWidth().getText());
					Point p=new Point(x,y);
					r=new Rectangle(p,h,w);
					if(dlg.isColorChosen()){
						r.setColor(dlg.getC());
					}else{
						r.setColor(dlg.getPc());
					}
					if(dlg.isInnerColorChosen()){
						r.setInnerColor(dlg.getInnerC());
					}else{
						r.setInnerColor(dlg.getInnerPc());
					}
					cmd = new UpdateRectangleCmd((Rectangle)selected,r);
					cmd.execute();
					
					model.pushCmdHistory(cmd);
					//model.getShapes().set(model.getShapes().indexOf(selected), r);
				}
			}else if(selected instanceof Donut){
				Donut d= (Donut) selected;
				DlgDonut dlg = new DlgDonut();
				dlg.getTxtX().setText("" + d.getCenter().getX());
				dlg.getTxtY().setText("" + d.getCenter().getY());
				dlg.getTxtRadius().setText(""+d.getRadius());
				dlg.getTxtInnerRadius().setText(""+d.getInnerRadius());
				dlg.setPc(d.getColor());
				dlg.setInnerPc(d.getInnerColor());
				dlg.setModal(true);
				dlg.setVisible(true);
				if(dlg.isOK()){
					x=Integer.parseInt(dlg.getTxtX().getText());
					y=Integer.parseInt(dlg.getTxtY().getText());
					int r=Integer.parseInt(dlg.getTxtRadius().getText());
					int iR=Integer.parseInt(dlg.getTxtInnerRadius().getText());
					Point p=new Point(x,y);
					d=new Donut(p,r,iR);
					if(dlg.isColorChosen()){
						d.setColor(dlg.getC());
					}else{
						d.setColor(dlg.getPc());
					}
					if(dlg.isInnerColorChosen()){
						d.setInnerColor(dlg.getInnerC());
					}else{
						d.setInnerColor(dlg.getInnerPc());
					}
					cmd = new UpdateDonutCmd((Donut)selected,d);
					cmd.execute();
					
					model.pushCmdHistory(cmd);
					//model.getShapes().set(model.getShapes().indexOf(selected), d);
				}
			} else if(selected instanceof Circle){
				Circle c= (Circle) selected;
				DlgCircle dlg= new DlgCircle();
				dlg.getTxtX().setText("" + c.getCenter().getX());
				dlg.getTxtY().setText("" + c.getCenter().getY());
				dlg.getTxtRadius().setText(""+c.getRadius());
				dlg.setPc(c.getColor());
				dlg.setInnerPc(c.getInnerColor());
				dlg.setModal(true);
				dlg.setVisible(true);
				if(dlg.isOK()){
					x=Integer.parseInt(dlg.getTxtX().getText());
					y=Integer.parseInt(dlg.getTxtY().getText());
					int r=Integer.parseInt(dlg.getTxtRadius().getText());
					Point p=new Point(x,y);
					c=new Circle(p,r);
					if(dlg.isColorChosen()){
						c.setColor(dlg.getC());
					}else{
						c.setColor(dlg.getPc());
					}
					if(dlg.isInnerColorChosen()){
						c.setInnerColor(dlg.getInnerC());
					}else{
						c.setInnerColor(dlg.getInnerPc());
					}
					cmd = new UpdateCircleCmd((Circle)selected,c);
					cmd.execute();
					
					model.pushCmdHistory(cmd);
					//model.getShapes().set(model.getShapes().indexOf(selected), c);
				}
			}else if(selected instanceof HexagonAdapter){
				HexagonAdapter hex= (HexagonAdapter) selected;
				DlgCircle dlg= new DlgCircle();
				dlg.getTxtX().setText("" + hex.getHexagon().getX());
				dlg.getTxtY().setText("" + hex.getHexagon().getY());
				dlg.getTxtRadius().setText(""+hex.getHexagon().getR());
				dlg.setPc(hex.getHexagon().getBorderColor());
				dlg.setInnerPc(hex.getHexagon().getAreaColor());
				dlg.setModal(true);
				dlg.setVisible(true);
				if(dlg.isOK()){
					x=Integer.parseInt(dlg.getTxtX().getText());
					y=Integer.parseInt(dlg.getTxtY().getText());
					int r=Integer.parseInt(dlg.getTxtRadius().getText());
					Point p=new Point(x,y);
					hex=new HexagonAdapter(p,r);
					if(dlg.isColorChosen()){
						hex.getHexagon().setBorderColor(dlg.getC());
					}else{
						hex.getHexagon().setBorderColor(dlg.getPc());
					}
					if(dlg.isInnerColorChosen()){
						hex.getHexagon().setAreaColor(dlg.getInnerC());
					}else{
						hex.getHexagon().setAreaColor(dlg.getInnerPc());
					}
					cmd = new UpdateHexagonCmd((HexagonAdapter)selected,hex);
					cmd.execute();
					
					model.pushCmdHistory(cmd);
				}
			}
			}
		
		else {
			JOptionPane.showMessageDialog(null, "You have not selected any shapes!","Error", JOptionPane.WARNING_MESSAGE);
		}
		disableRedo();
		frame.repaint();
		
	}
	
	public void undo() {
		if(!model.getCmdHistory().isEmpty()) {
			Command cmd = model.popCmdHistory();
			cmd.unexecute();
			checkPosition();
			model.pushCmdUndoHistory(cmd);
			frame.getBtnRedo().setEnabled(true);
			if(model.getCmdHistory().isEmpty()) {
				frame.getBtnUndo().setEnabled(false);
			}
		}
		frame.repaint();
	}

	public void redo() {
		if(!model.getCmdUndoHistory().isEmpty()) {
			Command cmd = model.popCmdUndoHistory();
			cmd.execute();
			checkPosition();
			model.pushCmdHistory(cmd);
			if(model.getCmdUndoHistory().isEmpty()) {
				frame.getBtnRedo().setEnabled(false);
			}
		}
		frame.getBtnUndo().setEnabled(true);
		frame.repaint();
	}

	public void disableRedo() {
		model.getCmdUndoHistory().clear();
		frame.getBtnRedo().setEnabled(false);
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
	
	/*CmdUndoHistory*/
	public Command popCmdUndoHistory() {
		return cmdUndoHistory.pop();
	}
	
	public void pushCmdUndoHistory(Command c) {
		cmdUndoHistory.add(c);
	}
	
	public Stack<Command> getCmdUndoHistory() {
		return cmdUndoHistory;
	}
	
	public void checkPosition() {
		if(model.getSelectedShapes().size()>0) {
			int i = model.getShapes().indexOf(model.getSelectedShapes().get(0));
			System.out.println(i+1);
			System.out.println(model.getShapes().size());
			if(i+1 == model.getShapes().size()) {
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
		Shape s = model.getSelectedShapes().get(0);
		Command cmd = new ToFrontCmd(s, model);
		cmd.execute();
		model.pushCmdHistory(cmd);
		checkPosition();
		disableRedo();
		frame.repaint();
	}

	public void toBack() {
		Shape s = model.getSelectedShapes().get(0);
		Command cmd = new ToBackCmd(s, model);
		cmd.execute();
		model.pushCmdHistory(cmd);
		checkPosition();
		disableRedo();
		frame.repaint();
	}

	public void bringToFront() {
		Shape s = model.getSelectedShapes().get(0);
		Command cmd = new BringToFrontCmd(s, model);
		cmd.execute();
		model.pushCmdHistory(cmd);
		checkPosition();
		disableRedo();
		frame.repaint();
	}

	public void bringToBack () {
		Shape s = model.getSelectedShapes().get(0);
		Command cmd = new BringToBackCmd(s, model);
		cmd.execute();
		model.pushCmdHistory(cmd);
		checkPosition();
		disableRedo();
		frame.repaint();
	}

	
}
