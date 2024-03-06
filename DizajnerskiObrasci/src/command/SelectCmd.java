package command;

import java.util.ArrayList;

import geometry.Shape;
import mvc.DrawingModel;
import observer.ButtonObservable;

public class SelectCmd implements Command {

	
	
	private ArrayList<Shape> selectedList;
	private Shape selected;

	
	
	public SelectCmd(ArrayList<Shape> selectedList,Shape selected){
		this.selectedList = selectedList;
		this.selected = selected;
		
	

	}
	
	@Override
	public void execute() {
		selected.setSelected(true);
		selectedList.add(selected);
		
	}

	@Override
	public void unexecute() {
		selected.setSelected(false);
		selectedList.remove(selected);
		

	}
	
	
	public String toString(){
		return "Select "+selected.toString();
	}

}
