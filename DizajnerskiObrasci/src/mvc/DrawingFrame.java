package mvc;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DrawingFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	//ne treba kreirati objekte, vec samo referenceriati, kad se kreiraju objekti nastane onaj haos
	//kreira se objakt za frame jer je top level...
	private DrawingView view= new DrawingView();
	private DrawingController controller;
	
	private JToggleButton tglbtnPoint = new JToggleButton("Point");
	private JToggleButton tglbtnLine = new JToggleButton("Line");
	private JToggleButton tglbtnRectangle = new JToggleButton("Rectangle");
	private JToggleButton tglbtnCircle = new JToggleButton("Circle");
	private JToggleButton tglbtnDonut = new JToggleButton("Donut");
	private JToggleButton tglbtnSelection = new JToggleButton("Selection");
	private JButton btnModification = new JButton("Modification");
	private JButton btnDelete = new JButton("Delete");
	private JButton btnColor = new JButton("");
	private JButton btnInnerColor = new JButton("");
	private final JTextArea txtAreaLog = new JTextArea();
	private final JScrollPane scrollPane = new JScrollPane(txtAreaLog);
	
	public DrawingFrame() {
		view.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				controller.mouseClicked(arg0);
			}
		});
		
		getContentPane().add(view, BorderLayout.CENTER);
		
		view.setBorder(new EmptyBorder(5, 5, 5, 5));
		view.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 100, 1000, 700);
		setTitle("Aleksa Komosar");
		
		JPanel pnlNorth = new JPanel();
		getContentPane().add(pnlNorth, BorderLayout.NORTH);
		JPanel pnlEast = new JPanel();
		getContentPane().add(pnlEast, BorderLayout.EAST);
		JPanel pnlWest = new JPanel();
		getContentPane().add(pnlWest, BorderLayout.WEST);
		JPanel pnlSouth = new JPanel();
		getContentPane().add(pnlSouth, BorderLayout.SOUTH);
		
		ButtonGroup btnGroup = new ButtonGroup();
		
		btnModification.setEnabled(true);
		btnModification.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.modify();
			}
		});
		
		btnDelete.setEnabled(true);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.delete();
			}
		});
		pnlNorth.setLayout(new GridLayout(0, 7, 5, 0));
		
		JLabel label = new JLabel("");
		JLabel label_1 = new JLabel("");
		JLabel label_2 = new JLabel("");
		JLabel label_3 = new JLabel("");
		JLabel label_4 = new JLabel("");
		JLabel label_5 = new JLabel("");
		JLabel label_6 = new JLabel("");
		JLabel innerColor = new JLabel("InnerColor");
		innerColor.setHorizontalAlignment(SwingConstants.RIGHT);
		innerColor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		JLabel color = new JLabel("Color");
		color.setFont(new Font("Tahoma", Font.PLAIN, 14));
		color.setHorizontalAlignment(SwingConstants.RIGHT);
		JLabel label_9 = new JLabel("");
		
		pnlNorth.add(label_6);
		pnlNorth.add(tglbtnPoint);
		pnlNorth.add(tglbtnLine);
		pnlNorth.add(tglbtnRectangle);
		pnlNorth.add(color);
		pnlNorth.add(btnColor);
		pnlNorth.add(label_9);
		pnlNorth.add(tglbtnCircle);
		pnlNorth.add(tglbtnDonut);	
		pnlNorth.add(innerColor);
		pnlNorth.add(btnInnerColor);
		
		pnlEast.setLayout(new GridLayout(0, 1, 0, 0));
		pnlEast.add(tglbtnSelection);
		pnlEast.add(btnModification);
		pnlEast.add(btnDelete);

		
		pnlWest.setLayout(new GridLayout(0, 1, 0, 0));
		pnlWest.add(label);
		pnlWest.add(label_1);

		pnlWest.add(label_2);

		pnlWest.add(label_3);
		pnlWest.add(label_4);
		pnlWest.add(label_5);
		
		btnGroup.add(tglbtnPoint);
		btnGroup.add(tglbtnLine);
		btnGroup.add(tglbtnRectangle);
		btnGroup.add(tglbtnCircle);
		btnGroup.add(tglbtnDonut);
		btnGroup.add(tglbtnSelection);
		
		JPanel pnlTop = new JPanel();
		JPanel pnlBottom = new JPanel();
		pnlSouth.add(pnlTop);
		pnlSouth.add(pnlBottom);
			
		
		pnlBottom.add(scrollPane);
		
		btnColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new JColorChooser();
				btnColor.setBackground(JColorChooser.showDialog(null, "Choose color", null));
			}
		});
		btnColor.setBackground(Color.BLACK);
		
		btnInnerColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new JColorChooser();
				btnInnerColor.setBackground(JColorChooser.showDialog(null, "Choose color", null));
			}
		});
		btnInnerColor.setBackground(Color.WHITE);
		
	
		
	}

	public DrawingView getView() {
		return view;
	}

	public void setController(DrawingController controller) {
		this.controller = controller;
	}

	public JToggleButton getTglbtnPoint() {
		return tglbtnPoint;
	}

	public void setTglbtnPoint(JToggleButton tglbtnPoint) {
		this.tglbtnPoint = tglbtnPoint;
	}

	public JToggleButton getTglbtnLine() {
		return tglbtnLine;
	}

	public void setTglbtnLine(JToggleButton tglbtnLine) {
		this.tglbtnLine = tglbtnLine;
	}

	public JToggleButton getTglbtnRectangle() {
		return tglbtnRectangle;
	}

	public void setTglbtnRectangle(JToggleButton tglbtnRectangle) {
		this.tglbtnRectangle = tglbtnRectangle;
	}

	public JToggleButton getTglbtnCircle() {
		return tglbtnCircle;
	}

	public void setTglbtnCircle(JToggleButton tglbtnCircle) {
		this.tglbtnCircle = tglbtnCircle;
	}

	public JToggleButton getTglbtnDonut() {
		return tglbtnDonut;
	}

	public void setTglbtnDonut(JToggleButton tglbtnDonut) {
		this.tglbtnDonut = tglbtnDonut;
	}


	public void setTglbtnSelection(JToggleButton tglbtnSelection) {
		this.tglbtnSelection = tglbtnSelection;
	}

	public JButton getBtnColor() {
		return btnColor;
	}


	public void setBtnColor(JButton btnColor) {
		this.btnColor = btnColor;
	}


	public JButton getBtnInnerColor() {
		return btnInnerColor;
	}


	public void setBtnInnerColor(JButton btnInnerColor) {
		this.btnInnerColor = btnInnerColor;
	}


	public JButton getBtnModification() {
		return btnModification;
	}


	public void setBtnModification(JButton btnModification) {
		this.btnModification = btnModification;
	}


	public JButton getBtnDelete() {
		return btnDelete;
	}
	public JToggleButton getTglbtnSelection() {
		return tglbtnSelection;
	}


	public void setBtnDelete(JButton btnDelete) {
		this.btnDelete = btnDelete;
	}
	
	
}
