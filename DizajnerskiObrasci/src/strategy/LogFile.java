package strategy;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import mvc.DrawingController;
import mvc.DrawingFrame;
import mvc.DrwingModel;
import util.LogUtil;

public class LogFile implements FileStrategy {
	DrwingModel model;
	DrawingFrame frame;
	DrawingController controller;
	LogUtil util;

	public LogFile(DrwingModel model, DrawingFrame frame, DrawingController controller) {
		this.model = model;
		this.frame = frame;
		this.controller = controller;
		this.util = new LogUtil(model, frame, controller);
	}

	@Override
	public void save(String path) {
		try {
			PrintWriter print = new PrintWriter(path);
			print.write(frame.getTxtAreaLog().getText());
			
			ImageIcon img=new ImageIcon("C:/Users/EC/git/IT21-2018-Aleksa-Komosar3/DizajnerskiObrasci/icons/succ.png");
			JOptionPane.showMessageDialog(frame, "Log is saved!", "OK", JOptionPane.OK_OPTION, img);
			print.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(frame, "Cannot save this file!", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	@Override
	public void load(String path) {
		Scanner f = null;
		try {
			f = new Scanner(new File(path));
			if (f.hasNextLine()) {
				Object[] options = { "Whole", "Step by step" };
				int ans = JOptionPane.showOptionDialog(frame, "Do you want whole or step by step log preview loaded?", "Question",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);

				this.util.loadFileByLoadingType(ans, f);
			}
			else {
				JOptionPane.showMessageDialog(frame, "Cannot load this file!", "Error", JOptionPane.ERROR_MESSAGE);
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
