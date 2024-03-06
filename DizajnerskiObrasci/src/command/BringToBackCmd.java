package command;

import java.util.Collections;

import javax.swing.JOptionPane;

import geometry.Shape;
import mvc.DrawingModel;

public class BringToBackCmd implements Command {

	
	private DrawingModel model;
	private int index;
	private Shape shape;
	
	
	public BringToBackCmd(DrawingModel model,Shape shape){
		this.model = model;
		index = model.indexOf(shape);
		this.shape = shape;
	}
	
	@Override
	public void execute() {
		
		if(index == 0){
			JOptionPane.showMessageDialog(null, 
					"Cant go further back",
					"ERROR",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		for(int i = index;i>0;i--){
			Collections.swap(model.getShapes(), i, i-1);
		}

	}

	@Override
	public void unexecute() {
		for(int i = 0;i<index;i++){
			Collections.swap(model.getShapes(), i, i+1);
		}

	}
	
	public String toString(){
		return "Bring to back "+shape.toString();
	}

}
