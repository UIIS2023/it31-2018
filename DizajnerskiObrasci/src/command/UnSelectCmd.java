package command;

import java.util.ArrayList;

import geometry.Shape;
import mvc.DrawingModel;
import observer.ButtonObservable;

public class UnSelectCmd implements Command {

	
	

	private ArrayList<Shape> selectedList;
	private Shape selected;


	
	public UnSelectCmd(ArrayList<Shape> selectedList,Shape selected){
			this.selectedList = selectedList;
			this.selected = selected;
			
			
		}
	
	@Override
	public void execute() {
		selected.setSelected(false);
		selectedList.remove(selected);
		
	}

	@Override
	public void unexecute() {
		selected.setSelected(true);
		selectedList.add(selected);
		
	}
	
	public String toString(){
		return "Unselect "+selected.toString();
	}

}
