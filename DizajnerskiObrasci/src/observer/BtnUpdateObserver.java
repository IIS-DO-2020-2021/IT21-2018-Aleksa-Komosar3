package observer;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import mvc.DrawingFrame;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter @ToString
public class BtnUpdateObserver implements PropertyChangeListener {
	private DrawingFrame frame;
	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals("btnSelect")) {
			frame.getBtnSelect().setEnabled((boolean) evt.getNewValue());
		} if (evt.getPropertyName().equals("btnDelete")) {
			frame.getBtnDelete().setEnabled((boolean) evt.getNewValue());
		} if (evt.getPropertyName().equals("btnModification")) {
			frame.getBtnModification().setEnabled((boolean) evt.getNewValue());
		} if (evt.getPropertyName().equals("btnToFront")) {
			frame.getBtnToFront().setEnabled((boolean) evt.getNewValue());
		} if (evt.getPropertyName().equals("btnBringToFront")) {
			frame.getBtnBringToFront().setEnabled((boolean) evt.getNewValue());
		} if (evt.getPropertyName().equals("btnToBack")) {
			frame.getBtnToBack().setEnabled((boolean) evt.getNewValue());
		} if (evt.getPropertyName().equals("btnBringToBack")) {
			frame.getBtnBringToBack().setEnabled((boolean) evt.getNewValue());
		} if (evt.getPropertyName().equals("btnUndo")) {
			frame.getBtnUndo().setEnabled((boolean) evt.getNewValue());
		} if (evt.getPropertyName().equals("btnRedo")) {
			frame.getBtnRedo().setEnabled((boolean) evt.getNewValue());
		}
	}
}
