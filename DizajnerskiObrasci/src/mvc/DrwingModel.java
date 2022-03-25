package mvc;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Stack;

import command.Command;
import geometry.Shape;

public class DrwingModel implements Serializable {
	private ArrayList<Shape> shapes = new ArrayList<Shape>();
	private ArrayList<Shape> selected = new ArrayList<Shape>();
	
	private PropertyChangeSupport propertyChangeSupport;


	public DrwingModel() {
		propertyChangeSupport = new PropertyChangeSupport(this);
	}

	public void add(Shape s) {
		shapes.add(s);
	}
	
	public void addOnIndex(int i, Shape s) {
		shapes.add(i, s);
	}
	
	public void remove(Shape s) {
		shapes.remove(s);
	}

	public Shape getSelectedShape(int i) {
		return selected.get(i);
	}

	public ArrayList<Shape> getShapes() {
		return shapes;
	}

	public ArrayList<Shape> getSelected() {
		return selected;
	}

	public ArrayList<Shape> getSelectedShapes() {
		return selected;
	}
	
	public Shape getShape(int i) {
		return shapes.get(i);
	}

	/*Selected*/
	public void addSelected(Shape s) {
		propertyChangeSupport.firePropertyChange("selected", this.selected.size(), this.selected.size()+1);
		selected.add(s);
	}

	public void removeSelected(Shape s) {
		propertyChangeSupport.firePropertyChange("selected", this.selected.size(), this.selected.size()-1);
		selected.remove(s);
	}
	
	public void addPropertyChangeListener(PropertyChangeListener propertyChangeListener) {
		propertyChangeSupport.addPropertyChangeListener(propertyChangeListener);
	}

	public void removePropertyChangeListener(PropertyChangeListener propertyChangeListener) {
		propertyChangeSupport.removePropertyChangeListener(propertyChangeListener);
	}
}
