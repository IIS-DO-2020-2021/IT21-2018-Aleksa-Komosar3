package mvc;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class DrawingFrame extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private DrawingView view=new DrawingView();
	private DrawingController controller;
	private JToggleButton btnPoint = new JToggleButton("Point     ");
	private JToggleButton btnLine = new JToggleButton("Line      ");
	private JToggleButton btnRectangle = new JToggleButton("Rectangle");
	private JToggleButton btnCircle = new JToggleButton("Circle    ");
	private JToggleButton btnDonut = new JToggleButton("Donut   ");
	private JToggleButton btnSelect = new JToggleButton("Selection");
	private JToggleButton btnHexagon=new JToggleButton("Hexagon");
	private final JButton btnUndo = new JButton("Undo         ");
	private final JButton btnRedo = new JButton("Redo");
	ButtonGroup btnGroup = new ButtonGroup();
	private final JButton btnOuterColor = new JButton("Outer color");
	private final JScrollPane scrollPane = new JScrollPane();
	private final JTextArea textArea = new JTextArea();
	private final JButton btnLoadCommands = new JButton("Load commands");
	private final JButton btnToFront = new JButton("To front");
	private final JButton btnToBack = new JButton("To back");
	private final JButton btnBringToFront = new JButton("Bring to front");
	private final JButton btnBringToBack = new JButton("Bring to back");
	private final JButton btnNext = new JButton("Next");
	private final JButton btnSaveCommands = new JButton("Save commands");
	private final JPanel panel = new JPanel();
	private final JButton btnSaveDrawing = new JButton("Save drawing");
	private final JButton btnLoadDrawing = new JButton("Load drawing");
	
	public JToggleButton getBtnHexagon() {
		return btnHexagon;
	}

	public void setBtnHexagon(JToggleButton btnHexagon) {
		this.btnHexagon = btnHexagon;
	}

	public DrawingView getView() {
		return view;
	}

	public void setView(DrawingView view) {
		this.view = view;
	}

	public DrawingController getController() {
		return controller;
	}

	public void setController(DrawingController controller) {
		this.controller = controller;
	}

	public JToggleButton getBtnPoint() {
		return btnPoint;
	}

	public void setBtnPoint(JToggleButton btnPoint) {
		this.btnPoint = btnPoint;
	}

	public JToggleButton getBtnLine() {
		return btnLine;
	}

	public void setBtnLine(JToggleButton btnLine) {
		this.btnLine = btnLine;
	}

	public JToggleButton getBtnRectangle() {
		return btnRectangle;
	}

	public void setBtnRectangle(JToggleButton btnRectangle) {
		this.btnRectangle = btnRectangle;
	}

	public JToggleButton getBtnCircle() {
		return btnCircle;
	}

	public void setBtnCircle(JToggleButton btnCircle) {
		this.btnCircle = btnCircle;
	}

	public JToggleButton getBtnDonut() {
		return btnDonut;
	}

	public void setBtnDonut(JToggleButton btnDonut) {
		this.btnDonut = btnDonut;
	}

	public JToggleButton getBtnSelect() {
		return btnSelect;
	}

	public void setBtnSelect(JToggleButton btnSelect) {
		this.btnSelect = btnSelect;
	}
	
	public DrawingFrame() {
		setBounds(150, 150, 1000, 700);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel pnlNorth = new JPanel();
		getContentPane().add(pnlNorth, BorderLayout.NORTH);
		GridBagLayout gbl_pnlNorth = new GridBagLayout();
		gbl_pnlNorth.columnWidths = new int[]{433, 0, 61, 61, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_pnlNorth.rowHeights = new int[]{55, 0, 0, 0};
		gbl_pnlNorth.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_pnlNorth.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		pnlNorth.setLayout(gbl_pnlNorth);
		btnGroup.add(btnSelect);
		GridBagConstraints gbc_btnSelect = new GridBagConstraints();
		gbc_btnSelect.insets = new Insets(0, 0, 5, 5);
		gbc_btnSelect.gridx = 1;
		gbc_btnSelect.gridy = 0;
		pnlNorth.add(btnSelect, gbc_btnSelect);
		
		JButton btnModification = new JButton("Modification");
		GridBagConstraints gbc_btnModification = new GridBagConstraints();
		gbc_btnModification.insets = new Insets(0, 0, 5, 5);
		gbc_btnModification.gridx = 2;
		gbc_btnModification.gridy = 0;
		pnlNorth.add(btnModification, gbc_btnModification);
		btnModification.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.editShape();
			}
		});
		
		JButton btnDelete = new JButton("Delete");
		GridBagConstraints gbc_btnDelete = new GridBagConstraints();
		gbc_btnDelete.insets = new Insets(0, 0, 5, 5);
		gbc_btnDelete.gridx = 3;
		gbc_btnDelete.gridy = 0;
		pnlNorth.add(btnDelete, gbc_btnDelete);
		
		JLabel lblColors = new JLabel("COLORS:");
		GridBagConstraints gbc_lblColors = new GridBagConstraints();
		gbc_lblColors.insets = new Insets(0, 0, 5, 5);
		gbc_lblColors.gridx = 7;
		gbc_lblColors.gridy = 0;
		pnlNorth.add(lblColors, gbc_lblColors);
		
		JButton btnInnerColor = new JButton("Inner color");
		GridBagConstraints gbc_btnInnerColor = new GridBagConstraints();
		gbc_btnInnerColor.insets = new Insets(0, 0, 5, 5);
		gbc_btnInnerColor.gridx = 6;
		gbc_btnInnerColor.gridy = 1;
		pnlNorth.add(btnInnerColor, gbc_btnInnerColor);
		
		GridBagConstraints gbc_btnOuterColor = new GridBagConstraints();
		gbc_btnOuterColor.insets = new Insets(0, 0, 5, 5);
		gbc_btnOuterColor.gridx = 8;
		gbc_btnOuterColor.gridy = 1;
		pnlNorth.add(btnOuterColor, gbc_btnOuterColor);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.deleteShape();
			}
		});
		
		view.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.mouseClicked(e);
			}
		});
		getContentPane().add(view);
		view.setBackground(Color.WHITE);
		GroupLayout gl_view = new GroupLayout(view);
		gl_view.setHorizontalGroup(
			gl_view.createParallelGroup(Alignment.LEADING)
				.addGap(0, 994, Short.MAX_VALUE)
		);
		gl_view.setVerticalGroup(
			gl_view.createParallelGroup(Alignment.LEADING)
				.addGap(0, 595, Short.MAX_VALUE)
		);
		view.setLayout(gl_view);
		JPanel pnlSouth = new JPanel();
		getContentPane().add(pnlSouth, BorderLayout.SOUTH);
		GridBagLayout gbl_pnlSouth = new GridBagLayout();
		gbl_pnlSouth.columnWidths = new int[]{814, 0};
		gbl_pnlSouth.rowHeights = new int[]{127, 0, 0};
		gbl_pnlSouth.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_pnlSouth.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		pnlSouth.setLayout(gbl_pnlSouth);
		
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		pnlSouth.add(scrollPane, gbc_scrollPane);
		textArea.setColumns(20);
		
		scrollPane.setViewportView(textArea);
		
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 1;
		pnlSouth.add(panel, gbc_panel);
		panel.add(btnSaveCommands);
		btnLoadCommands.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel.add(btnLoadCommands);
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel.add(btnNext);
		
		panel.add(btnSaveDrawing);
		
		panel.add(btnLoadDrawing);
		
		GridBagConstraints gbc_textArea = new GridBagConstraints();
		gbc_textArea.insets = new Insets(0, 0, 0, 5);
		gbc_textArea.anchor = GridBagConstraints.NORTHWEST;
		gbc_textArea.gridx = 0;
		gbc_textArea.gridy = 0;
	//	GridBagConstraints gbc_textArea = new GridBagConstraints();
	//	gbc_textArea.insets = new Insets(0, 0, 0, 5);
	//	gbc_textArea.gridx = 0;
		gbc_textArea.gridy = 0;
		setResizable(false);
		setTitle("IT 21-2018 Komosar Aleksa");
		getContentPane().setBackground(Color.WHITE);
		
		JPanel pnlWest = new JPanel();
		getContentPane().add(pnlWest, BorderLayout.WEST);
		GridBagLayout gbl_pnlWest = new GridBagLayout();
		gbl_pnlWest.columnWidths = new int[]{33, 55, -1, 0};
		gbl_pnlWest.rowHeights = new int[]{25, 0, 0, 0, 0, 0, 0, 0};
		gbl_pnlWest.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_pnlWest.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		pnlWest.setLayout(gbl_pnlWest);
		
		btnGroup.add(btnPoint);
		GridBagConstraints gbc_btnPoint = new GridBagConstraints();
		gbc_btnPoint.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnPoint.insets = new Insets(0, 0, 5, 5);
		gbc_btnPoint.gridx = 1;
		gbc_btnPoint.gridy = 0;
		pnlWest.add(btnPoint, gbc_btnPoint);
		btnGroup.add(btnLine);
		GridBagConstraints gbc_btnLine = new GridBagConstraints();
		gbc_btnLine.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnLine.insets = new Insets(0, 0, 5, 5);
		gbc_btnLine.gridx = 1;
		gbc_btnLine.gridy = 1;
		pnlWest.add(btnLine, gbc_btnLine);
		GridBagConstraints gbc_btnRectangle = new GridBagConstraints();
		gbc_btnRectangle.insets = new Insets(0, 0, 5, 5);
		gbc_btnRectangle.gridx = 1;
		gbc_btnRectangle.gridy = 2;
		pnlWest.add(btnRectangle, gbc_btnRectangle);
		btnGroup.add(btnRectangle);
		GridBagConstraints gbc_btnCircle = new GridBagConstraints();
		gbc_btnCircle.insets = new Insets(0, 0, 5, 5);
		gbc_btnCircle.gridx = 1;
		gbc_btnCircle.gridy = 3;
		btnCircle.setVerticalAlignment(SwingConstants.TOP);
		pnlWest.add(btnCircle, gbc_btnCircle);
		btnGroup.add(btnCircle);
		GridBagConstraints gbc_btnDonut = new GridBagConstraints();
		gbc_btnDonut.insets = new Insets(0, 0, 5, 5);
		gbc_btnDonut.gridx = 1;
		gbc_btnDonut.gridy = 4;
		pnlWest.add(btnDonut, gbc_btnDonut);
		btnGroup.add(btnDonut);
		GridBagConstraints gbc_btnHexagon = new GridBagConstraints();
		gbc_btnHexagon.insets = new Insets(0, 0, 5, 5);
		gbc_btnHexagon.gridx = 1;
		gbc_btnHexagon.gridy = 5;
		pnlWest.add(btnHexagon, gbc_btnHexagon);
		btnGroup.add(btnHexagon);
		
		JPanel pnlEast = new JPanel();
		getContentPane().add(pnlEast, BorderLayout.EAST);
		GridBagLayout gbl_pnlEast = new GridBagLayout();
		gbl_pnlEast.columnWidths = new int[]{135, 61, 0};
		gbl_pnlEast.rowHeights = new int[]{0, 25, 0, 0, 0, 0, 0};
		gbl_pnlEast.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_pnlEast.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		pnlEast.setLayout(gbl_pnlEast);
		GridBagConstraints gbc_btnUndo = new GridBagConstraints();
		gbc_btnUndo.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnUndo.insets = new Insets(0, 0, 5, 5);
		gbc_btnUndo.gridx = 0;
		gbc_btnUndo.gridy = 1;
		btnUndo.setHorizontalAlignment(SwingConstants.RIGHT);
		pnlEast.add(btnUndo, gbc_btnUndo);
		btnUndo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.undo();
			}
		});
		GridBagConstraints gbc_btnRedo = new GridBagConstraints();
		gbc_btnRedo.insets = new Insets(0, 0, 5, 0);
		gbc_btnRedo.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnRedo.gridx = 1;
		gbc_btnRedo.gridy = 1;
		pnlEast.add(btnRedo, gbc_btnRedo);
		
		GridBagConstraints gbc_btnToFront = new GridBagConstraints();
		gbc_btnToFront.insets = new Insets(0, 0, 5, 5);
		gbc_btnToFront.gridx = 0;
		gbc_btnToFront.gridy = 3;
		pnlEast.add(btnToFront, gbc_btnToFront);
		
		GridBagConstraints gbc_btnToBack = new GridBagConstraints();
		gbc_btnToBack.insets = new Insets(0, 0, 5, 0);
		gbc_btnToBack.gridx = 1;
		gbc_btnToBack.gridy = 3;
		pnlEast.add(btnToBack, gbc_btnToBack);
		
		GridBagConstraints gbc_btnBringToFront = new GridBagConstraints();
		gbc_btnBringToFront.insets = new Insets(0, 0, 0, 5);
		gbc_btnBringToFront.gridx = 0;
		gbc_btnBringToFront.gridy = 5;
		pnlEast.add(btnBringToFront, gbc_btnBringToFront);
		
		GridBagConstraints gbc_btnBringToBack = new GridBagConstraints();
		gbc_btnBringToBack.gridx = 1;
		gbc_btnBringToBack.gridy = 5;
		pnlEast.add(btnBringToBack, gbc_btnBringToBack);
		btnRedo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.redo();
			}
		});
		
	
			
	}
}
