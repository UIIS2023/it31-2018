package stack;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;

import geometry.Rectangle;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import net.miginfocom.swing.MigLayout;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmStack extends JFrame {

	private JPanel contentPane;
	private DefaultListModel<Rectangle> dlm = new DefaultListModel<Rectangle>();
	JList lstRectangle = new JList();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmStack frame = new FrmStack();
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
	public FrmStack() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 426, 384);
		contentPane = new JPanel();
		setTitle("IT31-2018,Gavrilo Stanic");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel pnlSouth = new JPanel();
		contentPane.add(pnlSouth, BorderLayout.SOUTH);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DlgRectangle dialog=new DlgRectangle();
				dialog.setVisible(true);
				
				if(dialog.isOK){
					dlm.add(0,dialog.rectangle );
				}
				
				
			}
		});
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
				DlgRectangle dialog=new DlgRectangle();
				Rectangle rect=dlm.get(0);
				dialog.setTxtX(Integer.toString(rect.getUpperLeftPoint().getX()));
				dialog.setTxtY(Integer.toString(rect.getUpperLeftPoint().getY()));
				dialog.setTxtWidth(Integer.toString(rect.getWidth()));
				dialog.setTxtHeight(Integer.toString(rect.getHeight()));
				dialog.setVisible(true);
				if(dialog.isOK){
					dlm.remove(0);
				}
				}
				catch( java.lang.ArrayIndexOutOfBoundsException e2){
					
					getToolkit().beep();
					JOptionPane.showMessageDialog(null, 
							"The list is empty",
							"ERROR",
							JOptionPane.ERROR_MESSAGE);
				
				}
				
			}
		});
		btnDelete.setHorizontalAlignment(SwingConstants.RIGHT);
		GroupLayout gl_pnlSouth = new GroupLayout(pnlSouth);
		gl_pnlSouth.setHorizontalGroup(
			gl_pnlSouth.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlSouth.createSequentialGroup()
					.addGap(43)
					.addComponent(btnAdd, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
					.addGap(173)
					.addComponent(btnDelete)
					.addGap(44))
		);
		gl_pnlSouth.setVerticalGroup(
			gl_pnlSouth.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlSouth.createSequentialGroup()
					.addGap(7)
					.addGroup(gl_pnlSouth.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAdd)
						.addComponent(btnDelete)))
		);
		pnlSouth.setLayout(gl_pnlSouth);
		
		JPanel plnCenter = new JPanel();
		contentPane.add(plnCenter, BorderLayout.CENTER);
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_plnCenter = new GroupLayout(plnCenter);
		gl_plnCenter.setHorizontalGroup(
			gl_plnCenter.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_plnCenter.createSequentialGroup()
					.addGap(53)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 288, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(57, Short.MAX_VALUE))
		);
		gl_plnCenter.setVerticalGroup(
			gl_plnCenter.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_plnCenter.createSequentialGroup()
					.addGap(35)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 208, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(52, Short.MAX_VALUE))
		);
		
	    lstRectangle.setModel(dlm);
		scrollPane.setViewportView(lstRectangle);
		plnCenter.setLayout(gl_plnCenter);
	}
}
