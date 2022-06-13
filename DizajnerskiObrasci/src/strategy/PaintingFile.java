package strategy;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import geometry.Shape;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import mvc.DrawingFrame;
import mvc.DrawingModel;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter @ToString
public class PaintingFile implements StrategyFile {
	DrawingModel model;
	DrawingFrame frame;

	@Override
	public void save(String path) {
		try {
			FileOutputStream fileOtpStream = new FileOutputStream(path);
			ObjectOutputStream objectOtpStream = new ObjectOutputStream(fileOtpStream);
			objectOtpStream.writeObject(model.getShapes());

			objectOtpStream.close();
			JOptionPane.showMessageDialog(frame, 
					"Drawing is saved!", 
					"OK", JOptionPane.OK_OPTION);
			fileOtpStream.close();
		}catch(Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(frame,
					"Cannot save this file!",
					"Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void load(String path) {
		try {
			
			FileInputStream fileInpStream = new FileInputStream(path);
			ObjectInputStream objectInpStream = new ObjectInputStream(fileInpStream);
			ArrayList<Shape> shapes = new ArrayList<Shape>();
			shapes = (ArrayList<Shape>)objectInpStream.readObject();
			model.getShapes().addAll(shapes);

			objectInpStream.close();
			JOptionPane.showMessageDialog(frame,
					"Drawing is loaded!",
					"OK", JOptionPane.OK_OPTION);
			fileInpStream.close();
		} catch (Exception e){
			e.printStackTrace();
				JOptionPane.showMessageDialog(frame, 
						"Cannot load this drawing! Corrupted file.",
						"Error", JOptionPane.ERROR_MESSAGE);
		}
	}

}
