package mvc;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import command.CmdAddShape;
import command.CmdBringToBack;
import command.CmdBringToFront;
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
import gui.DlgCircle;
import gui.DlgDonut;
import gui.DlgHexagon;
import gui.DlgLine;
import gui.DlgPoint;
import gui.DlgRectangle;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import observer.BtnUpdate;
import observer.BtnUpdateObserver;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter @ToString
public class DrawingController {
	
	private DrawingModel model;
	private DrawingFrame frame;
	private Shape selShape;
	private Point startPoint;
	private Command command;
	private BtnUpdateObserver btnUpdateObserver;
	private BtnUpdate btnUpdate=new BtnUpdate();

	public DrawingController(DrawingModel model, DrawingFrame frame) {
		this.model = model;
		this.frame = frame;
		btnUpdateObserver= new BtnUpdateObserver(frame);
		btnUpdate.addPropertyChangeListener(btnUpdateObserver);
		
	}
	
	public void btnsUndoRedo(){
		if (model.getUndo().size()==0 || model.getUndo().isEmpty()){
			btnUpdate.setBtnUndoAct(false);
		} else {
			btnUpdate.setBtnUndoAct(true);
		}
		if(model.getRedo().size()==0 || model.getRedo().isEmpty()) {
			btnUpdate.setBtnRedoAct(false);
		} else {
			btnUpdate.setBtnRedoAct(true);
		}
		frame.repaint();
	}
	
	public void checkBtnState() {
		if (model.getShapes().size() != 0) {
			btnUpdate.setBtnSelectAct(true);
			if (model.getSelectedShapes().size() > 0) {
				if (model.getSelectedShapes().size() == 1) {
					btnUpdate.setBtnModificationAct(true);
					btnUpdate.setBtnDeleteAct(true);
					btnsUpdate();
				} else {
					btnUpdate.setBtnModificationAct(false);
					btnUpdate.setBtnToBackAct(false);
					btnUpdate.setBtnToFrontAct(false);
					btnUpdate.setBtnBringFullFrontAct(false);
					btnUpdate.setBtnBringFullBackAct(false);
					btnUpdate.setBtnDeleteAct(true);
				}
			}else{
				btnUpdate.setBtnDeleteAct(false);
				btnUpdate.setBtnModificationAct(false);
				btnUpdate.setBtnBringFullBackAct(false);
				btnUpdate.setBtnBringFullFrontAct(false);
				btnUpdate.setBtnToBackAct(false);
				btnUpdate.setBtnToFrontAct(false);
			}
		}else{
			btnUpdate.setBtnModificationAct(false);
			btnUpdate.setBtnDeleteAct(false);
			btnUpdate.setBtnSelectAct(false);
			btnUpdate.setBtnBringFullBackAct(false);
			btnUpdate.setBtnBringFullFrontAct(false);
			btnUpdate.setBtnToBackAct(false);
			btnUpdate.setBtnToFrontAct(false);
		}
		//frame.repaint();
	}

	public void btnsUpdate() {
		int i=0;
		if(model.getSelectedShapes().size() == 1) {
				i = model.getShapes().indexOf(model.getSelectedShapes().get(0));
			if (model.getShapes().size() == 1) {
				btnUpdate.setBtnBringFullBackAct(false);
				btnUpdate.setBtnBringFullFrontAct(false);
				btnUpdate.setBtnToBackAct(false);
				btnUpdate.setBtnToFrontAct(false);
			} else if (i == 0) {
				btnUpdate.setBtnToBackAct(false);
				btnUpdate.setBtnBringFullBackAct(false);
				btnUpdate.setBtnBringFullFrontAct(true);
				btnUpdate.setBtnToFrontAct(true);
			} else if(model.getShapes().size() == i + 1) {
					btnUpdate.setBtnToFrontAct(false);
					btnUpdate.setBtnBringFullFrontAct(false);
					btnUpdate.setBtnToBackAct(true);
					btnUpdate.setBtnBringFullBackAct(true);
			} else if (model.getShapes().size() > i + 1 && i>0) {
				btnUpdate.setBtnBringFullBackAct(true);
				btnUpdate.setBtnBringFullFrontAct(true);
				btnUpdate.setBtnToBackAct(true);
				btnUpdate.setBtnToFrontAct(true);
			}
		}else{
			btnUpdate.setBtnBringFullBackAct(false);
			btnUpdate.setBtnBringFullFrontAct(false);
			btnUpdate.setBtnToBackAct(false);
			btnUpdate.setBtnToFrontAct(false);
		}
		//frame.repaint();
	}

	public void mouseClicked(MouseEvent e) {
		command=null;
		Shape newShape=null;
		selShape=null;
		
		if (frame.getBtnSelect().isSelected() && model.getShapes()!= null){
			Iterator<Shape> iterate=model.getShapes().iterator();
			while (iterate.hasNext()){
				Shape shape = iterate.next();
				if(shape.contains(e.getX(), e.getY())) {
					if (shape.isSelected()) {
						command=new CmdDeselectShape(model, shape);
						command.execute();
						model.getUndo().add(command);

				} else if (!shape.isSelected()){
					
					command=new CmdSelectShape(model, shape);
					command.execute();
					model.getUndo().add(command);
					selShape=shape;
				}
			//model.getRedo().clear();
			break;
			}
		}

		} else if(frame.getBtnPoint().isSelected()){
				Point point = new Point(e.getX(), e.getY());
				point.setColor(frame.getBtnInnerColor().getBackground());
				newShape = point;
			} else if (frame.getBtnLine().isSelected()){
				if (startPoint==null) {
					startPoint = new Point (e.getX(), e.getY());
				}
				else {
					Line line = new Line(startPoint, new Point(e.getX(),e.getY()));
					line.setColor(frame.getBtnInnerColor().getBackground());
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
				if(dlgRectangle.isColorChosen()) {
					rectangle.setColor(dlgRectangle.getColor());
				}else {
					rectangle.setColor(frame.getBtnOuterColor().getBackground());
				}if(dlgRectangle.isInnerColorChosen()){
					rectangle.setInnerColor(dlgRectangle.getInnerColor());
				} else {
					rectangle.setInnerColor(frame.getBtnInnerColor().getBackground());
				}
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
				if(dlgCircle.isColorChosen()) {
					circle.setColor(dlgCircle.getColor());
				}else {
					circle.setColor(frame.getBtnOuterColor().getBackground());
				}if (dlgCircle.isInnerColorChosen()){
					circle.setInnerColor(dlgCircle.getInnerColor());
				} else {
					circle.setInnerColor(frame.getBtnInnerColor().getBackground());
				}
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
				if(dlgDonut.isColorChosen()) {
					donut.setColor(dlgDonut.getColor());
				} else {
					donut.setColor(frame.getBtnOuterColor().getBackground());
				}if (dlgDonut.isInnerColorChosen()){
					donut.setInnerColor(dlgDonut.getInnerColor());
				} else {
					donut.setInnerColor(frame.getBtnInnerColor().getBackground());
				}
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
					
					if(dlgHexagon.isColorChosen()) {
						hexagon.setHexagonBorderColor(dlgHexagon.getPicked());
						//hexagon.setHexagonBorderColor(dlgHexagon.getPicked());
					} else {
						//hexagon.setHexagonBorderColor(frame.getBtnOuterColor().getBackground());
						hexagon.setHexagonBorderColor(frame.getBtnOuterColor().getBackground());
					}if (dlgHexagon.isInnerColorChosen()){
						hexagon.setHexagonInnerColor(dlgHexagon.getInnerPickedColor());
						//hexagon.getHexagon().setAreaColor(dlgHexagon.getInnerPickedColor());
					} else {
						hexagon.setHexagonInnerColor(frame.getBtnInnerColor().getBackground());
						//hexagon.getHexagon().setAreaColor(frame.getBtnInnerColor().getBackground());
					}
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
		model.getRedo().clear();
		//btnUpdate.setBtnRedoAct(false);
		btnsUndoRedo();
		checkBtnState();
		frame.repaint();
	}

	protected void editShape(){
		command=null;
		if (model.getSelectedShapes().size()==1) {
			Shape sel = model.getSelectedShapes().get(0);
			selShape=sel;
			if (model.getSelectedShapes().get(0) instanceof Point) {
				Point point = (Point) model.getSelectedShapes().get(0);
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
					command=new CmdModifyPoint((Point)model.getSelectedShapes().get(0), point);
					command.execute();
					model.getUndo().add(command);
					
					model.getRedo().clear();
					btnUpdate.setBtnRedoAct(false);
					btnsUndoRedo();
					//model.getSelectedShapes().get(0).setSelected(true);
				}
			} else if(model.getSelectedShapes().get(0) instanceof Line){
				Line line= (Line)model.getSelectedShapes().get(0);
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
					command=new CmdModifyLine((Line)model.getSelectedShapes().get(0), line);
					command.execute();
					model.getUndo().add(command);
				
					model.getRedo().clear();
					btnUpdate.setBtnRedoAct(false);
					btnsUndoRedo();
					//model.getSelectedShapes().get(0).setSelected(true);
				}
			} else if (sel instanceof Rectangle){
				Rectangle rectangle= (Rectangle) sel;
				DlgRectangle dlgRec=new DlgRectangle();
				dlgRec.getTxtX().setText("" + rectangle.getUpperLeft().getX());
				dlgRec.getTxtY().setText("" + rectangle.getUpperLeft().getY());
				dlgRec.getTxtHeight().setText("" + rectangle.getHeight());
				dlgRec.getTxtWidth().setText("" + rectangle.getWidth());
				dlgRec.setPicked(rectangle.getColor());
				dlgRec.setInnerPickedColor(rectangle.getInnerColor());
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
						rectangle.setInnerColor(dlgRec.getInnerPickedColor());
					}
					sel.setSelected(true);
					frame.repaint();
					command=new CmdModifyRectangle((Rectangle)sel, rectangle);
					command.execute();
					model.getUndo().add(command);
					
					model.getRedo().clear();
					btnUpdate.setBtnRedoAct(false);
					btnsUndoRedo();
					/*ArrayList<Shape> selected = new ArrayList<>();
					for(Shape s: model.getSelectedShapes()) {
						Shape sh = s;
						s.setSelected(false);
						selected.add(sh);
					}
					model.setSelectedShapes(selected);
					model.getSelectedShapes().get(0).setSelected(true);*/
				}
			} else if (model.getSelectedShapes().get(0) instanceof Donut){
				Donut donut= (Donut)model.getSelectedShapes().get(0);
				DlgDonut dlgDonut = new DlgDonut();
				dlgDonut.getTxtX().setText("" + donut.getCenter().getX());
				dlgDonut.getTxtY().setText("" + donut.getCenter().getY());
				dlgDonut.getTxtRadius().setText("" + donut.getRadius());
				dlgDonut.getTxtInnerRadius().setText("" + donut.getInnerRadius());
				dlgDonut.setPicked(donut.getColor());
				dlgDonut.setInnerPickedColor(donut.getInnerColor());
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
						donut.setColor(dlgDonut.getPicked());
					}
					if(dlgDonut.isInnerColorChosen()){
						donut.setInnerColor(dlgDonut.getInnerColor());
					}else{
						donut.setInnerColor(dlgDonut.getInnerPickedColor());
					}
					command=new CmdModifyDonut((Donut)model.getSelectedShapes().get(0), donut);
					command.execute();
					model.getUndo().add(command);
					
			
					model.getRedo().clear();
					btnUpdate.setBtnRedoAct(false);
					btnsUndoRedo();
					//model.getSelectedShapes().get(0).setSelected(true);
				}
			} else if(model.getSelectedShapes().get(0) instanceof Circle){
				Circle circle= (Circle) model.getSelectedShapes().get(0);
				DlgCircle dlgCircle= new DlgCircle();
				dlgCircle.getTxtX().setText("" + circle.getCenter().getX());
				dlgCircle.getTxtY().setText("" + circle.getCenter().getY());
				dlgCircle.getTxtRadius().setText("" + circle.getRadius());
				dlgCircle.setPicked(circle.getColor());
				dlgCircle.setInnerPickedColor(circle.getInnerColor());
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
						circle.setColor(dlgCircle.getPicked());
					}
					if(dlgCircle.isInnerColorChosen()){
						circle.setInnerColor(dlgCircle.getInnerColor());
					} else 
					{
						circle.setInnerColor(dlgCircle.getInnerPickedColor());
					}
					//selShape.setSelected(true);
					command=new CmdModifyCircle((Circle)model.getSelectedShapes().get(0), circle);
					command.execute();
					model.getUndo().add(command);
					
					model.getRedo().clear();
					btnUpdate.setBtnRedoAct(false);
					btnsUndoRedo();
				}
			}else if(model.getSelectedShapes().get(0) instanceof HexagonAdapter){
				HexagonAdapter hexagon= (HexagonAdapter) model.getSelectedShapes().get(0);
				DlgHexagon dlgHexagon= new DlgHexagon();
				dlgHexagon.getTxtX().setText("" + hexagon.getHexagon().getX());
				dlgHexagon.getTxtY().setText("" + hexagon.getHexagon().getY());
				dlgHexagon.getTxtRadius().setText("" + hexagon.getHexagon().getR());
				dlgHexagon.setPicked(hexagon.getHexagonBorderColor());
				dlgHexagon.setInnerPickedColor(hexagon.getHexagonBorderColor());
				dlgHexagon.setModal(true);
				dlgHexagon.setTitle("Edit hexagon");
				dlgHexagon.setVisible(true);
				
				if(dlgHexagon.isOK()){
					Point point=new Point(Integer.parseInt(dlgHexagon.getTxtX().getText()),
							Integer.parseInt(dlgHexagon.getTxtY().getText()));
					hexagon=new HexagonAdapter(point, 
							Integer.parseInt(dlgHexagon.getTxtRadius().getText()));
					if(dlgHexagon.isColorChosen()){
						//hexagon.getHexagon().setBorderColor(dlgHexagon.getPicked());
						hexagon.setHexagonBorderColor(dlgHexagon.getPicked());
					}else{
						//hexagon.getHexagon().setBorderColor(dlgHexagon.getPicked());
						hexagon.setHexagonBorderColor(dlgHexagon.getPicked());
					}
					if(dlgHexagon.isInnerColorChosen()){
						//hexagon.getHexagon().setAreaColor(dlgHexagon.getInnerPickedColor());
						hexagon.setHexagonInnerColor(dlgHexagon.getInnerPickedColor());
					} else
					{
						hexagon.getHexagon().setAreaColor(dlgHexagon.getInnerPickedColor());
					}
					command=new CmdModifyHexagon((HexagonAdapter)model.getSelectedShapes().get(0), hexagon);
					command.execute();
					model.getUndo().add(command);
					
					model.getRedo().clear();
					btnsUndoRedo();
					/*ArrayList<Shape> selected = new ArrayList<>();
					for(Shape s: model.getSelectedShapes()) {
						Shape sh = s;
						s.setSelected(false);
						selected.add(sh);
					}*/
					//model.setSelectedShapes(selected);
					//model.getSelectedShapes().get(0).setSelected(true);
				}
			} 	
			//frame.repaint();
			
			selShape.setSelected(true);
		}
		else if (model.getSelectedShapes().size()==0){
			ImageIcon icon=new ImageIcon("C:/Users/EC/git/IT21-2018-Aleksa-Komosar3/DizajnerskiObrasci/images/er.png");
			JOptionPane.showMessageDialog(null, "You did not select any shape to edit!","Error",
					JOptionPane.WARNING_MESSAGE, icon);
		}
		btnsUndoRedo();
		btnUpdate.setBtnRedoAct(false);
		model.getRedo().clear();
		checkBtnState();
		frame.repaint();
	}
	
	protected void deleteShape() {
		command=null;
		if (model.getSelectedShapes().size()==1) {
			int selectedOption = JOptionPane.showConfirmDialog(null, "Are you sure to delete this one shape?",
					"Warning message", JOptionPane.YES_NO_OPTION);
			if (selectedOption == JOptionPane.YES_OPTION) {
				Shape sh=model.getSelectedShapes().get(0);
				command=new CmdDeleteOneShape(model, sh);
				command.execute();
				model.getUndo().add(command);
			}
		} else if (model.getSelectedShapes().size()>1){
			int selectedOption = JOptionPane.showConfirmDialog(null, "Are you sure to delete more shapes?",
					"Warning message", JOptionPane.YES_NO_OPTION);
			if (selectedOption == JOptionPane.YES_OPTION) {
			for(int i=model.getSelectedShapes().size() - 1; i>=0; i-- )
			{
				 
					command=new CmdDeleteOneShape(model, model.getSelectedShapes().get(i));
					command.execute();
					model.getUndo().add(command);
			}
			}
		}
		else {
			ImageIcon icon=new ImageIcon("C:/Users/EC/git/IT21-2018-Aleksa-Komosar3/DizajnerskiObrasci/images/er.png");
			JOptionPane.showMessageDialog(null, "You did not select any shape!","Error",
					JOptionPane.WARNING_MESSAGE, icon);
		}
		checkBtnState();
		btnUpdate.setBtnRedoAct(false);
		//model.getRedo().clear();
		btnsUndoRedo();
		frame.repaint();
		
	}
	public void fullBringToBack(){
		command=null;
		Shape shape = model.getSelectedShapes().get(0);
		int index= model.getShapes().indexOf(shape);
		command = new CmdBringToBack(model, shape, index);
		command.execute();
		model.getUndo().add(command);
		checkBtnState();
		frame.repaint();
	}
	
	public void bringToBackByOne(){
		command=null;
		Shape shape = model.getSelectedShapes().get(0);
		int index= model.getShapes().indexOf(shape);
		command = new CmdToBackByOne(model, shape, index);
		command.execute();
		model.getUndo().add(command);
		checkBtnState();
		frame.repaint();
	}
	
	public void fullBringToFront(){
		command=null;
		Shape shape = model.getSelectedShapes().get(0);
		int index= model.getShapes().indexOf(shape);
		command = new CmdBringToFront(model, shape, index);
		command.execute();
		model.getUndo().add(command);
		checkBtnState();
		frame.repaint();
	}
	
	public void bringToFrontByOne(){
		command=null;
		Shape shape = model.getSelectedShapes().get(0);
		int index= model.getShapes().indexOf(shape);
		command = new CmdToFrontByOne(model, shape, index);
		command.execute();
		model.getUndo().add(command);
		checkBtnState();
		frame.repaint();
	}
	
	public void undo(){
		if(model.getUndo().size()!=0){
		Command command = model.getUndo().pop();
		command.unexecute();
		model.getRedo().add(command);

		btnUpdate.setBtnRedoAct(true);
			if(model.getUndo().size()==0){
				btnUpdate.setBtnUndoAct(false);
			}
		} else {
			btnUpdate.setBtnUndoAct(false);
		}
		checkBtnState();
		frame.repaint();
	}
	
	public void redo(){
		if(model.getRedo().size()!=0){
		Command command = model.getRedo().pop();
		command.execute();
		model.getUndo().add(command);
	
		btnUpdate.setBtnUndoAct(true);
			if(model.getRedo().size()==0){
				btnUpdate.setBtnRedoAct(false);
			}
		} else {
			btnUpdate.setBtnRedoAct(false);
		}
		checkBtnState();
		frame.repaint();
	}
	
}
