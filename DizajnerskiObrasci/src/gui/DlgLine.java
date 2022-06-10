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
public class DlgLine extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private final JPanel contentPanel2 = new JPanel();
	private JTextField txtXs;
	private JTextField txtYs;
	private JTextField txtXe;
	private JTextField txtYe;
	private Color color= Color.BLACK;
	private boolean isOK;
	private Color picked= Color.BLACK;
	private boolean colorChosen;

	public static void main(String[] args) {
		try {
			DlgLine dialog = new DlgLine();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public DlgLine() {
		setBounds(100, 100, 300, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 0, 5));
		getContentPane().add(contentPanel, BorderLayout.NORTH);
		contentPanel.setLayout(new GridLayout(0, 2, 0, 0));
		contentPanel.setPreferredSize(new Dimension(200, 175));
		{
			JLabel lblXsCoordinate = new JLabel("X start coordinate:");
			lblXsCoordinate.setHorizontalAlignment(SwingConstants.LEFT);
			contentPanel.add(lblXsCoordinate);
			lblXsCoordinate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		}
		{
			txtXs = new JTextField();
			txtXs.setTransferHandler(null);
			txtXs.addKeyListener(new KeyAdapter() {
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
			contentPanel.add(txtXs);
			txtXs.setColumns(10);
		}
		{
			JLabel lblYsCoordinate = new JLabel("Y start coordinate:");
			contentPanel.add(lblYsCoordinate);
			lblYsCoordinate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		}
		{
			txtYs = new JTextField();
			txtYs.setTransferHandler(null);
			txtYs.addKeyListener(new KeyAdapter() {
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
			contentPanel.add(txtYs);
			txtYs.setColumns(10);
		}
		{
			JLabel lblXeCoordinate = new JLabel("X end coordinate:");
			lblXeCoordinate.setHorizontalAlignment(SwingConstants.LEFT);
			contentPanel.add(lblXeCoordinate);
			lblXeCoordinate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		}
		{
			txtXe = new JTextField();
			txtXe.setTransferHandler(null);
			txtXe.addKeyListener(new KeyAdapter() {
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
			contentPanel.add(txtXe);
			txtXe.setColumns(10);
		}
		{
			JLabel lblYeCoordinate = new JLabel("Y end coordinate:");
			contentPanel.add(lblYeCoordinate);
			lblYeCoordinate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		}
		{
			txtYe = new JTextField();
			txtYe.setTransferHandler(null);
			txtYe.addKeyListener(new KeyAdapter() {
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
			contentPanel.add(txtYe);
			txtYe.setColumns(10);
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
						if (txtXs.getText().isEmpty() || txtYs.getText().isEmpty() || txtXe.getText().isEmpty() || txtYe.getText().isEmpty()) {
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
