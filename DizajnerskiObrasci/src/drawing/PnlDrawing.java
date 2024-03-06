package drawing;

import java.util.ArrayList;
import java.util.Iterator;

import geometry.*;


import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PnlDrawing extends JPanel {
	
	
	private FrmDrawing frame;
	private ArrayList<Shape> shapes=new ArrayList<Shape>();
	private Point startPoint;
	private Shape selected;
	
	/**
	 * Create the panel.
	 */
	public PnlDrawing(FrmDrawing frame) {
		this.frame=frame;
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				thisMouseClicked(e);
			}
		});

	}
	
	protected void thisMouseClicked(MouseEvent e){
		
		Shape newShape=null;
		if(frame.getTglbtnSelect().isSelected()){
			selected=null;
			Iterator<Shape> it= shapes.iterator();
			while(it.hasNext()){
				Shape shape=it.next();
				shape.setSelected(false);
				if(shape.contains(e.getX(), e.getY())){
					selected=shape;	
				}		
			}
			if(selected!=null){
				selected.setSelected(true);
			}
		}else if (frame.getTglbtnPoint().isSelected()){
			newShape=new Point(e.getX(),e.getY(),false,frame.getOuterColor());
		}else if(frame.getTglbtnLine().isSelected()){
			if(startPoint==null){
				startPoint=new Point(e.getX(),e.getY());
			}else{
				newShape=new Line(startPoint,new Point(e.getX(),e.getY()),false,frame.getOuterColor());
				startPoint=null;
			}
		}else if(frame.getTglbtnRectangle().isSelected()){
			DlgRectangle dlg=new DlgRectangle();
			
			dlg.setTxtX(Integer.toString(e.getX())); 
			dlg.setTxtY(Integer.toString(e.getY()));
			dlg.setVisible(true);
			if(dlg.isOk()){
				dlg.getRectangle().setColor(frame.getOuterColor());
				dlg.getRectangle().setInnerColor(frame.getInnerColor());
				newShape=dlg.getRectangle();
			}
			else{
				return;
			}
		}else if(frame.getTglbtnCircle().isSelected()){
			DlgCircle dlg=new DlgCircle();
			
			dlg.setTxtX(Integer.toString(e.getX()));
			dlg.setTxtY(Integer.toString(e.getY()));
			dlg.setVisible(true);
			if(dlg.isOk()){
				dlg.getCircle().setColor(frame.getOuterColor());
				dlg.getCircle().setInnerColor(frame.getInnerColor());
				newShape=dlg.getCircle();
			}
			else{
				return;
			}
			
		}else if(frame.getTglbtnDonut().isSelected()){
			DlgDonut dlg=new DlgDonut();
		
			dlg.setTxtX(Integer.toString(e.getX()));
			dlg.setTxtY(Integer.toString(e.getY()));
			dlg.setVisible(true);
			if(dlg.isOk()){
				dlg.getDonut().setColor(frame.getOuterColor());
				dlg.getDonut().setInnerColor(frame.getInnerColor());
				newShape=dlg.getDonut();
			}
			else{
				return;
			}
			
			
		}
		if(newShape!=null){
			shapes.add(newShape);
		}
		repaint();
		
	}
	
	public void paint(Graphics g){
		super.paint(g);
		Iterator<Shape> it=shapes.iterator();
		while(it.hasNext()){
			it.next().draw(g);
		}	
	}
	
	public Shape getSelected(){
		return selected;
	}
	
	public void setSelected(Shape shape){
		selected=shape;
	}
	
	public ArrayList<Shape> getShapes(){
		return shapes;
	}
}
