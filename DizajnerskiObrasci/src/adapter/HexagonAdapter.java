package adapter;

import java.awt.Color;
import java.awt.Graphics;

import geometry.Donut;
import geometry.Point;
import geometry.SurfaceShape;
import hexagon.Hexagon;

public class HexagonAdapter extends SurfaceShape {

	private Hexagon hexagon;
	
	
	private Point center;
	
	
	public HexagonAdapter(Hexagon hexagon){
		this.hexagon = hexagon;
	}
	public HexagonAdapter(int x,int y, int radius){
		hexagon = new Hexagon(x,y,radius);
		center = new Point(x,y);
	}
	
	public HexagonAdapter(Point center, int radius) {
		hexagon = new Hexagon(center.getX(), center.getY(), radius);
		this.center = center;
		
	
	}
	
	public HexagonAdapter(Point center, int radius, boolean selected) {
		this(center,radius);
		hexagon.setSelected(selected);
		this.center = center;
	
	}
	
	public HexagonAdapter(Point center, int radius, boolean selected, Color color) { 
		this(center,radius,selected);
		hexagon.setBorderColor(color);
		this.center = center;
	}
	
	public HexagonAdapter(Point center, int radius, boolean selected, Color color, Color innerColor) { 
		this(center,radius,selected,color);
		hexagon.setAreaColor(innerColor);
		this.center = center;
	}

	@Override
	public void moveBy(int byX, int byY) {
		hexagon.setX(hexagon.getX()+byX);
		hexagon.setY(hexagon.getY()+byY);
		
		
		
	}

	
	public boolean equals(Object o){
		
		if(o instanceof HexagonAdapter){
			HexagonAdapter tempHex = (HexagonAdapter)o;
			if(center.equals(tempHex.getCenter()) && getRadius() == tempHex.getRadius()){
				return true;
				
			}
			
		}
		
		return false;
	}
	
	@Override
	public int compareTo(Object o) {
		if (o instanceof HexagonAdapter) {
			return (int) (this.area() - ((HexagonAdapter) o).area());
		}
		return 0;
	}

	@Override
	public double area() {
		return (3 * Math.sqrt(3) * hexagon.getR()*hexagon.getR())/2;
		
		
	}

	@Override
	public void fill(Graphics g) {
		// TODO Auto-generated method stub
	
		
	}

	@Override
	public boolean contains(int x, int y) {
		if(hexagon.doesContain(x, y)){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public void draw(Graphics g) {
		hexagon.setX(center.getX());
		hexagon.setY(center.getY());
		hexagon.paint(g);
		
	}
	@Override
	public HexagonAdapter clone(){
		HexagonAdapter hexagon = new HexagonAdapter(new Point(0,0),0);
		hexagon.setCenter(new Point(this.center.getX(),this.center.getY()));
		hexagon.setRadius(this.getRadius());
		hexagon.setColor(this.getColor());
		hexagon.setInnerColor(this.getInnerColor());
		return hexagon;
	}
	
	@Override
	public void setColor(Color color){
		hexagon.setBorderColor(color);
	}
	@Override
	public Color getColor(){
		return hexagon.getBorderColor();
	}
	
	@Override
	public void setInnerColor(Color innerColor){
		hexagon.setAreaColor(innerColor);
	}
	@Override
	public Color getInnerColor(){
		return hexagon.getAreaColor();
	}
	@Override
	public void setSelected(boolean selected){
		hexagon.setSelected(selected);
	
	}
	@Override
	public boolean isSelected(){
		return hexagon.isSelected();
	}
	
	
	public int getRadius(){
		return hexagon.getR();
	}
	
	public void setCenter(Point p){
		hexagon.setX(p.getX());
		hexagon.setY(p.getY());
		center = p;
	}
	
	
	public void setRadius(int r){
		hexagon.setR(r);
	}
	
	
	public String toString(){
		return "Hexagon (["+center.getX()+"],["+center.getY()+"]);Radius ["+getRadius()+"];OuterColor ["+getColor().getRGB()+"];InnerColor ["+getInnerColor().getRGB()+"];";
	}
	public Point getCenter() {
		return center;
	}
	
	
}
