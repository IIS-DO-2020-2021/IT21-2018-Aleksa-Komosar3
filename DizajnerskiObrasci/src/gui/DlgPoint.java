package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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
public class DlgPoint extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private final JPanel contentPanel2 = new JPanel();
	private JTextField txtX;
	private JTextField txtY;
	private boolean isOK;
	private Color color=Color.BLACK;
	private Color picked=Color.BLACK;
	private boolean colorChosen;
	
	public static void main(String[] args) {
		try {
			DlgPoint dialog = new DlgPoint();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public DlgPoint() {
		setBounds(100, 100, 250, 225);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 0, 5));
		getContentPane().add(contentPanel, BorderLayout.NORTH);
		contentPanel.setLayout(new GridLayout(0, 2, 0, 0));
		contentPanel.setPreferredSize(new Dimension(200, 100));
		
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
		contentPanel2.setBorder(new EmptyBorder(0, 5, 5, 5));
		getContentPane().add(contentPanel2, BorderLayout.CENTER);
		contentPanel2.setLayout(new BorderLayout(0, 0));
		contentPanel2.setPreferredSize(new Dimension(200, 50));
		{
			JButton btnColor = new JButton("Color");
			contentPanel2.add(btnColor);
			btnColor.setFont(new Font("Tahoma", Font.PLAIN, 15));
			btnColor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					color = JColorChooser.showDialog(null, "Choose color",picked);
					colorChosen = true;
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
				getRootPane().setDefaultButton(okButton);
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (txtX.getText().isEmpty() || txtY.getText().isEmpty()) {
							isOK = false;
							setVisible(true);
							JOptionPane.showMessageDialog(null, "All fields are required!","Error", JOptionPane.WARNING_MESSAGE);
						} else {
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
