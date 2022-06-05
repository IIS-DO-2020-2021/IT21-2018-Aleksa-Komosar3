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

public class DrawingFrame extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private DrawingView view=new DrawingView();
	private DrawingController controller;
	private JToggleButton btnPoint = new JToggleButton("Point");
	private JToggleButton btnLine = new JToggleButton("Line");
	private JToggleButton btnRectangle = new JToggleButton("Rectangle");
	private JToggleButton btnCircle = new JToggleButton("Circle");
	private JToggleButton btnDonut = new JToggleButton("Donut");
	private JToggleButton btnSelect = new JToggleButton("Selection");
	private JToggleButton btnHexagon=new JToggleButton("Hexagon");
	private final JLabel lblNewLabel = new JLabel("New label");
	private final JLabel lblNewLabel_1 = new JLabel("New label");
	private final JButton btnNewButton = new JButton("New button");
	private final JButton btnNewButton_1 = new JButton("New button");
	private final JButton btnNewButton_2 = new JButton("Undo");
	private final JButton btnNewButton_3 = new JButton("Redo");
	
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
		
		view.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.mouseClicked(e);
			}
		});
		setBounds(150, 150, 1000, 700);
		getContentPane().add(view, BorderLayout.CENTER);
		view.setBackground(Color.WHITE);
		setResizable(false);
		setTitle("IT 21-2018 Komosar Aleksa");
		view.setLayout(new BorderLayout(0, 0));
		
		JPanel pnlNorth = new JPanel();
		getContentPane().add(pnlNorth, BorderLayout.NORTH);
		JPanel pnlSouth = new JPanel();
		getContentPane().add(pnlSouth, BorderLayout.SOUTH);
		getContentPane().setBackground(Color.WHITE);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.deleteShape();
			}
		});
		
		ButtonGroup btnGroup = new ButtonGroup();
		
		JButton btnModification = new JButton("Modification");
		btnModification.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.editShape();
			}
		});
		
		pnlNorth.add(btnPoint);
		pnlNorth.add(btnLine);
		pnlNorth.add(btnRectangle);
		pnlNorth.add(btnCircle);
		pnlNorth.add(btnDonut);
		pnlNorth.add(btnHexagon);
		
		
		pnlSouth.add(btnSelect);
		pnlSouth.add(btnModification);
		pnlSouth.add(btnDelete);	
		
		btnGroup.add(btnPoint);
		btnGroup.add(btnLine);
		btnGroup.add(btnRectangle);
		btnGroup.add(btnCircle);
		btnGroup.add(btnDonut);
		btnGroup.add(btnSelect);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.undo();
			}
		});
		
		
		pnlSouth.add(btnNewButton_2);
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.redo();
			}
		});
		
		pnlSouth.add(btnNewButton_3);
		btnGroup.add(btnHexagon);
		
		pnlNorth.add(lblNewLabel);
		
		pnlNorth.add(btnNewButton);
		
		pnlNorth.add(lblNewLabel_1);
		
		pnlNorth.add(btnNewButton_1);
			
	}

}
