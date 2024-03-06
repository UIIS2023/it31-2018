package geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.Shape;

public class Donut extends Circle {

	private int innerRadius;
	private Shape donutShape;
	public Donut() {
		
	}
	
	public Donut(Point center, int radius, int innerRadius) {
		super(center, radius);
		this.innerRadius = innerRadius;
	}
	
	public Donut(Point center, int radius, int innerRadius, boolean selected) {
		this(center, radius, innerRadius);
		setSelected(selected);
	}
	
	public Donut(Point center, int radius, int innerRadius, boolean selected, Color color) { 
		this(center, radius, innerRadius, selected);
		setColor(color);
	}
	
	public Donut(Point center, int radius, int innerRadius, boolean selected, Color color, Color innerColor) { 
		this(center, radius, innerRadius, selected, color);
		setInnerColor(innerColor);
	}
	
	public void draw(Graphics g) {
		super.draw(g);
		g.setColor(getColor());
		g.drawOval(getCenter().getX() - getInnerRadius(), getCenter().getY() - getInnerRadius(), getInnerRadius() * 2, getInnerRadius() * 2);
	}

	public void fill(Graphics g) {
		if(donutShape == null){
			donutShape = createDonutArea();
		}
		Graphics2D g2d = (Graphics2D)g.create();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		g2d.setColor(getInnerColor());
		g2d.fill(donutShape);
	}
	
	private Shape createDonutArea(){
		
		Ellipse2D outer = new Ellipse2D.Double(
				(double)getCenter().getX() - (double)getRadius(),
				(double) getCenter().getY()- (double)getRadius(),
				(double)getRadius() + (double)getRadius(),
				(double)getRadius() + (double)getRadius());
		Ellipse2D inner = new Ellipse2D.Double(
				(double)getCenter().getX() - (double)getInnerRadius(),
				(double) getCenter().getY()- (double)getInnerRadius(),
				(double)getInnerRadius() + (double)getInnerRadius(),
				(double)getInnerRadius() + (double)getInnerRadius());
		Area area = new Area(outer);
		area.subtract(new Area(inner));
		return area;
	}
	
	public int compareTo(Object o) {
		if (o instanceof Donut) {
			return (int) (this.area() - ((Donut) o).area());
		}
		return 0;
	}
	
	public double area() {
		return super.area() - innerRadius * innerRadius * Math.PI;
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof Donut) {
			Donut d = (Donut) obj;
			if (this.getCenter().equals(d.getCenter()) &&
					this.getRadius() == d.getRadius() &&
					this.innerRadius == d.getInnerRadius()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	public boolean contains(int x, int y) {
		double dFromCenter = this.getCenter().distance(x, y);
		return super.contains(x, y) && dFromCenter > innerRadius;
	}
	
	public boolean contains(Point p) {
		double dFromCenter = this.getCenter().distance(p.getX(), p.getY());
		return super.contains(p.getX(), p.getY()) && dFromCenter > innerRadius;
	}
	
	@Override
	public Donut clone(){
		Donut donut = new Donut();
		donut.setCenter(this.getCenter().clone());
		donut.setRadius(this.getRadius());
		donut.setInnerRadius(this.getInnerRadius());
		donut.setColor(this.getColor());
		donut.setInnerColor(this.getInnerColor());
		
		return donut;
	}
	
	
	public int getInnerRadius() {
		return this.innerRadius;
	}
	
	public void setInnerRadius(int innerRadius) {
		this.innerRadius = innerRadius;
	}
	
	public String toString() {
		return "Donut (["+getCenter().getX()+"],["+getCenter().getY()+"]);Radius ["+getRadius()+"];InnerRadius ["+innerRadius+"] ;OuterColor ["+getColor().getRGB()+"];InnerColor ["+getInnerColor().getRGB()+"]";
	}
	
}
