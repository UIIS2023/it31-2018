package command;

import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JOptionPane;

import geometry.Shape;
import mvc.DrawingModel;

public class ToFrontCmd implements Command {

	
	
	private DrawingModel model;
	private Shape shape;
	
	public ToFrontCmd(DrawingModel model,Shape shape){
		this.model = model;
		this.shape = shape;
	}
	
	
	@Override
	public void execute() {
		int index =model.indexOf(shape);
		if(index == model.getShapes().size()-1){
			JOptionPane.showMessageDialog(null, 
					"Cant go further front",
					"ERROR",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		Collections.swap(model.getShapes(), index, index+1);

	}

	@Override
	public void unexecute() {
		int index =model.indexOf(shape);
		
		Collections.swap(model.getShapes(), index, index-1);

	}
	
	public String toString(){
		return "To front "+shape.toString();
	}

}
