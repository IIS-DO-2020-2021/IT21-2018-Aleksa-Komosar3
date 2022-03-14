package mvc;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;

import javax.swing.JPanel;

import geometry.Shape;

public class DrawingView extends JPanel {

	private static final long serialVersionUID = 1L;
	//samo referenca treba na model, ne objekat!!!! Ali se ipak kreira da bi se nesto prikazalo
	private DrwingModel model= new DrwingModel();
	
	
	public DrawingView() {
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Iterator<Shape> it = model.getShapes().iterator();
		while (it.hasNext()) {
			it.next().draw(g);
		}
		}
		
	public void setModel(DrwingModel model) {
		this.model = model;
	}
}
