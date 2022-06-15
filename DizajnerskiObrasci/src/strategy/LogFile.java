package strategy;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import mvc.DrawingController;
import mvc.DrawingFrame;
import mvc.DrawingModel;
import observer.BtnUpdate;
import util.Util;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter @ToString
public class LogFile implements StrategyFile {

	DrawingModel model;
	DrawingFrame frame;
	DrawingController controller;
	Util util;
	BtnUpdate btnUpd;

	public LogFile(DrawingModel model, DrawingFrame frame, DrawingController controller, BtnUpdate btnUpd) {
		this.model = model;
		this.frame = frame;
		this.controller = controller;
		this.btnUpd=btnUpd;
		this.util = new Util(model,controller, frame, btnUpd);
	}

	@Override
	public void save(String path) {
		try {
			PrintWriter print = new PrintWriter(path);
			print.write(frame.getTextArea().getText());
			
			ImageIcon img=new ImageIcon("C:/Users/EC/git/IT21-2018-Aleksa-Komosar3/DizajnerskiObrasci/images/ok.png");
			JOptionPane.showMessageDialog(frame,
					"Log is saved!", "OK", JOptionPane.OK_OPTION, img);
			print.close();
		}catch (FileNotFoundException e) {
			e.printStackTrace();
			
			ImageIcon img=new ImageIcon("C:/Users/EC/git/IT21-2018-Aleksa-Komosar3/DizajnerskiObrasci/images/fail.png");
			JOptionPane.showMessageDialog(frame, 
					"Cannot save this file!", "Error", JOptionPane.ERROR_MESSAGE, img);
		}
	}

	@Override
	public void load(String path) {
		Scanner sf = null;
		try{
			sf = new Scanner(new File(path));
			if (sf.hasNextLine()) {
				Object[] options = { "Whole", "Step by step" };
				int answer = JOptionPane.showOptionDialog(frame,
						"Do you want whole or step by step log preview loaded?", "Question",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);

				this.util.loadFileByLoadingType(answer, sf);
				model.getRedo().clear();
				model.getUndo().clear();
				btnUpd.setBtnUndoAct(false);
				btnUpd.setBtnRedoAct(false);
			}
			else {
				ImageIcon img=new ImageIcon("C:/Users/EC/git/IT21-2018-Aleksa-Komosar3/DizajnerskiObrasci/images/fail.png");
				JOptionPane.showMessageDialog(frame,
						"Cannot load this file!", "Error", JOptionPane.ERROR_MESSAGE, img);
			}
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
