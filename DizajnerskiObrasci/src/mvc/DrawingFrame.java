package mvc;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.awt.Font;
import javax.swing.UIManager;

@AllArgsConstructor @Getter @Setter @ToString
public class DrawingFrame extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private DrawingView view=new DrawingView();
	private DrawingController controller;
	private JToggleButton btnPoint = new JToggleButton("Point          ");
	private JToggleButton btnLine = new JToggleButton("Line            ");
	private JToggleButton btnRectangle = new JToggleButton("Rectangle ");
	private JToggleButton btnCircle = new JToggleButton("Circle         ");
	private JToggleButton btnDonut = new JToggleButton("Donut         ");
	private JToggleButton btnSelect = new JToggleButton("Selection");
	private JToggleButton btnHexagon=new JToggleButton("Hexagon    ");
	private JButton btnModification = new JButton("Modification");
	private JButton btnUndo = new JButton("Undo ");
	private JButton btnRedo = new JButton("Redo");
	ButtonGroup btnGroup = new ButtonGroup();
	private JButton btnOuterColor = new JButton("Outer color");
	private JTextArea textArea = new JTextArea();
	private JScrollPane scrollPane = new JScrollPane(textArea);
	private JButton btnLoadCommands = new JButton("Load commands");
	private JButton btnToFront = new JButton("To front");
	private JButton btnToBack = new JButton("To back");
	private JButton btnBringToFront = new JButton("Bring to front");
	private JButton btnBringToBack = new JButton("Bring to back");
	private JButton btnNext = new JButton("Next");
	private JButton btnSaveCommands = new JButton("Save commands");
	private JPanel panel = new JPanel();
	private JButton btnSaveDrawing = new JButton("Save drawing");
	private JButton btnLoadDrawing = new JButton("Load drawing");
	private JButton btnInnerColor = new JButton("Inner color");
	private JButton btnDelete = new JButton("Delete");
	public Color colorFrame;
	public Color innerColorFrame;

	public DrawingFrame() {
		setFont(new Font("Bahnschrift", Font.PLAIN, 12));
		setBackground(UIManager.getColor("Tree.selectionForeground"));
		setBounds(150, 150, 1000, 700);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel pnlNorth = new JPanel();
		pnlNorth.setBackground(UIManager.getColor("activeCaption"));
		pnlNorth.setForeground(UIManager.getColor("activeCaption"));
		getContentPane().add(pnlNorth, BorderLayout.NORTH);
		GridBagLayout gbl_pnlNorth = new GridBagLayout();
		gbl_pnlNorth.columnWidths = new int[]{292, 0, 61, 61, 0, 108, 0, 89, 60, 0, 0, 0, 0};
		gbl_pnlNorth.rowHeights = new int[]{55, 0, 0, 0};
		gbl_pnlNorth.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_pnlNorth.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		pnlNorth.setLayout(gbl_pnlNorth);
		textArea.setEditable(false);
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
		panel.setBackground(UIManager.getColor("activeCaption"));
		pnlSouth.add(panel, gbc_panel);
		
		btnSaveCommands.setEnabled(false);
		btnSaveCommands.setBackground(Color.WHITE);
		btnSaveCommands.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.saveLog();
			}
		});
		
		panel.add(btnSaveCommands);
		btnLoadCommands.setBackground(Color.WHITE);
		btnLoadCommands.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.loadLog();
			}
		});
		panel.add(btnLoadCommands);
		btnNext.setBackground(Color.WHITE);
		btnNext.setEnabled(false);
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel.add(btnNext);
		btnSaveDrawing.setBackground(Color.WHITE);
		btnSaveDrawing.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.saveDrw();
			}
		});
		
		
		btnSaveDrawing.setEnabled(false);
		panel.add(btnSaveDrawing);
		btnLoadDrawing.setBackground(Color.WHITE);
		btnLoadDrawing.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.loadDrawing();
			}
		});
		
		panel.add(btnLoadDrawing);
		
		GridBagConstraints gbc_textArea = new GridBagConstraints();
		gbc_textArea.insets = new Insets(0, 0, 0, 5);
		gbc_textArea.anchor = GridBagConstraints.NORTHWEST;
		gbc_textArea.gridx = 0;
		gbc_textArea.gridy = 0;
		gbc_textArea.gridy = 0;
		setResizable(false);
		setTitle("IT 21-2018 Komosar Aleksa");
		getContentPane().setBackground(Color.WHITE);
		
		JPanel pnlWest = new JPanel();
		pnlWest.setBackground(UIManager.getColor("activeCaption"));
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
		btnPoint.setBackground(Color.WHITE);
		btnPoint.setVerticalAlignment(SwingConstants.BOTTOM);
		btnPoint.setFont(new Font("Bahnschrift", Font.PLAIN, 13));
		btnPoint.setHorizontalAlignment(SwingConstants.LEFT);
		pnlWest.add(btnPoint, gbc_btnPoint);
		btnGroup.add(btnLine);
		GridBagConstraints gbc_btnLine = new GridBagConstraints();
		gbc_btnLine.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnLine.insets = new Insets(0, 0, 5, 5);
		gbc_btnLine.gridx = 1;
		gbc_btnLine.gridy = 1;
		btnLine.setBackground(Color.WHITE);
		btnLine.setFont(new Font("Bahnschrift", Font.PLAIN, 13));
		btnLine.setHorizontalAlignment(SwingConstants.LEFT);
		pnlWest.add(btnLine, gbc_btnLine);
		GridBagConstraints gbc_btnRectangle = new GridBagConstraints();
		gbc_btnRectangle.anchor = GridBagConstraints.WEST;
		gbc_btnRectangle.insets = new Insets(0, 0, 5, 5);
		gbc_btnRectangle.gridx = 1;
		gbc_btnRectangle.gridy = 2;
		btnRectangle.setBackground(Color.WHITE);
		btnRectangle.setFont(new Font("Bahnschrift", Font.PLAIN, 13));
		btnRectangle.setHorizontalAlignment(SwingConstants.LEFT);
		pnlWest.add(btnRectangle, gbc_btnRectangle);
		btnGroup.add(btnRectangle);
		GridBagConstraints gbc_btnCircle = new GridBagConstraints();
		gbc_btnCircle.anchor = GridBagConstraints.WEST;
		gbc_btnCircle.insets = new Insets(0, 0, 5, 5);
		gbc_btnCircle.gridx = 1;
		gbc_btnCircle.gridy = 3;
		btnCircle.setBackground(Color.WHITE);
		btnCircle.setFont(new Font("Bahnschrift", Font.PLAIN, 13));
		btnCircle.setHorizontalAlignment(SwingConstants.LEFT);
		btnCircle.setVerticalAlignment(SwingConstants.TOP);
		pnlWest.add(btnCircle, gbc_btnCircle);
		btnGroup.add(btnCircle);
		GridBagConstraints gbc_btnDonut = new GridBagConstraints();
		gbc_btnDonut.anchor = GridBagConstraints.WEST;
		gbc_btnDonut.insets = new Insets(0, 0, 5, 5);
		gbc_btnDonut.gridx = 1;
		gbc_btnDonut.gridy = 4;
		btnDonut.setBackground(Color.WHITE);
		btnDonut.setFont(new Font("Bahnschrift", Font.PLAIN, 13));
		btnDonut.setHorizontalAlignment(SwingConstants.LEFT);
		pnlWest.add(btnDonut, gbc_btnDonut);
		btnGroup.add(btnDonut);
		GridBagConstraints gbc_btnHexagon = new GridBagConstraints();
		gbc_btnHexagon.anchor = GridBagConstraints.WEST;
		gbc_btnHexagon.insets = new Insets(0, 0, 5, 5);
		gbc_btnHexagon.gridx = 1;
		gbc_btnHexagon.gridy = 5;
		btnHexagon.setBackground(Color.WHITE);
		btnHexagon.setFont(new Font("Bahnschrift", Font.PLAIN, 13));
		btnHexagon.setHorizontalAlignment(SwingConstants.LEFT);
		pnlWest.add(btnHexagon, gbc_btnHexagon);
		btnGroup.add(btnHexagon);
		
		JPanel pnlEast = new JPanel();
		pnlEast.setBackground(UIManager.getColor("activeCaption"));
		getContentPane().add(pnlEast, BorderLayout.EAST);
		GridBagLayout gbl_pnlEast = new GridBagLayout();
		gbl_pnlEast.columnWidths = new int[]{135, 61, 0};
		gbl_pnlEast.rowHeights = new int[]{0, 25, 0, 0, 0, 0, 0};
		gbl_pnlEast.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_pnlEast.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		pnlEast.setLayout(gbl_pnlEast);
		GridBagConstraints gbc_btnUndo = new GridBagConstraints();
		gbc_btnUndo.anchor = GridBagConstraints.NORTHEAST;
		gbc_btnUndo.insets = new Insets(0, 0, 5, 5);
		gbc_btnUndo.gridx = 0;
		gbc_btnUndo.gridy = 1;
		btnUndo.setBackground(Color.LIGHT_GRAY);
		btnUndo.setHorizontalAlignment(SwingConstants.RIGHT);
		pnlEast.add(btnUndo, gbc_btnUndo);
		btnUndo.setEnabled(false);
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
		btnRedo.setBackground(Color.LIGHT_GRAY);
		pnlEast.add(btnRedo, gbc_btnRedo);
		
		GridBagConstraints gbc_btnToFront = new GridBagConstraints();
		gbc_btnToFront.insets = new Insets(0, 0, 5, 5);
		gbc_btnToFront.gridx = 0;
		gbc_btnToFront.gridy = 3;
		btnToFront.setBackground(Color.WHITE);
		btnToFront.setEnabled(false);
		btnToFront.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.bringToFrontByOne();
			}
		});
		pnlEast.add(btnToFront, gbc_btnToFront);
		
		GridBagConstraints gbc_btnToBack = new GridBagConstraints();
		gbc_btnToBack.insets = new Insets(0, 0, 5, 0);
		gbc_btnToBack.gridx = 1;
		gbc_btnToBack.gridy = 3;
		btnToBack.setBackground(Color.WHITE);
		
		btnToBack.setEnabled(false);
		btnToBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.bringToBackByOne();
			}
		});
		pnlEast.add(btnToBack, gbc_btnToBack);
		
		GridBagConstraints gbc_btnBringToFront = new GridBagConstraints();
		gbc_btnBringToFront.anchor = GridBagConstraints.EAST;
		gbc_btnBringToFront.insets = new Insets(0, 0, 0, 5);
		gbc_btnBringToFront.gridx = 0;
		gbc_btnBringToFront.gridy = 5;
		btnBringToFront.setBackground(Color.WHITE);
		
		btnBringToFront.setEnabled(false);
		btnBringToFront.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.fullBringToFront();
			}
		});
		pnlEast.add(btnBringToFront, gbc_btnBringToFront);
		
		GridBagConstraints gbc_btnBringToBack = new GridBagConstraints();
		gbc_btnBringToBack.anchor = GridBagConstraints.WEST;
		gbc_btnBringToBack.gridx = 1;
		gbc_btnBringToBack.gridy = 5;
		btnBringToBack.setBackground(Color.WHITE);
		
		btnBringToBack.setEnabled(false);
		btnBringToBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.fullBringToBack();
			}
		});
		pnlEast.add(btnBringToBack, gbc_btnBringToBack);
		
		btnRedo.setEnabled(false);
		btnRedo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.redo();
			}
		});
		
		Image pimg=new ImageIcon(this.getClass().getResource("/point.png")).getImage();
		btnPoint.setIcon(new ImageIcon(pimg));
		Image cimg=new ImageIcon(this.getClass().getResource("/circle.png")).getImage();
		btnCircle.setIcon(new ImageIcon(cimg));
		Image rimg=new ImageIcon(this.getClass().getResource("/rectangle.png")).getImage();
		btnRectangle.setIcon(new ImageIcon(rimg));
		Image limg=new ImageIcon(this.getClass().getResource("/line.png")).getImage();
		btnLine.setIcon(new ImageIcon(limg));
		Image dimg=new ImageIcon(this.getClass().getResource("/donut.png")).getImage();
		btnDonut.setIcon(new ImageIcon(dimg));
		Image himg=new ImageIcon(this.getClass().getResource("/hexagon.png")).getImage();
		btnHexagon.setIcon(new ImageIcon(himg));
		Image deimg=new ImageIcon(this.getClass().getResource("/delete.png")).getImage();
		Image mimg=new ImageIcon(this.getClass().getResource("/modify.png")).getImage();
		Image simg=new ImageIcon(this.getClass().getResource("/select.png")).getImage();
		
		GridBagConstraints gbc_btnOuterColor = new GridBagConstraints();
		gbc_btnOuterColor.insets = new Insets(0, 0, 5, 5);
		gbc_btnOuterColor.gridx = 9;
		gbc_btnOuterColor.gridy = 1;
		btnOuterColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				borderColor();
			}
		});
		
		btnInnerColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				innerColor();
				getBtnInnerColor().setSelected(false);
			}
		});
		btnGroup.add(btnSelect);
		GridBagConstraints gbc_btnSelect = new GridBagConstraints();
		gbc_btnSelect.insets = new Insets(0, 0, 5, 5);
		gbc_btnSelect.gridx = 2;
		gbc_btnSelect.gridy = 0;
		btnSelect.setBackground(Color.WHITE);
		pnlNorth.add(btnSelect, gbc_btnSelect);
		
		btnSelect.setEnabled(false);
		btnSelect.setIcon(new ImageIcon(simg));
		
		GridBagConstraints gbc_btnModification = new GridBagConstraints();
		gbc_btnModification.insets = new Insets(0, 0, 5, 5);
		gbc_btnModification.gridx = 3;
		gbc_btnModification.gridy = 0;
		btnModification.setBackground(Color.WHITE);
		pnlNorth.add(btnModification, gbc_btnModification);
		btnModification.setEnabled(false);
		btnModification.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.editShape();
			}
		});
		btnModification.setIcon(new ImageIcon(mimg));
		
		GridBagConstraints gbc_btnDelete = new GridBagConstraints();
		gbc_btnDelete.insets = new Insets(0, 0, 5, 5);
		gbc_btnDelete.gridx = 4;
		gbc_btnDelete.gridy = 0;
		btnDelete.setBackground(Color.WHITE);
		pnlNorth.add(btnDelete, gbc_btnDelete);
		
		btnDelete.setEnabled(false);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.deleteShape();
			}
		});
		btnDelete.setIcon(new ImageIcon(deimg));
		
		JLabel lblInnerColor = new JLabel("Inner color:");
		lblInnerColor.setFont(new Font("Bahnschrift", Font.PLAIN, 13));
		GridBagConstraints gbc_lblInnerColor = new GridBagConstraints();
		gbc_lblInnerColor.anchor = GridBagConstraints.SOUTH;
		gbc_lblInnerColor.insets = new Insets(0, 0, 5, 5);
		gbc_lblInnerColor.gridx = 7;
		gbc_lblInnerColor.gridy = 0;
		pnlNorth.add(lblInnerColor, gbc_lblInnerColor);
		
		JLabel lblColors = new JLabel("COLORS:");
		GridBagConstraints gbc_lblColors = new GridBagConstraints();
		gbc_lblColors.insets = new Insets(0, 0, 5, 5);
		gbc_lblColors.gridx = 8;
		gbc_lblColors.gridy = 0;
		pnlNorth.add(lblColors, gbc_lblColors);
		
		JLabel lblOuterColor = new JLabel("Outer color:");
		lblOuterColor.setFont(new Font("Bahnschrift", Font.PLAIN, 13));
		GridBagConstraints gbc_lblOuterColor = new GridBagConstraints();
		gbc_lblOuterColor.anchor = GridBagConstraints.SOUTH;
		gbc_lblOuterColor.insets = new Insets(0, 0, 5, 5);
		gbc_lblOuterColor.gridx = 9;
		gbc_lblOuterColor.gridy = 0;
		pnlNorth.add(lblOuterColor, gbc_lblOuterColor);
		GridBagConstraints gbc_btnInnerColor = new GridBagConstraints();
		gbc_btnInnerColor.insets = new Insets(0, 0, 5, 5);
		gbc_btnInnerColor.gridx = 7;
		gbc_btnInnerColor.gridy = 1;
		pnlNorth.add(btnInnerColor, gbc_btnInnerColor);
		pnlNorth.add(btnOuterColor, gbc_btnOuterColor);
		
		Image unimg=new ImageIcon(this.getClass().getResource("/undo.png")).getImage();
		btnUndo.setIcon(new ImageIcon(unimg));
		
		Image reimg=new ImageIcon(this.getClass().getResource("/redo.png")).getImage();
		btnRedo.setIcon(new ImageIcon(reimg));
		
		Image ffimg=new ImageIcon(this.getClass().getResource("/tofront.png")).getImage();
		btnBringToFront.setIcon(new ImageIcon(ffimg));
		
		Image bbimg=new ImageIcon(this.getClass().getResource("/toback.png")).getImage();
		btnBringToBack.setIcon(new ImageIcon(bbimg));
		
		Image bfimg=new ImageIcon(this.getClass().getResource("/toback.png")).getImage();
		btnToBack.setIcon(new ImageIcon(bfimg));
		
		Image fbimg=new ImageIcon(this.getClass().getResource("/tofront.png")).getImage();
		btnToFront.setIcon(new ImageIcon(fbimg));
		
		Image slimg=new ImageIcon(this.getClass().getResource("/save.png")).getImage();
		btnSaveCommands.setIcon(new ImageIcon(slimg));
		
		Image sdimg=new ImageIcon(this.getClass().getResource("/save.png")).getImage();
		btnSaveDrawing.setIcon(new ImageIcon(sdimg));
		
		Image llimg=new ImageIcon(this.getClass().getResource("/load.png")).getImage();
		btnLoadCommands.setIcon(new ImageIcon(llimg));
		
		Image lpimg=new ImageIcon(this.getClass().getResource("/load.png")).getImage();
		btnLoadDrawing.setIcon(new ImageIcon(lpimg));
		
		Image next=new ImageIcon(this.getClass().getResource("/next.png")).getImage();
		btnNext.setIcon(new ImageIcon(next));
		
		
	}
	
	public void borderColor() {
		colorFrame = JColorChooser.showDialog(null, "Choose color", getBtnOuterColor().getBackground());
		if (colorFrame != null){
			getBtnOuterColor().setBackground(colorFrame);
		} else {
			getBtnOuterColor().setBackground(Color.BLACK);
		}
	}
	
	public void innerColor(){
		innerColorFrame = JColorChooser.showDialog(null, "Choose inner color", getBtnInnerColor().getBackground());
		if (innerColorFrame != null){
			getBtnInnerColor().setBackground(innerColorFrame);
	} else {
		getBtnInnerColor().setBackground(Color.BLACK);
		}
	}
}
