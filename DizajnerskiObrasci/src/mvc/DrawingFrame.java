package mvc;

import javax.swing.BoxLayout;
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
	private JButton btnModification = new JButton("Modification");
	private JButton btnDelete = new JButton("Delete");
	private JButton btnUndo = new JButton("Undo");
	private JButton btnRedo = new JButton("Redo");
	private JButton btnColor = new JButton("");
	private JButton btnInnerColor = new JButton("");
	//private final JLabel label_9_1 = new JLabel("");
	private final JLabel label_10 = new JLabel("");
	private final JLabel label_11 = new JLabel("");
	private final JLabel label_12 = new JLabel("");
	private final JLabel label_13 = new JLabel("");
	private final JLabel label_14 = new JLabel("");
	private final JLabel label_15 = new JLabel("");
	private final JLabel label_16 = new JLabel("");
	private final JLabel label_17 = new JLabel("");
	private final JLabel label_18 = new JLabel("");
	
	private final JTextArea txtAreaLog = new JTextArea();
	private final JScrollPane scrollPane = new JScrollPane(txtAreaLog);
	
	private JButton btnToFront = new JButton("ToFront");
	private JButton btnToBack = new JButton("ToBack");
	private JButton btnBringToFront = new JButton("BringToFront");
	private JButton btnBringToBack = new JButton("BringToBack");
	
	private final JButton btnSaveDrawing = new JButton("SaveDrawing");
	private final JButton btnLoadDrawing = new JButton("LoadDrawing");
	private final JButton btnSaveLog = new JButton("SaveLog");
	private final JButton btnLoadLog = new JButton("LoadLog");
	private final JButton btnNext = new JButton("Next");

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
		
		view.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		view.setBackground(Color.WHITE);
		

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 300, 1000, 700);
		setResizable(false);
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
		

		//JButton btnModification = new JButton("Modification");
		btnModification.setEnabled(false);
		btnModification.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.modify();
			}
		});
		//JButton btnDelete = new JButton("Delete");
		btnDelete.setEnabled(false);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.delete();
			}
		});
		
		pnlNorth.setLayout(new GridLayout(2, 6, 5, 0));

		JLabel label = new JLabel("");
		JLabel label_1 = new JLabel("");
		JLabel label_2 = new JLabel("");
		JLabel label_3 = new JLabel("");
		JLabel label_4 = new JLabel("");
		JLabel label_5 = new JLabel("");
		JLabel label_6 = new JLabel("");
		JLabel label_7 = new JLabel("");
		JLabel label_8 = new JLabel("");
		JLabel label_9 = new JLabel("");
		
		JLabel innerColor = new JLabel("Color");
		innerColor.setHorizontalAlignment(SwingConstants.RIGHT);
		innerColor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		JLabel color = new JLabel("InnerColor");
		color.setFont(new Font("Tahoma", Font.PLAIN, 14));
		color.setHorizontalAlignment(SwingConstants.RIGHT);
		
				pnlNorth.add(tglbtnPoint);
				tglbtnPoint.setBackground(Color.LIGHT_GRAY);
				
						btnGroup.add(tglbtnPoint);
		pnlNorth.add(tglbtnLine);
		btnGroup.add(tglbtnLine);
		pnlNorth.add(tglbtnRectangle);
		btnGroup.add(tglbtnRectangle);
		pnlNorth.add(tglbtnDonut);
		btnGroup.add(tglbtnDonut);
		
		
		pnlNorth.add(label_6);
		pnlNorth.add(label_8);
		pnlNorth.add(label_9);
		pnlNorth.add(label_7);
		pnlNorth.add(tglbtnHexagon);
		btnGroup.add(tglbtnHexagon);
		pnlNorth.add(tglbtnCircle);
		btnGroup.add(tglbtnCircle);
		pnlNorth.add(color);
		pnlNorth.add(btnColor);
		
		btnColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new JColorChooser();
				btnColor.setBackground(JColorChooser.showDialog(null, "Choose color", null));
			}
		});
		btnColor.setBackground(Color.BLACK);
		pnlNorth.add(innerColor);
		
		
		/*pnlSouth.add(tglbtnSelection);
		pnlSouth.add(btnModification);
		pnlSouth.add(btnDelete);*/
		pnlWest.setLayout(new GridLayout(0, 1, 0, 0));
		pnlWest.add(label);
		pnlWest.add(label_1);
		pnlWest.add(btnToFront);
		
				btnToFront.setEnabled(false);
				btnToFront.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						controller.toFront();
					}
				});
		pnlWest.add(btnUndo);
		pnlWest.add(btnRedo);
		pnlWest.add(btnToBack);
		
				btnToBack.setEnabled(false);
				btnToBack.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						controller.toBack();
					}
				});
		pnlWest.add(label_2);
		pnlWest.add(label_3);
		pnlWest.add(btnBringToFront);
		pnlWest.add(btnBringToBack);
		pnlWest.add(label_4);
		pnlWest.add(label_5);
		
		pnlEast.setLayout(new GridLayout(0, 1, 0, 0));
		pnlEast.add(label_10);
		pnlEast.add(label_11);
		pnlEast.add(label_12);
		pnlEast.add(label_13);
		pnlEast.add(tglbtnSelection);
		pnlEast.add(btnModification);
		pnlEast.add(btnDelete);
		pnlEast.add(label_14);	
		pnlEast.add(label_15);
		pnlEast.add(label_16);
		pnlEast.add(label_17);
		pnlEast.add(label_18);
		
		pnlWest.add(btnUndo);
		pnlWest.add(btnRedo);
		btnGroup.add(tglbtnSelection);
		
		pnlSouth.setLayout(new BoxLayout(pnlSouth, BoxLayout.Y_AXIS));
		
		//pnlSouth.add(scrollPane);
		pnlNorth.add(btnInnerColor);
		
				btnInnerColor.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						new JColorChooser();
						btnInnerColor.setBackground(JColorChooser.showDialog(null, "Choose color", null));
					}
				});
				btnInnerColor.setBackground(Color.WHITE);
		
		
		btnUndo.setEnabled(false);
		btnUndo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.undo();
			}
		});


		btnRedo.setEnabled(false);
		btnRedo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.redo();
			}
		});

		btnBringToFront.setEnabled(false);
		btnBringToFront.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.bringToFront();
			}
		});

		btnBringToBack.setEnabled(false);
		btnBringToBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.bringToBack();
			}
		});
		
		btnSaveDrawing.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.saveDrawing();
			}
		});


		btnLoadDrawing.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.loadDrawing();
			}
		});

		btnSaveLog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.saveLog();
			}
		});

		btnLoadLog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.loadLog();
			}
		});
		
		txtAreaLog.setEnabled(false);
		txtAreaLog.setEditable(false);
		txtAreaLog.setTabSize(10);
		txtAreaLog.setColumns(50);
		txtAreaLog.setRows(5);
		txtAreaLog.setDisabledTextColor(Color.BLACK);

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
	
	public JButton getBtnToFront() {
		return btnToFront;
	}


	public void setBtnToFront(JButton btnToFront) {
		this.btnToFront = btnToFront;
	}


	public JButton getBtnToBack() {
		return btnToBack;
	}


	public void setBtnToBack(JButton btnToBack) {
		this.btnToBack = btnToBack;
	}

	public JButton getBtnBringToFront() {
		return btnBringToFront;
	}

	public void setBtnBringToFront(JButton btnBringToFront) {
		this.btnBringToFront = btnBringToFront;
	}

	public JButton getBtnBringToBack() {
		return btnBringToBack;
	}

	public void setBtnBringToBack(JButton btnBringToBack) {
		this.btnBringToBack = btnBringToBack;
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

	public void setBtnDelete(JButton btnDelete) {
		this.btnDelete = btnDelete;
	}
	
	public JTextArea getTxtAreaLog() {
		return txtAreaLog;
	}
	public JButton getBtnNext() {
		return btnNext;
	}	


}
