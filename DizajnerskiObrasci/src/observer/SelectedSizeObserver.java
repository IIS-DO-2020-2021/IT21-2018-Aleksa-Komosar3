package observer;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import mvc.DrawingFrame;

public class SelectedSizeObserver implements PropertyChangeListener {
	
	private int size;
	private DrawingFrame drFrame;

	public SelectedSizeObserver(DrawingFrame drFrame) {
		this.drFrame = drFrame;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals("selected")) {
			this.size = (int) evt.getNewValue();
			changeEnabled();
		}
	}

	public void changeEnabled() {
		if (size==0) {
			drFrame.getBtnModification().setEnabled(false);
			drFrame.getBtnDelete().setEnabled(false);
		} else if (size==1) {
			drFrame.getBtnModification().setEnabled(true);
			drFrame.getBtnDelete().setEnabled(true);
		} else {
			drFrame.getBtnModification().setEnabled(false);
			drFrame.getBtnDelete().setEnabled(true);
		}		
	}

}
