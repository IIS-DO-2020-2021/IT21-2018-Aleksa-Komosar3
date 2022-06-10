package mvc;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;

import javax.swing.JPanel;


import geometry.Shape;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor  @Getter @Setter @ToString
public class DrawingView extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private DrawingModel model=new DrawingModel();

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
		Iterator<Shape> iterateShape = model.getShapes().iterator();
		while (iterateShape.hasNext()) {
			iterateShape.next().draw(g);
		}
	}
}
