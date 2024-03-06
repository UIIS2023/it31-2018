package mvc;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.border.EmptyBorder;

import drawing.DlgCircle;
import drawing.DlgDonut;
import drawing.DlgLine;
import drawing.DlgPoint;
import drawing.DlgRectangle;
import drawing.FrmDrawing;
import drawing.PnlDrawing;
import geometry.Circle;
import geometry.Donut;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Component;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import java.awt.GridLayout;

public class DrawingFrame extends JFrame {
	

	private DrawingView view = new DrawingView();
	private DrawingController controller;	
	
	private final ButtonGroup TglbtnGroup = new ButtonGroup();
	
	private JToggleButton tglbtnSelect = new JToggleButton("Select");
	private JToggleButton tglbtnPoint = new JToggleButton("Point");
	private JToggleButton tglbtnLine = new JToggleButton("Line");
	private JToggleButton tglbtnRectangle = new JToggleButton("Rectangle");
	private JToggleButton tglbtnCircle = new JToggleButton("Circle");
	private JToggleButton tglbtnDonut = new JToggleButton("Donut");
	private final JToggleButton tglbtnHexagon = new JToggleButton("Hexagon");
	private final JButton btnUndo = new JButton("Undo");
	private final JButton btnRedo = new JButton("Redo");
	private final JPanel pnlSouth = new JPanel();
	private final JList<String> logList = new JList<String>();
	JScrollPane scrollPane = new JScrollPane();
	DefaultListModel<String> logListModel = new DefaultListModel<String>();
	private final JLabel lblOuterColor = new JLabel("Outer color: ");
	private final JPanel pnlOuterColor = new JPanel();
	JPanel pnlInnerColor = new JPanel();
	private final JButton btnToFront = new JButton("To front");
	private final JButton btnBringToFront = new JButton("Bring to front");
	private final JButton btnToBack = new JButton("To back");
	private final JButton btnBringToBack = new JButton("Bring to back");
	private final JButton btnDelete = new JButton("Delete");
	private final JButton btnModify = new JButton("Modify");
	
	private final JMenuBar menuBar = new JMenuBar();
	  
    // JMenu
	private final JMenu menu = new JMenu("File",false);
  
    // Menu items
	private final JMenuItem menuItemSaveImage = new JMenuItem("Save");
	private final JMenuItem menuItemLoadImage = new JMenuItem("Load");
	private final JMenuItem menuItemSaveLog = new JMenuItem("Save log");
	private final JMenuItem menuItemLoadLog = new JMenuItem("Load log");
	private final JButton btnNext = new JButton("Next");


	
	
	



	
	public DrawingFrame() {
		pnlOuterColor.setBackground(Color.BLACK);
		lblOuterColor.setFont(new Font("Tahoma", Font.PLAIN, 15));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setTitle("IT31-2018,Gavrilo Stanic");
		
		menuItemSaveImage.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.saveDrawing();
				
			}
		});
		
		menuItemLoadImage.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.loadDrawing();
				
			}
		});
		
		btnNext.setVisible(false);
		
		menuItemSaveLog.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.saveLog();
				
			}
		});
		
		menuItemLoadLog.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.LoadLog();
				
			}
		});
		menu.add(menuItemSaveImage);
		menu.add(menuItemLoadImage);
		menu.add(menuItemSaveLog);
		menu.add(menuItemLoadLog);
		
		menuBar.add(menu);
		
		this.setJMenuBar(menuBar);
		
		JPanel pnlNorth = new JPanel();

		getContentPane().add(pnlNorth, BorderLayout.NORTH);
		pnlNorth.setLayout(new BoxLayout(pnlNorth, BoxLayout.Y_AXIS));
		
		JToolBar toolBar = new JToolBar();
		toolBar.setAlignmentX(Component.LEFT_ALIGNMENT);
		toolBar.setFont(new Font("Segoe UI", Font.BOLD, 15));
		pnlNorth.add(toolBar);
		btnUndo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.undo();
			}
		});
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.loadNext();
			}
		});
		btnNext.setFont(new Font("Tahoma", Font.ITALIC, 13));
		
		toolBar.add(btnNext);
		btnUndo.setFont(new Font("Tahoma", Font.ITALIC, 13));
		
		toolBar.add(btnUndo);
		btnRedo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.redo(); 
			}
		});
		btnRedo.setFont(new Font("Tahoma", Font.ITALIC, 13));
		
		toolBar.add(btnRedo);
		
		
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
		
		
		
		tglbtnHexagon.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		toolBar.add(tglbtnHexagon);
		TglbtnGroup.add(tglbtnHexagon);
		
		toolBar.add(btnModify);
		
		
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.delete();
				
			}
		});
		toolBar.add(btnDelete);
		btnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.modify();
				
			}
		});
		getContentPane().add(view, BorderLayout.CENTER);
		view.setBackground(Color.WHITE);
		
		getContentPane().add(pnlSouth, BorderLayout.SOUTH);
		logList.setVisibleRowCount(6);
		
		
		
		scrollPane.setViewportView(logList);
		scrollPane.setPreferredSize(new Dimension(250, 100));
		
		
		
		view.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e){
				controller.mouseClicked(e);
			}
		});
		
		
		pnlOuterColor.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e){
				controller.changeOuterColor();
			}
		});
		
		pnlInnerColor.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e){
				controller.changeInnerColor();
			}
		});
		
		
		
		logList.setModel(logListModel);
		
		JLabel lblLog = new JLabel("Log:");
		
		
		pnlInnerColor.setBackground(Color.BLACK);
		
		JLabel lblInnerColor = new JLabel("Inner color: ");
		lblInnerColor.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GroupLayout gl_pnlSouth = new GroupLayout(pnlSouth);
		gl_pnlSouth.setHorizontalGroup(
			gl_pnlSouth.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_pnlSouth.createSequentialGroup()
					.addGap(40)
					.addGroup(gl_pnlSouth.createParallelGroup(Alignment.LEADING)
						.addComponent(lblInnerColor)
						.addComponent(lblOuterColor))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_pnlSouth.createParallelGroup(Alignment.LEADING, false)
						.addComponent(pnlOuterColor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(pnlInnerColor, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_pnlSouth.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlSouth.createSequentialGroup()
							.addGap(70)
							.addComponent(btnBringToFront)
							.addGap(30)
							.addComponent(btnBringToBack)
							.addPreferredGap(ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
							.addComponent(lblLog))
						.addGroup(gl_pnlSouth.createSequentialGroup()
							.addGap(80)
							.addComponent(btnToFront)
							.addGap(58)
							.addComponent(btnToBack)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_pnlSouth.setVerticalGroup(
			gl_pnlSouth.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlSouth.createSequentialGroup()
					.addGroup(gl_pnlSouth.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlSouth.createSequentialGroup()
							.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_pnlSouth.createSequentialGroup()
							.addGap(22)
							.addGroup(gl_pnlSouth.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_pnlSouth.createParallelGroup(Alignment.LEADING, false)
									.addComponent(pnlInnerColor, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(lblInnerColor, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addGroup(gl_pnlSouth.createParallelGroup(Alignment.BASELINE)
									.addComponent(btnToFront)
									.addComponent(btnToBack)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_pnlSouth.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_pnlSouth.createParallelGroup(Alignment.BASELINE)
									.addComponent(lblLog)
									.addComponent(btnBringToFront)
									.addComponent(btnBringToBack))
								.addGroup(gl_pnlSouth.createParallelGroup(Alignment.LEADING, false)
									.addComponent(pnlOuterColor, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(lblOuterColor, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		btnToBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.toBack();
			}
		});
		btnBringToFront.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				controller.bringToFront();
			}
		});
		btnBringToBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.bringToBack();
			}
		});
		GroupLayout gl_pnlOuterColor = new GroupLayout(pnlOuterColor);
		gl_pnlOuterColor.setHorizontalGroup(
			gl_pnlOuterColor.createParallelGroup(Alignment.LEADING)
				.addGap(0, 19, Short.MAX_VALUE)
		);
		gl_pnlOuterColor.setVerticalGroup(
			gl_pnlOuterColor.createParallelGroup(Alignment.LEADING)
				.addGap(0, 19, Short.MAX_VALUE)
		);
		pnlOuterColor.setLayout(gl_pnlOuterColor);
		btnToFront.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				controller.toFront();
			}
		});
		pnlSouth.setLayout(gl_pnlSouth);
		
		
		
		
		
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


	public DrawingView getView() {
		return view;
	}

	public void setController(DrawingController controller){
		this.controller = controller;
	}

	public JToggleButton getTglbtnHexagon() {
		return tglbtnHexagon;
	}




	public JPanel getPnlInnerColor() {
		return pnlInnerColor;
	}




	public void setPnlInnerColor(JPanel pnlInnerColor) {
		this.pnlInnerColor = pnlInnerColor;
	}


	


	public JPanel getPnlOuterColor() {
		return pnlOuterColor;
	}




	public JButton getBtnToFront() {
		return btnToFront;
	}




	public JButton getBtnBringToFront() {
		return btnBringToFront;
	}




	public JButton getBtnToBack() {
		return btnToBack;
	}




	public JButton getBtnBringToBack() {
		return btnBringToBack;
	}




	public JButton getBtnDelete() {
		return btnDelete;
	}




	public JButton getBtnModify() {
		return btnModify;
	}

	


	public JButton getBtnUndo() {
		return btnUndo;
	}




	public JButton getBtnRedo() {
		return btnRedo;
	}




	public DefaultListModel<String> getLogListModel() {
		return logListModel;
	}




	public JButton getBtnNext() {
		return btnNext;
	}
	
}
	
	
	
	

