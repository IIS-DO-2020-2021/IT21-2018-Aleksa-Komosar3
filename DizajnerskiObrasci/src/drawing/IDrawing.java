package drawing;

import java.awt.event.MouseEvent;

import geometry.Shape;
import mvc.DrawingController;

public interface IDrawing {
	void delete(DrawingController controler);
	void modify(DrawingController controler);
	void mouseClickSelected(DrawingController controler, MouseEvent e, Shape newShape);
	void modifySelected(DrawingController controler, Shape selected);
}
