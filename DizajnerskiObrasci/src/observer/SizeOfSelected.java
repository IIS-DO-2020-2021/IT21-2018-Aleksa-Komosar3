package observer;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import mvc.DrawingFrame;

public class SizeOfSelected implements PropertyChangeListener {
	
	private int size;
	private DrawingFrame drawingFrame;

	public SizeOfSelected(DrawingFrame drawingFrame) {
		this.drawingFrame = drawingFrame;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals("selected")) {
			this.size = (int) evt.getNewValue();
			enablingButtons();
		}
	}

	public void enablingButtons() {
		if (size==0) {
			drawingFrame.getBtnModification().setEnabled(false);
			drawingFrame.getBtnDelete().setEnabled(false);
		} else if (size==1) {
			drawingFrame.getBtnModification().setEnabled(true);
			drawingFrame.getBtnDelete().setEnabled(true);
		} else {
			drawingFrame.getBtnModification().setEnabled(false);
			drawingFrame.getBtnDelete().setEnabled(true);
		}		
	}

}
