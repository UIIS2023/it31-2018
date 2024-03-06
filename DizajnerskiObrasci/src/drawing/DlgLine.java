package drawing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;


import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import geometry.Line;
import geometry.Point;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DlgLine extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtStartX;
	private JTextField txtStartY;
	private JTextField txtEndX;
	private JTextField txtEndY;
	private Line line;
	private boolean isOk; //variable indicating that everything went allright

	private JPanel pnlColor = new JPanel();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgLine dialog = new DlgLine();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgLine() {
		setBounds(100, 100, 274, 246);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setTitle("Line");
		setResizable(false);
		setModal(true);
		
		JLabel lblStartX = new JLabel("Start X:");
		
		JLabel lblStartY = new JLabel("Start Y:");
		
		JLabel lblEndX = new JLabel("End X:");
		
		JLabel lblEndY = new JLabel("End Y:");
		
		txtStartX = new JTextField();
		txtStartX.setColumns(10);
		
		txtStartY = new JTextField();
		txtStartY.setColumns(10);
		
		txtEndX = new JTextField();
		txtEndX.setColumns(10);
		
		txtEndY = new JTextField();
		txtEndY.setColumns(10);
		
		JButton btnColor = new JButton("Color");
		btnColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Color color = JColorChooser.showDialog(null, "Choose color", pnlColor.getBackground());
				if(color !=null){
					pnlColor.setBackground(color);
				}
			}
		});
		
		
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblStartX)
						.addComponent(lblStartY)
						.addComponent(lblEndX)
						.addComponent(lblEndY)
						.addComponent(btnColor))
					.addGap(38)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(txtEndY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtEndX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtStartY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtStartX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(pnlColor, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(29, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblStartX)
						.addComponent(txtStartX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblStartY)
						.addComponent(txtStartY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEndX)
						.addComponent(txtEndX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEndY)
						.addComponent(txtEndY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(pnlColor, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnColor, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						/*checking if all the textboxes are filled*/
						if(txtStartX.getText().trim().isEmpty()||txtStartY.getText().trim().isEmpty()||txtEndX.getText().trim().isEmpty()||txtEndY.getText().trim().isEmpty()){
							isOk=false;
							setVisible(true);
							getToolkit().beep();
							JOptionPane.showMessageDialog(null, 
									"Everything must be filled",
									"ERROR",
									JOptionPane.ERROR_MESSAGE);
							
						}else{
							try{
								/*checking if every number entered is positive*/
								if(Integer.parseInt(txtStartX.getText())<0||Integer.parseInt(txtStartY.getText())<0||Integer.parseInt(txtEndX.getText())<0||Integer.parseInt(txtEndY.getText())<0){
									getToolkit().beep();
									JOptionPane.showMessageDialog(null, 
											"Numbers must be positive",
											"ERROR",
											JOptionPane.ERROR_MESSAGE);
								}else{
									/*creating a line object*/
									line=new Line(new Point(Integer.parseInt(txtStartX.getText()),Integer.parseInt(txtStartY.getText())),new Point(Integer.parseInt(txtEndX.getText()),Integer.parseInt(txtEndY.getText())),false,pnlColor.getBackground());
									isOk=true;
									dispose();
								}
							/*catches exception if someone enters something that isnt a number*/
							}catch(IllegalArgumentException e1){
								JOptionPane.showMessageDialog(null, 
										"Wrong data type",
										"ERROR",
										JOptionPane.ERROR_MESSAGE);
							}
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	public JTextField getTxtStartX() {
		return txtStartX;
	}

	public void setTxtStartX(String txtStartX) {
		this.txtStartX.setText(txtStartX);;
	}

	public JTextField getTxtStartY() {
		return txtStartY;
	}

	public void setTxtStartY(String txtStartY) {
		this.txtStartY.setText(txtStartY);;
	}

	public JTextField getTxtEndX() {
		return txtEndX;
	}

	public void setTxtEndX(String txtEndX) {
		this.txtEndX.setText(txtEndX);;
	}

	public JTextField getTxtEndY() {
		return txtEndY;
	}

	public void setTxtEndY(String txtEndY) {
		this.txtEndY.setText(txtEndY);;
	}

	public Line getLine() {
		return line;
	}

	public void setLine(Line line) {
		this.line = line;
	}

	public boolean isOk() {
		return isOk;
	}

	public JPanel getPnlColor() {
		return pnlColor;
	}
	
	
}
