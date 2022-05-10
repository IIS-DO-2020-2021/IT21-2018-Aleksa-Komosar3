package mvc;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import java.awt.Image;
import javax.swing.ImageIcon;
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
	
	private JButton btnToFront = new JButton("To front");
	private JButton btnToBack = new JButton("To back");
	private JButton btnBringToFront = new JButton("Bring to front");
	private JButton btnBringToBack = new JButton("Bring to back");

	private final JButton btnSaveDrawing = new JButton("Save drawing");
	private final JButton btnLoadDrawing = new JButton("Load drawing from PC");
	private final JButton btnSaveLog = new JButton("Save log");
	private final JButton btnLoadLog = new JButton("Load log");
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
		
		Image pointimg=new ImageIcon(this.getClass().getResource("/point.png")).getImage();
		tglbtnPoint.setIcon(new ImageIcon(pointimg));
		
		Image circleimg=new ImageIcon(this.getClass().getResource("/circle.png")).getImage();
		
		Image lineimg=new ImageIcon(this.getClass().getResource("/line.png")).getImage();
		tglbtnLine.setIcon(new ImageIcon(lineimg));
		
		Image rectangleimg=new ImageIcon(this.getClass().getResource("/rectangle.png")).getImage();
		tglbtnRectangle.setIcon(new ImageIcon(rectangleimg));
		
		Image donutimg=new ImageIcon(this.getClass().getResource("/donut.png")).getImage();
		
		Image heximg=new ImageIcon(this.getClass().getResource("/hexagon.png")).getImage();
		
		Image undoimg=new ImageIcon(this.getClass().getResource("/undo.png")).getImage();
		
		Image redoimg=new ImageIcon(this.getClass().getResource("/redo.png")).getImage();
		
		Image saveimg=new ImageIcon(this.getClass().getResource("/save.png")).getImage();
		btnSaveLog.setIcon(new ImageIcon(saveimg));
		btnSaveDrawing.setIcon(new ImageIcon(saveimg));
		
		Image loadimg=new ImageIcon(this.getClass().getResource("/load.png")).getImage();
		btnLoadLog.setIcon(new ImageIcon(loadimg));
		btnLoadDrawing.setIcon(new ImageIcon(loadimg));
		
		Image selectionimg=new ImageIcon(this.getClass().getResource("/selection.png")).getImage();
		tglbtnSelection.setIcon(new ImageIcon(selectionimg));
		
		Image modifyimg=new ImageIcon(this.getClass().getResource("/modify.png")).getImage();
		btnModification.setIcon(new ImageIcon(modifyimg));
		
		Image delimg=new ImageIcon(this.getClass().getResource("/delete.png")).getImage();
		btnDelete.setIcon(new ImageIcon(delimg));
		
		Image nextimg=new ImageIcon(this.getClass().getResource("/next.png")).getImage();
		btnNext.setIcon(new ImageIcon(nextimg));
		
		
		Image bbimg=new ImageIcon(this.getClass().getResource("/bf.png")).getImage();
		
		Image bfimg=new ImageIcon(this.getClass().getResource("/bb.png")).getImage();
		
		
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
		pnlSouth.setLayout(new BoxLayout(pnlSouth, BoxLayout.Y_AXIS));
		ButtonGroup btnGroup = new ButtonGroup();
		
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
		Image colorimg1=new ImageIcon(this.getClass().getResource("/color.png")).getImage();
		
				pnlNorth.add(tglbtnPoint);
				tglbtnPoint.setBackground(Color.LIGHT_GRAY);
				tglbtnLine.setBackground(Color.LIGHT_GRAY);
				tglbtnRectangle.setBackground(Color.LIGHT_GRAY);
				
				
		btnGroup.add(tglbtnPoint);
		pnlNorth.add(tglbtnLine);
		btnGroup.add(tglbtnLine);
		pnlNorth.add(tglbtnRectangle);
		btnGroup.add(tglbtnRectangle);
		tglbtnCircle.setIcon(new ImageIcon(circleimg));
		tglbtnCircle.setBackground(Color.LIGHT_GRAY);
		pnlNorth.add(tglbtnCircle);
		btnGroup.add(tglbtnCircle);
		
		
		pnlNorth.add(label_6);
		pnlNorth.add(btnUndo);
		btnUndo.setIcon(new ImageIcon(undoimg));
		btnUndo.setBackground(Color.WHITE);
		
		
		btnUndo.setEnabled(false);
		btnUndo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.undo();
			}
		});
		tglbtnDonut.setIcon(new ImageIcon(donutimg));
		tglbtnDonut.setBackground(Color.LIGHT_GRAY);
		pnlNorth.add(tglbtnDonut);
		btnGroup.add(tglbtnDonut);
		tglbtnHexagon.setIcon(new ImageIcon(heximg));
		tglbtnHexagon.setBackground(Color.LIGHT_GRAY);
		pnlNorth.add(tglbtnHexagon);
		btnGroup.add(tglbtnHexagon);
		pnlNorth.add(label_8);
		pnlNorth.add(label_9);
		pnlNorth.add(label_7);
		pnlNorth.add(btnRedo);
		btnRedo.setIcon(new ImageIcon(redoimg));
		btnRedo.setBackground(Color.WHITE);
		btnRedo.setEnabled(false);
		btnRedo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.redo();
			}
		});
		
		pnlWest.setLayout(new GridLayout(0, 1, 0, 0));
		pnlWest.add(label);
		btnToFront.setIcon(new ImageIcon(bbimg));
		pnlWest.add(btnToFront);
		
				btnToFront.setEnabled(false);
				btnToFront.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						controller.toFront();
					}
				});
				btnToFront.setBackground(Color.WHITE);
		btnToBack.setIcon(new ImageIcon(bfimg));
		pnlWest.add(btnToBack);
		
				btnToBack.setEnabled(false);
				btnToBack.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						controller.toBack();
					}
				});
				btnToBack.setBackground(Color.WHITE);
		pnlWest.add(label_1);
		btnBringToFront.setIcon(new ImageIcon(bbimg));
		pnlWest.add(btnBringToFront);
		btnBringToFront.setBackground(Color.WHITE);
		btnBringToFront.setEnabled(false);
		btnBringToFront.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.bringToFront();
			}
		});
		btnBringToBack.setIcon(new ImageIcon(bfimg));
		btnBringToBack.setBackground(Color.WHITE);
		pnlWest.add(btnBringToBack);
		btnBringToBack.setEnabled(false);
		btnBringToBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.bringToBack();
			}
		});
		pnlWest.add(label_2);
		pnlWest.add(label_3);
		pnlWest.add(label_4);
		pnlWest.add(label_5);
		JLabel color = new JLabel("Color");
		pnlWest.add(color);
		color.setIcon(new ImageIcon(colorimg1));
		color.setFont(new Font("Tahoma", Font.PLAIN, 14));
		color.setHorizontalAlignment(SwingConstants.LEFT);
		pnlWest.add(btnColor);
		
		btnColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new JColorChooser();
				btnColor.setBackground(JColorChooser.showDialog(null, "Choose color", null));
			}
		});
		btnColor.setBackground(Color.BLACK);
		
		JLabel innerColor = new JLabel("Inner Color");
		pnlWest.add(innerColor);
		innerColor.setIcon(new ImageIcon(colorimg1));
		innerColor.setHorizontalAlignment(SwingConstants.LEFT);
		innerColor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlWest.add(btnInnerColor);
		
						btnInnerColor.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								new JColorChooser();
								btnInnerColor.setBackground(JColorChooser.showDialog(null, "Choose color", null));
							}
						});
						btnInnerColor.setBackground(Color.WHITE);
		
		pnlEast.setLayout(new GridLayout(0, 1, 0, 0));
		pnlEast.add(label_10);
		
		btnDelete.setEnabled(false);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.delete();
			}
		});
		
		
		btnModification.setEnabled(false);
		btnModification.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.modify();
			}
		});
		tglbtnSelection.setBackground(Color.WHITE);
		pnlEast.add(tglbtnSelection);
		btnGroup.add(tglbtnSelection);
		btnModification.setBackground(Color.WHITE);
		pnlEast.add(btnModification);
		btnDelete.setBackground(Color.WHITE);
		pnlEast.add(btnDelete);
		pnlEast.add(label_11);
		pnlEast.add(label_12);
		pnlEast.add(label_13);
		pnlEast.add(label_14);	
		pnlEast.add(label_15);
		pnlEast.add(label_16);
		pnlEast.add(label_17);
		pnlEast.add(label_18);
		
		JPanel pnlTop = new JPanel();
		JPanel pnlBottom = new JPanel();
		pnlSouth.add(pnlTop);
		pnlSouth.add(pnlBottom);
		
		pnlTop.add(btnSaveDrawing);
		btnSaveDrawing.setBackground(Color.WHITE);
		pnlTop.add(btnLoadDrawing);	
		btnLoadDrawing.setBackground(Color.WHITE);
		pnlTop.add(btnSaveLog);	
		btnSaveLog.setBackground(Color.WHITE);
		pnlTop.add(btnLoadLog);	
		btnLoadLog.setBackground(Color.WHITE);
		btnNext.setEnabled(false);
		pnlTop.add(btnNext);		
		btnNext.setBackground(Color.WHITE);
		
		pnlBottom.add(scrollPane);

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