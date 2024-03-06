package observer;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import mvc.DrawingFrame;

public class ButtonObserver  implements PropertyChangeListener{

	
	private DrawingFrame frame;
	
	public ButtonObserver(DrawingFrame frame){
		this.frame = frame;
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if(evt.getPropertyName().equals("selection")){
			if((int)evt.getNewValue()==0){
			
				frame.getBtnModify().setVisible(false);
				frame.getBtnDelete().setVisible(false);
				frame.getBtnToFront().setVisible(false);
				frame.getBtnToBack().setVisible(false);
				frame.getBtnBringToBack().setVisible(false);
				frame.getBtnBringToFront().setVisible(false);
			}else if((int)evt.getNewValue()==1){
				
				frame.getBtnModify().setVisible(true);
				frame.getBtnDelete().setVisible(true);
				frame.getBtnToFront().setVisible(true);
				frame.getBtnToBack().setVisible(true);
				frame.getBtnBringToBack().setVisible(true);
				frame.getBtnBringToFront().setVisible(true);
				
			}else{
				frame.getBtnModify().setVisible(false);
				frame.getBtnDelete().setVisible(true);
				frame.getBtnToFront().setVisible(false);
				frame.getBtnToBack().setVisible(false);
				frame.getBtnBringToBack().setVisible(false);
				frame.getBtnBringToFront().setVisible(false);
			}
		}else if(evt.getPropertyName().equals("undo")){
			if((int)evt.getNewValue() == 0){
				frame.getBtnUndo().setVisible(false);
			}else{
				frame.getBtnUndo().setVisible(true);
			}
			
			
		}else if(evt.getPropertyName().equals("redo")){
			if((int)evt.getNewValue() == 0){
				frame.getBtnRedo().setVisible(false);
			}else{
				frame.getBtnRedo().setVisible(true);
			}
		}
		
	}

	
	
	
}
