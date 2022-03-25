package mvc;

import javax.swing.JFrame;

public class Application {

	public static void main(String[] args) {
		DrwingModel model = new DrwingModel();
		DrawingFrame frame=new DrawingFrame();
		frame.getView().setModel(model);
		DrawingController controller=new DrawingController(model, frame);
		frame.setController(controller);
		
		//frame.setSize(1000, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}

}
