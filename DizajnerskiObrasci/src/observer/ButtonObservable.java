package observer;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

import geometry.Shape;

public class ButtonObservable {

	
	
	
	private PropertyChangeSupport propertyChangeSupport;
	
	private int selectionSize=-1;
	private int undoSize = -1;
	private int redoSize = -1;
	
	
	
	
	
	public ButtonObservable(){
		propertyChangeSupport = new PropertyChangeSupport(this);

		
	}
	
	public void addPropertyChangeListener(PropertyChangeListener listener){
		propertyChangeSupport.addPropertyChangeListener(listener);
	}
	
	public void removePropertyCHangeListener(PropertyChangeListener listener){
		propertyChangeSupport.removePropertyChangeListener(listener);
	}
	
	public void selectionChanged(int size){
		
		propertyChangeSupport.firePropertyChange("selection",this.selectionSize,size);
		this.selectionSize = size;
		
	}
	
	public void undoListChanged(int size){
		propertyChangeSupport.firePropertyChange("undo",this.undoSize,size);
		undoSize = size;
	}
	
	public void redoListChanged(int size){
		propertyChangeSupport.firePropertyChange("redo",this.redoSize,size);
		redoSize = size;
	}
	
	
	
	
	
}
