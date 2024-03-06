package drawing;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import geometry.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JToolBar;
import javax.swing.JToggleButton;
import java.awt.Font;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;

import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmDrawing extends JFrame {

	private JPanel contentPane;
	private PnlDrawing drawingPanel= new PnlDrawing(this);
	private final ButtonGroup TglbtnGroup = new ButtonGroup();
	
	private JToggleButton tglbtnSelect = new JToggleButton("Select");
	private JToggleButton tglbtnPoint = new JToggleButton("Point");
	private JToggleButton tglbtnLine = new JToggleButton("Line");
	private JToggleButton tglbtnRectangle = new JToggleButton("Rectangle");
	private JToggleButton tglbtnCircle = new JToggleButton("Circle");
	private JToggleButton tglbtnDonut = new JToggleButton("Donut");
	private final JButton btnColor = new JButton("Color");
	private Color outerColor=Color.BLACK;
	private Color innerColor=Color.BLACK;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmDrawing frame = new FrmDrawing();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	
	



	/**
	 * Create the frame.
	 */
	public FrmDrawing() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 778, 578);
		setTitle("IT31-2018,Gavrilo Stanic");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel pnlNorth = new JPanel();
		contentPane.add(pnlNorth, BorderLayout.NORTH);
		pnlNorth.setLayout(new BoxLayout(pnlNorth, BoxLayout.X_AXIS));
		
		JToolBar toolBar = new JToolBar();
		toolBar.setFont(new Font("Segoe UI", Font.BOLD, 15));
		pnlNorth.add(toolBar);
		
		
		tglbtnSelect.setForeground(Color.BLACK);
		tglbtnSelect.setFont(new Font("Tahoma", Font.BOLD, 13));
		toolBar.add(tglbtnSelect);
		
		
		tglbtnPoint.setFont(new Font("Tahoma", Font.BOLD, 13));
		toolBar.add(tglbtnPoint);
		
		
		tglbtnLine.setFont(new Font("Tahoma", Font.BOLD, 13));
		toolBar.add(tglbtnLine);
		
		
		tglbtnRectangle.setFont(new Font("Tahoma", Font.BOLD, 13));
		toolBar.add(tglbtnRectangle);
		
		
		tglbtnCircle.setFont(new Font("Tahoma", Font.BOLD, 13));
		toolBar.add(tglbtnCircle);
		
	
		tglbtnDonut.setFont(new Font("Tahoma", Font.BOLD, 13));
		toolBar.add(tglbtnDonut);
		
		
		
		TglbtnGroup.add(tglbtnSelect);
		TglbtnGroup.add(tglbtnPoint);
		TglbtnGroup.add(tglbtnLine);
		TglbtnGroup.add(tglbtnRectangle);
		TglbtnGroup.add(tglbtnCircle);
		TglbtnGroup.add(tglbtnDonut);
		
		JButton btnModify = new JButton("Modify");
		toolBar.add(btnModify);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete();
				
			}
		});
		toolBar.add(btnDelete);
		btnColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
		
		toolBar.add(btnColor);
		btnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modify();
				
			}
		});
		getContentPane().add(drawingPanel);
		drawingPanel.setBackground(Color.WHITE);
		
		
	}

	private void delete(){
		Shape selected= drawingPanel.getSelected();
		if(selected != null){
			Object[] options = { "Yes", "No" };
			int reply=JOptionPane.showOptionDialog(null, "Are you sure you want to delete it", "Warning",
		             JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
		             null, options, JOptionPane.YES_NO_OPTION);
			if(reply==JOptionPane.YES_OPTION){
				drawingPanel.getShapes().remove(selected);
			}
		}else{
			JOptionPane.showMessageDialog(null, 
					"You must select a shape",
					"ERROR",
					JOptionPane.ERROR_MESSAGE);
			
		}
		drawingPanel.repaint();
		
	}
	
	private void modify(){
		
		Shape selected =drawingPanel.getSelected();
		if(selected != null){
			if(selected instanceof Point){
				Point point = (Point)selected;
				DlgPoint dlg=new DlgPoint();
				
				dlg.setTxtX(Integer.toString(point.getX()));
				dlg.setTxtY(Integer.toString(point.getY()));
				
				dlg.setVisible(true);
				
				if(dlg.isOk()){
					dlg.getPoint().setColor(outerColor);
					drawingPanel.getShapes().remove(selected);
					drawingPanel.getShapes().add(dlg.getPoint());
					drawingPanel.setSelected(null);
				}
				
			} else if(selected instanceof Line){
				Line line = (Line) selected;
				DlgLine dlg=new DlgLine();
				
				dlg.setTxtStartX(Integer.toString(line.getStartPoint().getX()));
				dlg.setTxtStartY(Integer.toString(line.getStartPoint().getY()));
				dlg.setTxtEndX(Integer.toString(line.getEndPoint().getX()));
				dlg.setTxtEndY(Integer.toString(line.getEndPoint().getY()));
				
				dlg.setVisible(true);
				if(dlg.isOk()){
					dlg.getLine().setColor(outerColor);
					drawingPanel.getShapes().remove(selected);
					drawingPanel.getShapes().add(dlg.getLine());
					drawingPanel.setSelected(null);
				}
				
			}else if(selected instanceof Rectangle){
				
				Rectangle rect=(Rectangle) selected;
				DlgRectangle dlg=new DlgRectangle();
				dlg.setTxtX(Integer.toString(rect.getUpperLeftPoint().getX()));
				dlg.setTxtY(Integer.toString(rect.getUpperLeftPoint().getY()));
				dlg.setTxtHeight(Integer.toString(rect.getHeight()));
				dlg.setTxtWidth(Integer.toString(rect.getWidth()));
				
				dlg.setVisible(true);
				if(dlg.isOk()){
					dlg.getRectangle().setColor(outerColor);
					dlg.getRectangle().setInnerColor(innerColor);
					drawingPanel.getShapes().remove(selected);
					drawingPanel.getShapes().add(dlg.getRectangle());
					drawingPanel.setSelected(null);
				}
				
			}else if(selected instanceof Donut){
				
				
				Donut donut = (Donut) selected;
				DlgDonut dlg=new DlgDonut();
				dlg.setTxtX(Integer.toString(donut.getCenter().getX()));
				dlg.setTxtY(Integer.toString(donut.getCenter().getY()));
				dlg.setTxtInnerRadius(Integer.toString(donut.getInnerRadius()));
				dlg.setTxtOuterRadius(Integer.toString(donut.getRadius()));
				
				dlg.setVisible(true);
				
				if(dlg.isOk()){
					dlg.getDonut().setColor(outerColor);
					dlg.getDonut().setInnerColor(innerColor);
					drawingPanel.getShapes().remove(selected);
					drawingPanel.getShapes().add(dlg.getDonut());
					drawingPanel.setSelected(null);
				}
				
			}else if(selected instanceof Circle){
				Circle circle = (Circle) selected;
				DlgCircle dlg = new DlgCircle();
				dlg.setTxtX(Integer.toString(circle.getCenter().getX()));
				dlg.setTxtY(Integer.toString(circle.getCenter().getY()));
				dlg.setTxtRadius(Integer.toString(circle.getRadius()));
				
				dlg.setVisible(true);
				
				if(dlg.isOk()){
					dlg.getCircle().setColor(outerColor);
					dlg.getCircle().setInnerColor(innerColor);
					drawingPanel.getShapes().remove(selected);
					drawingPanel.getShapes().add(dlg.getCircle());
					drawingPanel.setSelected(null);
				}
				
			}
			
		}else{
			JOptionPane.showMessageDialog(null, 
					"You must select a shape",
					"ERROR",
					JOptionPane.ERROR_MESSAGE);
		}
			
		
		
		drawingPanel.repaint();
		
		
		
	}


	
	







	public void setTglbtnLine(JToggleButton tglbtnLine) {
		this.tglbtnLine = tglbtnLine;
	}



	public JToggleButton getTglbtnLine() {
		return tglbtnLine;
	}



	public JToggleButton getTglbtnRectangle() {
		return tglbtnRectangle;
	}







	public void setTglbtnRectangle(JToggleButton tglbtnRectangle) {
		this.tglbtnRectangle = tglbtnRectangle;
	}







	public JToggleButton getTglbtnCircle() {
		return tglbtnCircle;
	}







	public void setTglbtnCircle(JToggleButton tglbtnCircle) {
		this.tglbtnCircle = tglbtnCircle;
	}







	public JToggleButton getTglbtnDonut() {
		return tglbtnDonut;
	}







	public void setTglbtnDonut(JToggleButton tglbtnDonut) {
		this.tglbtnDonut = tglbtnDonut;
	}







	public JToggleButton getTglbtnSelect() {
		return tglbtnSelect;
	}



	public void setTglbtnSelect(JToggleButton tglbtnSelect) {
		this.tglbtnSelect = tglbtnSelect;
	}


	public JToggleButton getTglbtnPoint() {
		return tglbtnPoint;
	}



	public void setTglbtnPoint(JToggleButton tglbtnPoint) {
		this.tglbtnPoint = tglbtnPoint;
	}







	public Color getOuterColor() {
		return outerColor;
	}







	public void setOuterColor(Color outerColor) {
		this.outerColor = outerColor;
	}







	public Color getInnerColor() {
		return innerColor;
	}







	public void setInnerColor(Color innerColor) {
		this.innerColor = innerColor;
	}
	
	

}
