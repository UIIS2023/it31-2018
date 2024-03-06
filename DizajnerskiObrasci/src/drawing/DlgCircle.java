package drawing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import geometry.Circle;
import geometry.Point;

import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DlgCircle extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtRadius;
	private Circle circle;
	private boolean isOk; //variable indicating that everything went allright
	private JTextField txtY;
	private JTextField txtX;
	private JPanel pnlOuterColor = new JPanel();
	
	private JPanel pnlInnerColor = new JPanel();


	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgCircle dialog = new DlgCircle();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgCircle() {
		setBounds(100, 100, 267, 240);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setTitle("Circle");
		setResizable(false);
		setModal(true);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			txtRadius = new JTextField();
			txtRadius.setColumns(10);
		}
		
		JLabel lblRadius = new JLabel("Radius:");
		JLabel lblY = new JLabel("Y:");
		txtY = new JTextField();
		txtY.setColumns(10);
		JLabel lblX = new JLabel("X:");
		txtX = new JTextField();
		txtX.setColumns(10);
		
		JButton btnOutercolor = new JButton("OuterColor");
		btnOutercolor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Color color = JColorChooser.showDialog(null, "Choose outer color", pnlOuterColor.getBackground());
				if(color !=null){
					pnlOuterColor.setBackground(color);
				}
				
			}
		});
		
		JButton btnInnerColor = new JButton("Inner Color");
		btnInnerColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Color color = JColorChooser.showDialog(null, "Choose inner color", pnlInnerColor.getBackground());
				if(color !=null){
					pnlInnerColor.setBackground(color);
				}
			}
		});
		
		
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
					.addGap(29)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblRadius)
						.addComponent(lblY)
						.addComponent(lblX))
					.addPreferredGap(ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(txtX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtRadius, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(24))
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(btnOutercolor)
						.addComponent(btnInnerColor))
					.addGap(47)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(pnlInnerColor, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(pnlOuterColor, GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE))
					.addContainerGap(69, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblX)
						.addComponent(txtX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblY)
						.addComponent(txtY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(txtRadius, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblRadius))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(pnlOuterColor, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnOutercolor, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(pnlInnerColor, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnInnerColor, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
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
						if(txtRadius.getText().trim().isEmpty()||txtRadius.getText().trim().isEmpty()){
							isOk=false;
							getToolkit().beep();
							JOptionPane.showMessageDialog(null, 
									"Everything must be filled",
									"ERROR",
									JOptionPane.ERROR_MESSAGE);
						}
						else{
							try{
								/*checking if every number entered is positive*/
								if(Integer.parseInt(txtX.getText())<0||Integer.parseInt(txtY.getText())<0||Integer.parseInt(txtRadius.getText())<=0){
									getToolkit().beep();
									JOptionPane.showMessageDialog(null, 
											"Numbers must be positive",
											"ERROR",
											JOptionPane.ERROR_MESSAGE);
								}else{
									/*Creating a Circle object*/
									circle = new Circle(new Point(Integer.parseInt(txtX.getText()),Integer.parseInt(txtY.getText())),Integer.parseInt(txtRadius.getText()),false,pnlOuterColor.getBackground(),pnlInnerColor.getBackground());
									isOk=true;
									dispose();
								}
							/*catches exception if someone enters something that isnt a number*/
							}catch(IllegalArgumentException e1){
								getToolkit().beep();
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
						isOk=false;
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	public JTextField getTxtY() {
		return txtY;
	}

	public void setTxtY(String txtY) {
		this.txtY.setText(txtY);;
	}

	public JTextField getTxtX() {
		return txtX;
	}

	public void setTxtX(String txtX) {
		this.txtX.setText(txtX);;
	}

	public Circle getCircle() {
		return circle;
	}

	public void setCircle(Circle circle) {
		this.circle = circle;
	}
	
	public JTextField getTxtRadius() {
		return txtRadius;
	}

	public void setTxtRadius(String txtRadius) {
		this.txtRadius.setText(txtRadius);
	}
	
	

	public JPanel getPnlOuterColor() {
		return pnlOuterColor;
	}

	public JPanel getPnlInnerColor() {
		return pnlInnerColor;
	}

	public boolean isOk() {
		return isOk;
	}
}
