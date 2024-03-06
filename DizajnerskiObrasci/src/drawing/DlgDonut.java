package drawing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import geometry.Donut;
import geometry.Point;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DlgDonut extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtOuterRadius;
	private JTextField txtInnerRadius;
	private Donut donut;
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
			DlgDonut dialog = new DlgDonut();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgDonut() {
		setBounds(100, 100, 318, 282);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		setTitle("Donut");
		setResizable(false);
		setModal(true);
		
		txtOuterRadius = new JTextField();
		txtOuterRadius.setColumns(10);
		
		JLabel lblOuterRadius = new JLabel("Outer Radius:");
		
		txtInnerRadius = new JTextField();
		txtInnerRadius.setColumns(10);
		
		JLabel lblInnerRadius = new JLabel("Inner Radius:");
		
		JLabel lblY = new JLabel("Y:");
		
		txtY = new JTextField();
		txtY.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("X:");
		
		txtX = new JTextField();
		txtX.setColumns(10);
		
		JButton btnOuterColor = new JButton("Outer Color");
		btnOuterColor.addActionListener(new ActionListener() {
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
			gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(btnInnerColor)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblInnerRadius)
								.addComponent(lblOuterRadius)
								.addComponent(lblY)
								.addComponent(lblNewLabel)
								.addComponent(btnOuterColor))
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addGap(36)
									.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(txtX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(txtY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(txtOuterRadius, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(txtInnerRadius, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addGap(46)
									.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(pnlInnerColor, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(pnlOuterColor, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE))))))
					.addContainerGap(41, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(txtX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblY)
						.addComponent(txtY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblOuterRadius)
						.addComponent(txtOuterRadius, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblInnerRadius)
						.addComponent(txtInnerRadius, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(pnlOuterColor, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnOuterColor, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(pnlInnerColor, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnInnerColor, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
						if(txtOuterRadius.getText().trim().isEmpty()||txtInnerRadius.getText().trim().isEmpty()){
							isOk=false;
							getToolkit().beep();
							JOptionPane.showMessageDialog(null, 
									"Everything must be filled",
									"ERROR",
									JOptionPane.ERROR_MESSAGE);
							
						}else{
							try{
								/*checking if every number entered is positive*/
								if(Integer.parseInt(txtX.getText())<0||Integer.parseInt(txtY.getText())<0||Integer.parseInt(txtOuterRadius.getText())<0||Integer.parseInt(txtInnerRadius.getText())<0){
									getToolkit().beep();
									JOptionPane.showMessageDialog(null, 
											"Numbers must be posiive",
											"ERROR",
											JOptionPane.ERROR_MESSAGE);
								/*Checking if the inner radius is smaller then the outer radius*/
								}else if(Integer.parseInt(txtInnerRadius.getText())>Integer.parseInt(txtOuterRadius.getText())){
									getToolkit().beep();
									JOptionPane.showMessageDialog(null, 
											"Inner radius needs to be smaller than the Outer radius",
											"ERROR",
											JOptionPane.ERROR_MESSAGE);
									
								}else{
								/*Creating a DOnut object*/
								donut=new Donut(new Point(Integer.parseInt(txtX.getText()),Integer.parseInt(txtY.getText())),Integer.parseInt(txtOuterRadius.getText()),Integer.parseInt(txtInnerRadius.getText()),false,pnlOuterColor.getBackground(),pnlInnerColor.getBackground());
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

	public JTextField getTxtOuterRadius() {
		return txtOuterRadius;
	}

	public void setTxtOuterRadius(String txtOuterRadius) {
		this.txtOuterRadius.setText(txtOuterRadius);;
	}

	public JTextField getTxtInnerRadius() {
		return txtInnerRadius;
	}

	public void setTxtInnerRadius(String txtInnerRadius) {
		this.txtInnerRadius.setText(txtInnerRadius);;
	}

	public Donut getDonut() {
		return donut;
	}

	public void setDonut(Donut donut) {
		this.donut = donut;
	}

	public boolean isOk() {
		return isOk;
	}

	public JPanel getPnlOuterColor() {
		return pnlOuterColor;
	}

	public JPanel getPnlInnerColor() {
		return pnlInnerColor;
	}
	
	
}
