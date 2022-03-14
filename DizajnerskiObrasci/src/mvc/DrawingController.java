package mvc;

import java.awt.Color;
import java.awt.event.MouseEvent;


import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JOptionPane;

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

	public DrawingController(DrwingModel model, DrawingFrame frame) {
		this.model = model;
		this.frame = frame;
	}

	public void mouseClicked(MouseEvent e) {
		Shape newShape=null;
		int v1=0,v2=0;
		if (frame.getTglbtnSelection().isSelected()){
			selected=null;
			Iterator<Shape> it=model.getShapes().iterator();
			while (it.hasNext()){
				Shape shape = it.next();
				shape.setSelected(false);
				if(shape.contains(e.getX(), e.getY()))
					selected= shape;
			}
			if (selected!=null) {
				selected.setSelected(true);
				model.getSelected().add(selected);
			}

		} else if(frame.getTglbtnPoint().isSelected()){
				Point p = new Point(e.getX(), e.getY());
				p.setColor(Color.BLACK);
				newShape = p;
			} else if (frame.getTglbtnLine().isSelected()){
				if (startPoint==null) {
					startPoint = new Point (e.getX(), e.getY());
				}
				else {
					Line l = new Line(startPoint, new Point(e.getX(),e.getY()));
					l.setColor(Color.BLACK);
					newShape = l;
					startPoint=null;
				}
		} else if (frame.getTglbtnRectangle().isSelected()){
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
			r.setColor(dlg.getC());
			r.setInnerColor(dlg.getInnerC());
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
			c = new Circle(new Point(e.getX(),e.getY()), v1);
			c.setColor(dlg.getC());
			c.setInnerColor(dlg.getInnerC());
			try {
				newShape= c;
			} catch (Exception ex){
				JOptionPane.showMessageDialog(frame, "Wrong data type.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		} else if (frame.getTglbtnDonut().isSelected()){
			DlgDonut dlg= new DlgDonut();
			Donut d = new Donut();
			dlg.setModal(true);
			dlg.getTxtX().setText("" + e.getX());
			dlg.getTxtY().setText("" + e.getY());
			dlg.setVisible(true);
			if(dlg.isOK()){
				v1=Integer.parseInt(dlg.getTxtRadius().getText());
				v2=Integer.parseInt(dlg.getTxtInnerRadius().getText());
			}
			d = new Donut(new Point(e.getX(),e.getY()), v1,v2);
			d.setColor(dlg.getC());
			d.setInnerColor(dlg.getInnerC());
			try {
				newShape= d;
			} catch (Exception ex){
				JOptionPane.showMessageDialog(frame, "Wrong data type.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		} 
		if (newShape!=null)
			model.getShapes().add(newShape);
		frame.repaint();
	}

	protected void delete() {
		if (selected != null) {
			int selectedOption = JOptionPane.showConfirmDialog(null, "Are you sure?", "Warning message",
					JOptionPane.YES_NO_OPTION);
			if (selectedOption == JOptionPane.YES_OPTION) {
				model.getShapes().remove(selected);
			}
		} else {
			JOptionPane.showMessageDialog(null, "You have not selected any shape!","Error", JOptionPane.WARNING_MESSAGE);
		}
		this.selected=null;
		frame.repaint();
	}

	protected void modify(){
		int x=0,y=0;
		//Shape selected = model.getSelectedShape(0);
		if (selected != null) {
			if (selected instanceof Point) {
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
					p.setColor(dlg.getC());			
					model.getShapes().set(model.getShapes().indexOf(selected), p);	
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
					l.setColor(dlg.getC());
					model.getShapes().set(model.getShapes().indexOf(selected), l);
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
					model.getShapes().set(model.getShapes().indexOf(selected), r);
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
					model.getShapes().set(model.getShapes().indexOf(selected), d);
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
					model.getShapes().set(model.getShapes().indexOf(selected), c);
				}
			}
			frame.repaint();
			}
		else {
			JOptionPane.showMessageDialog(null, "You have not selected any shape!","Error", JOptionPane.WARNING_MESSAGE);
		}
		}
	
}
