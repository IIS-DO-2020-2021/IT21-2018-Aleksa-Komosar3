package observer;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor @Getter @Setter @ToString
public class BtnUpdate {
	
	private PropertyChangeSupport propertyChangeSupport;
	private boolean btnSelectAct;
	private boolean btnModificationAct;
	private boolean btnDeleteAct;
	
	private boolean btnBringFullBackAct;
	private boolean btnBringFullFrontAct;
	private boolean btnToBackAct;
	private boolean btnToFrontAct;
	
	private boolean btnUndoAct;
	private boolean btnRedoAct;
	
	private boolean btnSaveDrwAct;
	private boolean btnSaveComAct;

	public BtnUpdate() {
		super();
		propertyChangeSupport = new PropertyChangeSupport(this);
	}
	
	public void setBtnSaveComAct(boolean btnSaveComAct) {
		propertyChangeSupport.firePropertyChange("btnSaveCommands", this.btnSaveComAct, btnSaveComAct);
		this.btnSaveComAct = btnSaveComAct;
	}
	
	public void setBtnSaveDrwAct(boolean btnSaveDrwAct) {
		propertyChangeSupport.firePropertyChange("btnSaveDrawing", this.btnSaveDrwAct, btnSaveDrwAct);
		this.btnSaveDrwAct = btnSaveDrwAct;
	}
	
	public void setBtnUndoAct(boolean btnUndoAct) {
		propertyChangeSupport.firePropertyChange("btnUndo", this.btnUndoAct, btnUndoAct);
		this.btnUndoAct = btnUndoAct;
	}
	
	public void setBtnRedoAct(boolean btnRedoAct) {
		propertyChangeSupport.firePropertyChange("btnRedo", this.btnRedoAct, btnRedoAct);
		this.btnRedoAct = btnRedoAct;
	}
	
	public void setBtnSelectAct(boolean btnSelectAct) {
		propertyChangeSupport.firePropertyChange("btnSelect", this.btnSelectAct, btnSelectAct);
		this.btnSelectAct = btnSelectAct;
	}
	
	public void setBtnModificationAct(boolean btnModificationAct) {
		propertyChangeSupport.firePropertyChange("btnModification", this.btnModificationAct, btnModificationAct);
		this.btnModificationAct = btnModificationAct;
	}
	public void setBtnDeleteAct(boolean btnDeleteAct) {
		propertyChangeSupport.firePropertyChange("btnDelete", this.btnDeleteAct, btnDeleteAct);
		this.btnDeleteAct = btnDeleteAct;
	}
	
	public void setBtnBringFullBackAct(boolean btnBringFullBackAct) {
		propertyChangeSupport.firePropertyChange("btnBringToBack", this.btnBringFullBackAct, btnBringFullBackAct);
		this.btnBringFullBackAct = btnBringFullBackAct;
	}
	
	public void setBtnBringFullFrontAct(boolean btnBringFullFrontAct) {
		propertyChangeSupport.firePropertyChange("btnBringToFront", this.btnBringFullFrontAct, btnBringFullFrontAct);
		this.btnBringFullFrontAct = btnBringFullFrontAct;
	}

	public void setBtnToBackAct(boolean btnToBackAct) {
		propertyChangeSupport.firePropertyChange("btnToBack", this.btnToBackAct, btnToBackAct);
		this.btnToBackAct = btnToBackAct;
	}
	
	public void setBtnToFrontAct(boolean btnToFrontAct) {
		propertyChangeSupport.firePropertyChange("btnToFront", this.btnToFrontAct, btnToFrontAct);
		this.btnToFrontAct = btnToFrontAct;
	}

	public void addPropertyChangeListener(PropertyChangeListener propertyChangeListener) {
		propertyChangeSupport.addPropertyChangeListener(propertyChangeListener);
	}

	public void removePropertyChangeListener(PropertyChangeListener propertyChangeListener) {
		propertyChangeSupport.removePropertyChangeListener(propertyChangeListener);
	}

}
