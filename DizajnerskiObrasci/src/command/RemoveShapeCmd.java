package command;

import geometry.Shape;
import mvc.DrawingModel;

public class RemoveShapeCmd implements Command {

	
	private DrawingModel model;
	private Shape shape;
	private int index;
	
	public RemoveShapeCmd(Shape shape,DrawingModel model){
		this.model = model;
	
		index = model.indexOf(shape);
		this.shape = model.get(index);
	}
	
	@Override
	public void execute() {
		model.remove(shape);

	}

	@Override
	public void unexecute() {
		model.add(index,shape);

	}
	
	
	
	public Shape getShape() {
		return shape;
	}

	public String toString(){
		return "Remove "+shape.toString();
	}

}
