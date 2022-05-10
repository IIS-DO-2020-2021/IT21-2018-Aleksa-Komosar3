package strategy;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import geometry.Shape;
import mvc.DrawingFrame;
import mvc.DrwingModel;

public class DrawingFile implements FileStrategy {
	DrwingModel model;
	DrawingFrame frame;
	
	public DrawingFile(DrwingModel model, DrawingFrame frame) {
		this.model = model;
		this.frame=frame;
	}
	
	@Override
	public void save(String path) {
		// TODO Auto-generated method stub
		try {
			FileOutputStream f = new FileOutputStream(path);
			ObjectOutputStream obj = new ObjectOutputStream(f);
			obj.writeObject(model.getShapes());

			obj.close();
			ImageIcon img=new ImageIcon("C:/Users/EC/git/IT21-2018-Aleksa-Komosar3/DizajnerskiObrasci/icons/succ.png");
			JOptionPane.showMessageDialog(frame, "Drawing is saved!", "OK", JOptionPane.OK_OPTION, img);
			f.close();
		} catch(Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(frame, "Cannot save this file!", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void load(String path) {
		// TODO Auto-generated method stub
		try {
			FileInputStream f = new FileInputStream(path);
			ObjectInputStream obj = new ObjectInputStream(f);
			ArrayList<Shape> shapes = new ArrayList<Shape>();
			shapes=(ArrayList<Shape>)obj.readObject();
			model.getShapes().addAll(shapes);

			obj.close();
			ImageIcon img=new ImageIcon("C:/Users/EC/git/IT21-2018-Aleksa-Komosar3/DizajnerskiObrasci/icons/succ.png");
			JOptionPane.showMessageDialog(frame, "Drawing is loaded!", "OK", JOptionPane.OK_OPTION, img);
			f.close();
		} catch (Exception e){
			e.printStackTrace();
				JOptionPane.showMessageDialog(frame, "Cannot load this drawing! Corrupted file.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

}
