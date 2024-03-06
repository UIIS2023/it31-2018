package geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

public abstract class Shape implements Moveable, Comparable,Serializable  {
	
	private boolean selected;
	private Color color;
	
	private static final long SerialVersionUID = 1L;
	
	public abstract boolean contains(int x, int y);
	public abstract void draw(Graphics g);
	public abstract Shape clone();
	

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	
	

}
