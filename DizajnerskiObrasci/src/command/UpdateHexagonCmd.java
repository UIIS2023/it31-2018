package command;

import adapter.HexagonAdapter;

public class UpdateHexagonCmd implements Command {

	
	private HexagonAdapter oldState;
	private HexagonAdapter newState;
	private HexagonAdapter original;
	
	
	
	public UpdateHexagonCmd(HexagonAdapter oldState, HexagonAdapter newState) {
		super();
		this.oldState = oldState;
		this.newState = newState;
	}

	@Override
	public void execute() {
		original = oldState.clone();
		
		oldState.getCenter().setX(newState.getCenter().getX());
		oldState.getCenter().setY(newState.getCenter().getY());
		oldState.setRadius(newState.getRadius());
		oldState.setColor(newState.getColor());
		oldState.setInnerColor(newState.getInnerColor());
		

	}

	@Override
	public void unexecute() {
		oldState.getCenter().setX(newState.getCenter().getX());
		oldState.getCenter().setY(newState.getCenter().getY());
		oldState.setRadius(original.getRadius());
		oldState.setColor(original.getColor());
		oldState.setInnerColor(original.getInnerColor());

	}
	public String toString(){
		return "Update "+original.toString()+" ==> "+newState.toString();
	}

}
