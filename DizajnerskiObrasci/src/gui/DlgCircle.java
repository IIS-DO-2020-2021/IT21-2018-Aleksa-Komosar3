package gui;

import java.awt.BorderLayout;
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
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor @Getter @Setter @ToString
public class DlgCircle extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtX;
	private JTextField txtY;
	private JTextField txtRadius;
	private Color color=Color.BLACK;
	private Color innerColor=Color.BLACK;
	private Color picked;
	private Color innerPickedColor;
	private boolean isOK;
	private boolean innerColorChosen=false;
	private boolean colorChosen=false;


	public static void main(String[] args) {
		try {
			DlgCircle dialog = new DlgCircle();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public DlgCircle() {
		setBounds(100, 100, 300, 275);
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
			JButton btnColor = new JButton("Color");
			btnColor.setFont(new Font("Tahoma", Font.PLAIN, 15));
			contentPanel.add(btnColor);
			btnColor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					color = JColorChooser.showDialog(null, "Choose color",picked);
					colorChosen=true;
				}
			});
		}
		{
			JButton btnInnerColor = new JButton("Inner Color");
			btnInnerColor.setFont(new Font("Tahoma", Font.PLAIN, 15));
			contentPanel.add(btnInnerColor);
			btnInnerColor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					innerColor = JColorChooser.showDialog(null, "Choose color",innerPickedColor);
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
				okButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (txtX.getText().isEmpty() || txtY.getText().isEmpty() || txtRadius.getText().isEmpty()) {
							isOK = false;
							setVisible(true);
							JOptionPane.showMessageDialog(null, "All fields are required!","Error", JOptionPane.WARNING_MESSAGE);
						} else if (Integer.parseInt(txtRadius.getText())==0) {
							JOptionPane.showMessageDialog(null, "Radius must be > 0!","Error", JOptionPane.WARNING_MESSAGE);
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
				cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
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
	
	
}
