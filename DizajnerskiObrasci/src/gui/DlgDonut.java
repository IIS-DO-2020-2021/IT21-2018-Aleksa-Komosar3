package gui;

import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class DlgDonut extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtX;
	private JTextField txtY;
	private JTextField txtRadius;
	private JTextField txtInnerRadius;
	private Color color=Color.BLACK, innerC=Color.BLACK;
	private Color pc, innerPc;
	private boolean isOK;
	private boolean colorChosen=false, innerColorChosen=false;

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
		setBounds(100, 100, 300, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(0, 2, 0, 0));
		
		{
			JLabel lblXCoordinate = new JLabel("X coordinate:");
			lblXCoordinate.setHorizontalAlignment(SwingConstants.LEFT);
			contentPanel.add(lblXCoordinate);
			lblXCoordinate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		}
		{
			txtX = new JTextField();
			txtX.setTransferHandler(null);
			txtX.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					char c = e.getKeyChar();
					if (!((c >= '0') && (c <= '9') ||
							(c == KeyEvent.VK_BACK_SPACE) ||
							(c == KeyEvent.VK_DELETE))) {
						e.consume();
						getToolkit().beep();
					}
				}
			});
			contentPanel.add(txtX);
			txtX.setColumns(10);
		}
		{
			JLabel lblYCoordinate = new JLabel("Y coordinate:");
			contentPanel.add(lblYCoordinate);
			lblYCoordinate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		}
		{
			txtY = new JTextField();
			txtY.setTransferHandler(null);
			txtY.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					char c = e.getKeyChar();
					if (!((c >= '0') && (c <= '9') ||
							(c == KeyEvent.VK_BACK_SPACE) ||
							(c == KeyEvent.VK_DELETE))) {
						e.consume();
						getToolkit().beep();
					}
				}
			});
			contentPanel.add(txtY);
			txtY.setColumns(10);
		}
		{
			JLabel lblRadius = new JLabel("Radius:");
			contentPanel.add(lblRadius);
			lblRadius.setFont(new Font("Tahoma", Font.PLAIN, 15));
		}
		{
			txtRadius = new JTextField();
			txtRadius.setTransferHandler(null);
			txtRadius.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					char c = e.getKeyChar();
					if (!((c >= '0') && (c <= '9') ||
							(c == KeyEvent.VK_BACK_SPACE) ||
							(c == KeyEvent.VK_DELETE))) {
						e.consume();
						getToolkit().beep();
					}
				}
			});
			contentPanel.add(txtRadius);
			txtRadius.setColumns(10);
		}
		{
			JLabel lblInnerRadius = new JLabel("Inner radius:");
			contentPanel.add(lblInnerRadius);
			lblInnerRadius.setFont(new Font("Tahoma", Font.PLAIN, 15));
		}
		{
			txtInnerRadius = new JTextField();
			txtInnerRadius.setTransferHandler(null);
			txtInnerRadius.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					char c = e.getKeyChar();
					if (!((c >= '0') && (c <= '9') ||
							(c == KeyEvent.VK_BACK_SPACE) ||
							(c == KeyEvent.VK_DELETE))) {
						e.consume();
						getToolkit().beep();
					}
				}
			});
			contentPanel.add(txtInnerRadius);
			txtInnerRadius.setColumns(10);
		}
		{
			JButton btnColor = new JButton("Outline color");
			btnColor.setFont(new Font("Tahoma", Font.PLAIN, 15));
			contentPanel.add(btnColor);
			btnColor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					color = JColorChooser.showDialog(null, "Choose color",pc);
					colorChosen=true;
				}
			});
		}
		{
			JButton btnInnerColor = new JButton("Color of donut");
			btnInnerColor.setHorizontalAlignment(SwingConstants.LEADING);
			btnInnerColor.setFont(new Font("Tahoma", Font.PLAIN, 15));
			contentPanel.add(btnInnerColor);
			btnInnerColor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					innerC = JColorChooser.showDialog(null, "Choose color",innerPc);
					innerColorChosen=true;
				}
			});
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (txtX.getText().isEmpty() || txtY.getText().isEmpty() || txtRadius.getText().isEmpty() || txtInnerRadius.getText().isEmpty()) {
							isOK = false;
							setVisible(true);
							JOptionPane.showMessageDialog(null, "All fields are required!","Error", JOptionPane.WARNING_MESSAGE);
						} else if (Integer.parseInt(txtRadius.getText())==0 || Integer.parseInt(txtInnerRadius.getText())== 0) {
							JOptionPane.showMessageDialog(null, "Radius must be > 0!","Error", JOptionPane.WARNING_MESSAGE);
						} else if (Integer.parseInt(txtInnerRadius.getText()) > Integer.parseInt(txtRadius.getText())) {
							JOptionPane.showMessageDialog(null, "Inner radius must be smaller than radius!","Error", JOptionPane.WARNING_MESSAGE);
						}
						else {
							isOK = true;
							dispose();
						}
					}
				});
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
			}
		}
	}

	public JTextField getTxtX() {
		return txtX;
	}

	public void setTxtX(JTextField txtX) {
		this.txtX = txtX;
	}

	public JTextField getTxtY() {
		return txtY;
	}

	public void setTxtY(JTextField txtY) {
		this.txtY = txtY;
	}

	public JTextField getTxtRadius() {
		return txtRadius;
	}

	public void setTxtRadius(JTextField txtRadius) {
		this.txtRadius = txtRadius;
	}

	public JTextField getTxtInnerRadius() {
		return txtInnerRadius;
	}

	public void setTxtInnerRadius(JTextField txtInnerRadius) {
		this.txtInnerRadius = txtInnerRadius;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color c) {
		this.color = c;
	}

	public Color getInnerColor() {
		return innerC;
	}

	public void setInnerColor(Color innerC) {
		this.innerC = innerC;
	}

	public Color getPc() {
		return pc;
	}

	public void setPc(Color pc) {
		this.pc = pc;
	}

	public Color getInnerPc() {
		return innerPc;
	}

	public void setInnerPc(Color innerPc) {
		this.innerPc = innerPc;
	}

	public boolean isOK() {
		return isOK;
	}

	public void setOK(boolean isOK) {
		this.isOK = isOK;
	}

	public boolean isColorChosen() {
		return colorChosen;
	}

	public void setColorChosen(boolean colorChosen) {
		this.colorChosen = colorChosen;
	}

	public boolean isInnerColorChosen() {
		return innerColorChosen;
	}

	public void setInnerColorChosen(boolean innerColorChosen) {
		this.innerColorChosen = innerColorChosen;
	}

}
