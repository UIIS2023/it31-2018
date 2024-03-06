package command;

import java.util.Collections;

import javax.swing.JOptionPane;

import geometry.Shape;
import mvc.DrawingModel;

public class BringToFrontCmd implements Command {

	DrawingModel model;
	
	int index;
	Shape shape;
	
	
	public BringToFrontCmd(DrawingModel model,Shape shape){
		this.model = model;
		index = model.indexOf(shape);
		this.shape = shape;
	}
	
	
	@Override
	public void execute() {
		
		if(index == model.getShapes().size()-1){
			JOptionPane.showMessageDialog(null, 
					"Cant go further front",
					"ERROR",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		for(int i = index;i<model.getShapes().size()-1;i++){
			Collections.swap(model.getShapes(), i, i+1);
		}

	}

	@Override
	public void unexecute() {
		for(int i = model.getShapes().size()-1;i>index;i--){
			Collections.swap(model.getShapes(), i, i-1);
		}

	}
	
	public String toString(){
		return "Bring to front "+shape.toString();
	}

}
