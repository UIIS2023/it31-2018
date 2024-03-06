package command;

import geometry.Rectangle;

public class UpdateRectangleCmd implements Command {
	
	private Rectangle oldState;
	private Rectangle newState;
	private Rectangle original ;
	
	public UpdateRectangleCmd(Rectangle oldState,Rectangle newState) {
		this.oldState = oldState;
		this.newState = newState;
	}

	//jedan nacin kako sam pre ovo radio
	@Override
	public void execute() {
//		original.getUpperLeftPoint().setX(oldState.getUpperLeftPoint().getX());
//		original.getUpperLeftPoint().setY(oldState.getUpperLeftPoint().getY());
//		original.setHeight(oldState.getHeight());
//		original.setWidth(oldState.getWidth());
//		original.setColor(oldState.getColor());
//		original.setInnerColor(original.getInnerColor());
		
		original = oldState.clone();   
		
		oldState.getUpperLeftPoint().setX(newState.getUpperLeftPoint().getX());
		oldState.getUpperLeftPoint().setY(newState.getUpperLeftPoint().getY());
		oldState.setHeight(newState.getHeight());
		oldState.setWidth(newState.getWidth());
		oldState.setColor(newState.getColor());
		oldState.setInnerColor(newState.getInnerColor());
	}

	@Override
	public void unexecute() {
		oldState.getUpperLeftPoint().setX(original.getUpperLeftPoint().getX());
		oldState.getUpperLeftPoint().setY(original.getUpperLeftPoint().getY());
		oldState.setHeight(original.getHeight());
		oldState.setWidth(original.getWidth());
		oldState.setColor(original.getColor());
		oldState.setInnerColor(original.getInnerColor());
	}

	public String toString(){
		return "Update "+original.toString()+" ==> "+newState.toString();
	}
}
