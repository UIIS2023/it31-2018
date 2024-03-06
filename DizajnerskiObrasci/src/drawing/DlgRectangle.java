package drawing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import geometry.Point;
import geometry.Rectangle;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.SwingConstants;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DlgRectangle extends JDialog {

	private final JPanel contentPanel = new JPanel();
	

	private JTextField txtWidth;
	private JLabel lblWidth;
	private JTextField txtHeight;
	
	

	private Rectangle rectangle;
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
			DlgRectangle dialog = new DlgRectangle();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgRectangle() {
		setBounds(100, 100, 275, 286);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setTitle("Rectangle");
		setResizable(false);
		setModal(true);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			lblWidth = new JLabel("Width:");
			lblWidth.setHorizontalAlignment(SwingConstants.TRAILING);
		}
		{
			txtWidth = new JTextField();
			txtWidth.setColumns(10);
		}
		
		JLabel lblHeight = new JLabel("Height:");
		
		txtHeight = new JTextField();
		txtHeight.setColumns(10);
		
		JLabel lblY = new JLabel("Y:");
		
		txtY = new JTextField();
		txtY.setColumns(10);
		
		JLabel lblX = new JLabel("X:");
		
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
				.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
					.addContainerGap(40, Short.MAX_VALUE)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblHeight)
						.addComponent(lblWidth)
						.addComponent(lblY)
						.addComponent(lblX))
					.addGap(42)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(txtX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtWidth, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtHeight, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(20))
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(btnOuterColor)
						.addComponent(btnInnerColor))
					.addGap(36)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(pnlInnerColor, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(pnlOuterColor, GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE))
					.addContainerGap(86, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_contentPanel.createSequentialGroup()
					.addGap(25)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblX)
						.addComponent(txtX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblY)
						.addComponent(txtY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblWidth)
						.addComponent(txtWidth, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblHeight)
						.addComponent(txtHeight, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(pnlOuterColor, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnOuterColor, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
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
						if(txtHeight.getText().trim().isEmpty()||txtWidth.getText().trim().isEmpty()){
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
								if(Integer.parseInt(txtX.getText())<0||Integer.parseInt(txtY.getText())<0 ||Integer.parseInt(txtHeight.getText())<=0||Integer.parseInt(txtWidth.getText())<=0){
									isOk=false;
									getToolkit().beep();
									JOptionPane.showMessageDialog(null, 
											"Numbers must be positive",
											"ERROR",
											JOptionPane.ERROR_MESSAGE);
								}
								else{
									/*creating a Rectangle object*/
								rectangle = new Rectangle(new Point(Integer.parseInt(txtX.getText()),Integer.parseInt(txtY.getText())),Integer.parseInt(txtHeight.getText()),Integer.parseInt(txtWidth.getText()),false,pnlOuterColor.getBackground(),pnlInnerColor.getBackground());
								
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
						
					}}
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
		this.txtX.setText(txtX);
	}

	public Rectangle getRectangle() {
		return rectangle;
	}

	public void setRectangle(Rectangle rectangle) {
		this.rectangle = rectangle;
	}
	
	public JTextField getTxtWidth() {
		return txtWidth;
	}

	public void setTxtWidth(String txtWidth) {
		this.txtWidth.setText(txtWidth);
	}

	public JTextField getTxtHeight() {
		return txtHeight;
	}

	public void setTxtHeight(String txtHeight) {
		this.txtHeight.setText(txtHeight);
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
