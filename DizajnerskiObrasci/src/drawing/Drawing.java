package drawing;

import java.awt.event.MouseEvent;
import java.util.ListIterator;

import javax.swing.JOptionPane;

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
import gui.DlgCircle;
import gui.DlgDonut;
import gui.DlgLine;
import gui.DlgPoint;
import gui.DlgRectangle;
import mvc.DrawingController;

public class Drawing implements IDrawing {
	
	public void delete(DrawingController controler) {
		if (controler.getModel().getSelectedShapes().size() != 0) {
			int selectedOption = JOptionPane.showConfirmDialog(null, "Are you sure to delete shape/s?", "Warning message",
					JOptionPane.YES_NO_OPTION);
			if (selectedOption == JOptionPane.YES_OPTION) {
				controler.getFrame().getTxtAreaLog().append("Delete: " + controler.getModel().getSelectedShapes().toString() + "\n");
				ICommand command = new RemoveShapesCmd(controler.getModel().getSelectedShapes(), controler.getModel());
				command.execute();
				controler.pushHistoryForUndo(command);
			}
		} else {
			JOptionPane.showMessageDialog(null, "You did not select any shape!","Error", JOptionPane.WARNING_MESSAGE);
		}
		controler.positionOfObject();
		controler.getHistoryForRedo().clear();
		controler.getFrame().getBtnRedo().setEnabled(false);
		controler.getFrame().repaint();
	}
	
	public void modify(DrawingController controler) {
		if (!controler.getModel().getSelectedShapes().isEmpty()) {
			Shape selected = controler.getModel().getSelectedShapes().get(0);
			this.modifySelected(controler, selected);
		} else {
			JOptionPane.showMessageDialog(null, "You did not select any shape!","Error", JOptionPane.WARNING_MESSAGE);
		}
		controler.getFrame().repaint();
	}
	
	public void modifySelected(DrawingController controler, Shape selected) {
		ICommand command;
		
		if (selected instanceof Point) {
			Point point = (Point) selected;
			DlgPoint dlgPoint = new DlgPoint();
			dlgPoint.getTxtX().setText("" + point.getX());
			dlgPoint.getTxtY().setText("" + point.getY());
			dlgPoint.setPicked(point.getColor());
			dlgPoint.setModal(true);
			dlgPoint.setTitle("Modify Point");
			dlgPoint.setVisible(true);
			if (dlgPoint.isOK()) {
				point = new Point(Integer.parseInt(dlgPoint.getTxtX().getText()), Integer.parseInt(dlgPoint.getTxtY().getText()));
				if(dlgPoint.isColorChosen()){
					point.setColor(dlgPoint.getC());
				}else{
					point.setColor(dlgPoint.getPicked());
				}
				controler.getFrame().getTxtAreaLog().append("Modify: " + ((Point)selected).toString() + " To: " + point.toString() + "\n");
				command = new UpdatePointCmd((Point)selected,point);
				command.execute();
				controler.pushHistoryForUndo(command);
			}
		} else if(selected instanceof Line){
			Line line= (Line)selected;
			DlgLine dlgLine=new DlgLine();
			dlgLine.getTxtXs().setText("" + line.getStartPoint().getX());
			dlgLine.getTxtYs().setText("" + line.getStartPoint().getY());
			dlgLine.getTxtXe().setText("" + line.getEndPoint().getX());
			dlgLine.getTxtYe().setText("" + line.getEndPoint().getY());
			dlgLine.setPicked(line.getColor());
			dlgLine.setModal(true);

			dlgLine.setTitle("Modify Line");
			dlgLine.setVisible(true);
			
			if(dlgLine.isOK()){
				Point startPoint = new Point(Integer.parseInt(dlgLine.getTxtXs().getText()), Integer.parseInt(dlgLine.getTxtYs().getText()));
				Point endPoint= new Point(Integer.parseInt(dlgLine.getTxtXe().getText()),Integer.parseInt(dlgLine.getTxtYe().getText()));
				line=new Line(startPoint,endPoint);
				if(dlgLine.isColorChosen()){
					line.setColor(dlgLine.getColor());
				}else{
					line.setColor(dlgLine.getPicked());
				}
				controler.getFrame().getTxtAreaLog().append("Modify: " + ((Line)selected).toString() + " To: " + line.toString() + "\n");
				command = new UpdateLineCmd((Line)selected,line);
				command.execute();
				controler.pushHistoryForUndo(command);
			}
		} else if (selected instanceof Rectangle){
			Rectangle rectangle= (Rectangle) selected;
			DlgRectangle dlgRec =new DlgRectangle();
			dlgRec.getTxtX().setText("" + rectangle.getUpperLeft().getX());
			dlgRec.getTxtY().setText("" + rectangle.getUpperLeft().getY());
			dlgRec.getTxtHeight().setText(""+ rectangle.getHeight());
			dlgRec.getTxtWidth().setText(""+rectangle.getWidth());
			dlgRec.setPicked(rectangle.getColor());
			dlgRec.setInnerPc(rectangle.getInnerColor());
			dlgRec.setModal(true);
			dlgRec.setTitle("Modify Rectangle");
			dlgRec.setVisible(true);
			
			if(dlgRec.isOK()){
				Point point=new Point(Integer.parseInt(dlgRec.getTxtX().getText()),Integer.parseInt(dlgRec.getTxtY().getText()));
				rectangle=new Rectangle(point,Integer.parseInt(dlgRec.getTxtHeight().getText()),Integer.parseInt(dlgRec.getTxtWidth().getText()));
				if(dlgRec.isColorChosen()){
					rectangle.setColor(dlgRec.getColor());
				}else{
					rectangle.setColor(dlgRec.getPicked());
				}
				if(dlgRec.isInnerColorChosen()){
					rectangle.setInnerColor(dlgRec.getInnerC());
				}else{
					rectangle.setInnerColor(dlgRec.getInnerPc());
				}
				controler.getFrame().getTxtAreaLog().append("Modify: " + ((Rectangle)selected).toString() + " To: " + rectangle.toString() + "\n");
				command = new UpdateRectangleCmd((Rectangle)selected,rectangle);
				command.execute(); 
				controler.pushHistoryForUndo(command);
			}
		}else if(selected instanceof Donut){
			Donut donut= (Donut) selected;
			DlgDonut dlgDonut = new DlgDonut();
			dlgDonut.getTxtX().setText("" + donut.getCenter().getX());
			dlgDonut.getTxtY().setText("" + donut.getCenter().getY());
			dlgDonut.getTxtRadius().setText("" + donut.getRadius());
			dlgDonut.getTxtInnerRadius().setText("" + donut.getInnerRadius());
			dlgDonut.setPc(donut.getColor());
			dlgDonut.setInnerPc(donut.getInnerColor());
			dlgDonut.setModal(true);
			dlgDonut.setTitle("Modify Donut");
			dlgDonut.setVisible(true);
			
			
			if(dlgDonut.isOK()){
				Point point=new Point(Integer.parseInt(dlgDonut.getTxtX().getText()),Integer.parseInt(dlgDonut.getTxtY().getText()));
				donut=new Donut(point,Integer.parseInt(dlgDonut.getTxtRadius().getText()),Integer.parseInt(dlgDonut.getTxtInnerRadius().getText()));
				if(dlgDonut.isColorChosen()){
					donut.setColor(dlgDonut.getC());
				}else{
					donut.setColor(dlgDonut.getPc());
				}
				if(dlgDonut.isInnerColorChosen()){
					donut.setInnerColor(dlgDonut.getInnerC());
				}else{
					donut.setInnerColor(dlgDonut.getInnerPc());
				}
				controler.getFrame().getTxtAreaLog().append("Modify: " + ((Donut)selected).toString() + " To: " + donut.toString() + "\n");
				command = new UpdateDonutCmd((Donut)selected,donut);
				command.execute(); 
				controler.pushHistoryForUndo(command);
			}
		} else if(selected instanceof Circle){
			Circle circle= (Circle) selected;
			DlgCircle dlgCircle= new DlgCircle();
			dlgCircle.getTxtX().setText("" + circle.getCenter().getX());
			dlgCircle.getTxtY().setText("" + circle.getCenter().getY());
			dlgCircle.getTxtRadius().setText("" + circle.getRadius());
			dlgCircle.setPc(circle.getColor());
			dlgCircle.setInnerPc(circle.getInnerColor());
			dlgCircle.setModal(true);
			dlgCircle.setTitle("Modify Circle");
			dlgCircle.setVisible(true);
			
			if(dlgCircle.isOK()){
				Point point=new Point(Integer.parseInt(dlgCircle.getTxtX().getText()),Integer.parseInt(dlgCircle.getTxtY().getText()));
				circle=new Circle(point,Integer.parseInt(dlgCircle.getTxtRadius().getText()));
				if(dlgCircle.isColorChosen()){
					circle.setColor(dlgCircle.getC());
				}else{
					circle.setColor(dlgCircle.getPc());
				}
				if(dlgCircle.isInnerColorChosen()){
					circle.setInnerColor(dlgCircle.getInnerC());
				}else{
					circle.setInnerColor(dlgCircle.getInnerPc());
				}
				controler.getFrame().getTxtAreaLog().append("Modify: " + ((Circle)selected).toString() + " To: " + circle.toString() + "\n");
				command = new UpdateCircleCmd((Circle)selected,circle);
				command.execute(); 
				controler.pushHistoryForUndo(command);
			}
		} else if(selected instanceof HexagonAdapter){
			HexagonAdapter hex= (HexagonAdapter) selected;
			DlgCircle dlgHex= new DlgCircle();
			dlgHex.getTxtX().setText("" + hex.getHexagon().getX());
			dlgHex.getTxtY().setText("" + hex.getHexagon().getY());
			dlgHex.getTxtRadius().setText(""+hex.getHexagon().getR());
			dlgHex.setPc(hex.getHexagon().getBorderColor());
			dlgHex.setInnerPc(hex.getHexagon().getAreaColor());
			dlgHex.setModal(true);
			dlgHex.setTitle("Modify Hexagon");
			dlgHex.setVisible(true);
			
			
			if(dlgHex.isOK()){
				Point point=new Point(Integer.parseInt(dlgHex.getTxtX().getText()),Integer.parseInt(dlgHex.getTxtY().getText()));
				hex=new HexagonAdapter(point,Integer.parseInt(dlgHex.getTxtRadius().getText()));
				if(dlgHex.isColorChosen()){
					hex.getHexagon().setBorderColor(dlgHex.getC());
				}else{
					hex.getHexagon().setBorderColor(dlgHex.getPc());
				}
				if(dlgHex.isInnerColorChosen()){
					hex.getHexagon().setAreaColor(dlgHex.getInnerC());
				}else{
					hex.getHexagon().setAreaColor(dlgHex.getInnerPc());
				}
				hex.getHexagon().setSelected(true);
				controler.getFrame().getTxtAreaLog().append("Modify: " + ((HexagonAdapter)selected).toString() + " To: " + hex.toString() + "\n");
				command = new UpdateHexagonCmd((HexagonAdapter)selected,hex);
				command.execute(); 
				controler.pushHistoryForUndo(command);
			}
		}
	}
	
	public void mouseClickSelected(DrawingController controler, MouseEvent e, Shape newShape) {
		if (controler.getFrame().getTglbtnSelection().isSelected()){
			ListIterator<Shape> it=controler.getModel().getShapes().listIterator(controler.getModel().getShapes().size());
			while (it.hasPrevious()){
				Shape shape = it.previous();
				if(shape.contains(e.getX(), e.getY())) {
					if (!shape.isSelected()) {
						ICommand command = new SelectShapesCmd(shape, controler.getModel());
						command.execute();
						controler.getHistoryForUndo().add(command);
						controler.getFrame().getTxtAreaLog().append("Select: " + shape.toString() + "\n");
					} else if (shape.isSelected()) {
						ICommand command = new UnselectShapesCmd(shape, controler.getModel());
						command.execute();
						controler.getHistoryForUndo().add(command);
						controler.getFrame().getTxtAreaLog().append("Unselect: " + shape.toString() + "\n");
					}
					break;
				}
			}
		} else if (controler.getFrame().getTglbtnPoint().isSelected()){
				Point point = new Point(e.getX(), e.getY());
				point.setColor(controler.getFrame().getBtnColor().getBackground());
				newShape = point;
		} else if (controler.getFrame().getTglbtnLine().isSelected()){
			if (controler.getStartPoint()==null) {
				controler.setStartPoint(new Point (e.getX(), e.getY()));
			} else {
				Line line = new Line(controler.getStartPoint(), new Point(e.getX(),e.getY()));
				line.setColor(controler.getFrame().getBtnColor().getBackground());
				newShape = line;
				controler.setStartPoint(null);
			}
		} else if (controler.getFrame().getTglbtnRectangle().isSelected()){
			DlgRectangle dlgRec = new DlgRectangle();
			Rectangle rec= new Rectangle();
			dlgRec.setModal(true);
			dlgRec.setTitle("Rectangle");
			dlgRec.getTxtX().setText("" + e.getX());
			dlgRec.getTxtY().setText("" + e.getY());
			dlgRec.setVisible(true);
			if(dlgRec.isOK()){
				rec = new Rectangle(new Point(e.getX(),e.getY()), Integer.parseInt(dlgRec.getTxtHeight().getText()), Integer.parseInt(dlgRec.getTxtWidth().getText()));
				if(dlgRec.isColorChosen())
					rec.setColor(dlgRec.getColor());
				else
					rec.setColor(controler.getFrame().getBtnColor().getBackground());
				if(dlgRec.isInnerColorChosen())
					rec.setInnerColor(dlgRec.getInnerC());
				else
					rec.setInnerColor(controler.getFrame().getBtnInnerColor().getBackground());
				try {
					newShape = rec;
				} catch (Exception ex){
					JOptionPane.showMessageDialog(controler.getFrame(), "Wrong data type", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
			
		} else if (controler.getFrame().getTglbtnCircle().isSelected()){
			DlgCircle dlgCircle= new DlgCircle();
			Circle circle= new Circle();
			dlgCircle.setModal(true);
			dlgCircle.setTitle("Circle");
			dlgCircle.getTxtX().setText("" + e.getX());
			dlgCircle.getTxtY().setText("" + e.getY());
			dlgCircle.setVisible(true);
			if(dlgCircle.isOK()){
				circle = new Circle(new Point(e.getX(),e.getY()), Integer.parseInt(dlgCircle.getTxtRadius().getText()));
				if(dlgCircle.isColorChosen())
					circle.setColor(dlgCircle.getC());
				else
					circle.setColor(controler.getFrame().getBtnColor().getBackground());
				if(dlgCircle.isInnerColorChosen())
					circle.setInnerColor(dlgCircle.getInnerC());
				else
					circle.setInnerColor(controler.getFrame().getBtnInnerColor().getBackground());
				try {
					newShape= circle;
				} catch (Exception ex){
					JOptionPane.showMessageDialog(controler.getFrame(), "Wrong data type", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
			
		} else if (controler.getFrame().getTglbtnDonut().isSelected()){
			DlgDonut dlgDonut= new DlgDonut();
			Donut donut = new Donut();
			dlgDonut.setModal(true);
			dlgDonut.getTxtX().setText("" + e.getX());
			dlgDonut.getTxtY().setText("" + e.getY());
			dlgDonut.setVisible(true);
			dlgDonut.setTitle("Donut");
			if(dlgDonut.isOK()){
				donut = new Donut(new Point(e.getX(),e.getY()), Integer.parseInt(dlgDonut.getTxtRadius().getText()),Integer.parseInt(dlgDonut.getTxtInnerRadius().getText()));
				if(dlgDonut.isColorChosen())
					donut.setColor(dlgDonut.getC());
				else
					donut.setColor(controler.getFrame().getBtnColor().getBackground());
				if(dlgDonut.isInnerColorChosen())
					donut.setInnerColor(dlgDonut.getInnerC());
				else
					donut.setInnerColor(controler.getFrame().getBtnInnerColor().getBackground());
				try {
					newShape= donut;
				} catch (Exception ex){
					JOptionPane.showMessageDialog(controler.getFrame(), "Wrong data type.", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
			
		} else if (controler.getFrame().getTglbtnHexagon().isSelected()) {
			DlgCircle dlgHex= new DlgCircle();
			HexagonAdapter hex= new HexagonAdapter();
			dlgHex.setModal(true);
			dlgHex.setTitle("Hexagon");
			dlgHex.getTxtX().setText("" + e.getX());
			dlgHex.getTxtY().setText("" + e.getY());
			dlgHex.setVisible(true);
			if(dlgHex.isOK()){
				hex = new HexagonAdapter(new Point(e.getX(),e.getY()),Integer.parseInt(dlgHex.getTxtRadius().getText()));
				if(dlgHex.isColorChosen())
					hex.getHexagon().setBorderColor(dlgHex.getC());
				else
					hex.getHexagon().setBorderColor(controler.getFrame().getBtnColor().getBackground());
				if(dlgHex.isInnerColorChosen())
					hex.getHexagon().setAreaColor(dlgHex.getInnerC());
				else
					hex.getHexagon().setAreaColor(controler.getFrame().getBtnInnerColor().getBackground());
				try {
					newShape= hex;
				} catch (Exception ex){
					JOptionPane.showMessageDialog(controler.getFrame(), "Wrong data type.", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
			
		}
		
		if (newShape != null) {
			ICommand command = new AddShapeCmd(newShape, controler.getModel());
			command.execute();
			controler.getHistoryForUndo().add(command);
			controler.getFrame().getTxtAreaLog().append("Add: " + newShape.toString() + "\n");
		}
	}
}
