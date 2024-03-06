package command;

import geometry.Point;
import geometry.Shape;
import mvc.DrawingModel;

public class AddShapeCmd implements Command {

	private DrawingModel model;
	private Shape shape;
	
	public AddShapeCmd(Shape shape,DrawingModel model){
		this.model = model;
		this.shape = shape;
	}
	
	
	@Override
	public void execute() {
		model.add(shape);
		
	
		

	}

	@Override
	public void unexecute() {
		model.remove(shape);

	}
	
	public String toString(){
		return "Add "+shape.toString();
	}

}
