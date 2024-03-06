package drawing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import adapter.HexagonAdapter;
import geometry.Donut;
import geometry.Point;
import hexagon.Hexagon;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.LayoutStyle.ComponentPlacement;

public class DlgHexagon extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtRadius;
	private boolean isOk;
	private Hexagon hexagon;
	private HexagonAdapter hexagonAdapter;
	private JTextField txtY;
	private JTextField txtX;
	
	private JPanel pnlOuterColor = new JPanel();
	
	private JPanel pnlInnerColor = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgHexagon dialog = new DlgHexagon();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgHexagon() {
		setBounds(100, 100, 291, 271);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JLabel lblRadius = new JLabel("Radius:");
		
		setTitle("Hexagon");
		setResizable(false);
		setModal(true);
		
		txtRadius = new JTextField();
		txtRadius.setColumns(10);
		JLabel lblX = new JLabel("X:");
		JLabel lblY = new JLabel("Y:");
		txtY = new JTextField();
		txtY.setColumns(10);
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
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(32)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblRadius)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addGap(14)
									.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(lblX)
										.addComponent(lblY))))
							.addGap(29)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(txtY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtRadius, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(btnOuterColor)
								.addComponent(btnInnerColor))
							.addGap(42)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(pnlInnerColor, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(pnlOuterColor, GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE))))
					.addContainerGap(55, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblX)
						.addComponent(txtX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblY)
						.addComponent(txtY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRadius)
						.addComponent(txtRadius, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
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
						
						if(txtX.getText().trim().isEmpty() || txtY.getText().trim().isEmpty() || txtRadius.getText().trim().isEmpty()){
							isOk=false;
							getToolkit().beep();
							JOptionPane.showMessageDialog(null, 
									"Everything must be filled",
									"ERROR",
									JOptionPane.ERROR_MESSAGE);
							
						}else{
							try{
								/*checking if every number entered is positive*/
								if(Integer.parseInt(txtX.getText()) < 0 || Integer.parseInt(txtY.getText()) < 0 || Integer.parseInt(txtRadius.getText()) < 0){
									getToolkit().beep();
									JOptionPane.showMessageDialog(null, 
											"Everything must be positive",
											"ERROR",
											JOptionPane.ERROR_MESSAGE);
								/*Checking if the inner radius is smaller then the outer radius*/
								}else{
								/*Creating a Hexagon object*/
							
								hexagonAdapter = new HexagonAdapter(new Point(Integer.parseInt(txtX.getText()),Integer.parseInt(txtY.getText())),Integer.parseInt(txtRadius.getText()),false,pnlOuterColor.getBackground(),pnlInnerColor.getBackground());
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
					public void actionPerformed(ActionEvent arg0) {
						isOk = false;
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	public boolean isOk() {
		return isOk;
	}

	public HexagonAdapter getHexagon() {
		return hexagonAdapter;
	}

	public JTextField getTxtRadius() {
		return txtRadius;
	}

	public void setTxtRadius(String txtRadius) {
		this.txtRadius.setText(txtRadius);;
	}

	public JTextField getTxtY() {
		return txtY;
	}

	public void setTxtY(String txtY) {
		this.txtY.setText(txtY);
	}

	public JTextField getTxtX() {
		return txtX;
	}

	public void setTxtX(String txtX) {
		this.txtX.setText(txtX);;
	}

	public JPanel getPnlOuterColor() {
		return pnlOuterColor;
	}

	public JPanel getPnlInnerColor() {
		return pnlInnerColor;
	}

	
	
	
	
}
