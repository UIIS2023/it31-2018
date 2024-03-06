package mvc;

import java.util.ArrayList;

import geometry.Shape;

public class DrawingModel {

	private ArrayList<Shape> shapes = new ArrayList<Shape>();
	
	
	public void add(Shape s){
		shapes.add(s);
	}
	public void add(int index,Shape element){
		shapes.add(index, element);
	}
	
	public void remove(Shape s){
		shapes.remove(s);
	}
	
	public void remove(int index){
		shapes.remove(index);
	}
	
	public int indexOf(Shape s){
		return shapes.indexOf(s);
	}
	
	
	
	public Shape get(int index){
		return shapes.get(index);
	}
	
	public ArrayList<Shape> getShapes(){
		return shapes;
	}
	
}
