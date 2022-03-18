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
	private DrawingView view = new DrawingView();
	private DrawingController controller;

	private JToggleButton tglbtnPoint = new JToggleButton("Point");
	private JToggleButton tglbtnLine = new JToggleButton("Line");
	private JToggleButton tglbtnRectangle = new JToggleButton("Rectangle");
	private JToggleButton tglbtnCircle = new JToggleButton("Circle");
	private JToggleButton tglbtnDonut = new JToggleButton("Donut");
	private JToggleButton tglbtnSelection = new JToggleButton("Selection");
	private JToggleButton tglbtnHexagon = new JToggleButton("Hexagon");
	private JButton btnUndo = new JButton("Undo");
	private JButton btnRedo = new JButton("Redo");

	public DrawingFrame() {
		view.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.mouseClicked(e);
			}
		});
		getContentPane().add(view, BorderLayout.CENTER);
		view.setLayout(new BorderLayout(0, 0));
		view.setBorder(new EmptyBorder(5, 5, 5, 5));
		view.setBackground(Color.WHITE);
		

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 700);
		setResizable(false);
		setTitle("Aleksa Komosar");

		JPanel pnlNorth = new JPanel();
		getContentPane().add(pnlNorth, BorderLayout.NORTH);
		JPanel pnlSouth = new JPanel();
		getContentPane().add(pnlSouth, BorderLayout.SOUTH);


		ButtonGroup btnGroup = new ButtonGroup();
		

		JButton btnModification = new JButton("Modification");
		btnModification.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.modify();
			}
		});
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.delete();
			}
		});
		
		btnUndo.setEnabled(false);
		btnUndo.setBackground(Color.white);
		btnUndo.setForeground(Color.BLACK);
		btnUndo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.undo();
			}
		});
		btnRedo.setEnabled(false);
		btnRedo.setBackground(Color.white);
		btnRedo.setForeground(Color.BLACK);
		btnRedo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.redo();
			}
		});

		pnlNorth.add(tglbtnPoint);
		tglbtnPoint.setBackground(Color.LIGHT_GRAY);
		pnlNorth.add(tglbtnLine);
		pnlNorth.add(tglbtnRectangle);
		pnlNorth.add(tglbtnCircle);
		pnlNorth.add(tglbtnDonut);
		pnlNorth.add(tglbtnHexagon);
		
		
		pnlSouth.add(tglbtnSelection);
		pnlSouth.add(btnModification);
		pnlSouth.add(btnDelete);
		
		pnlSouth.add(btnUndo);
		pnlSouth.add(btnRedo);

		btnGroup.add(tglbtnPoint);
		btnGroup.add(tglbtnLine);
		btnGroup.add(tglbtnRectangle);
		btnGroup.add(tglbtnCircle);
		btnGroup.add(tglbtnDonut);
		btnGroup.add(tglbtnHexagon);
		btnGroup.add(tglbtnSelection);
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
	
	public JToggleButton getTglbtnHexagon() {
		return tglbtnHexagon;
	}


	public void setTglbtnHexagon(JToggleButton tglbtnHexagon) {
		this.tglbtnHexagon = tglbtnHexagon;
	}

	public JToggleButton getTglbtnSelection() {
		return tglbtnSelection;
	}

	public void setTglbtnSelection(JToggleButton tglbtnSelection) {
		this.tglbtnSelection = tglbtnSelection;
	}
	
	public JButton getBtnUndo() {
		return btnUndo;
	}


	public void setBtnUndo(JButton btnUndo) {
		this.btnUndo = btnUndo;
	}


	public JButton getBtnRedo() {
		return btnRedo;
	}


	public void setBtnRedo(JButton btnRedo) {
		this.btnRedo = btnRedo;
	}	

	
}
