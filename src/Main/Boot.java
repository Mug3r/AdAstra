package Main;

import javax.swing.JFrame;

public class Boot {
	private static JFrame window;
	
	//Main method runs on launch creating a new gamepanel object and frame in which it can be found
	public static void main(String[]args){
		
		window = new JFrame("AdAstra");
		window.setContentPane(new GamePanel());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.pack();
		window.setLocationRelativeTo(null);
		window.setVisible(true); 
	}
	//A very very dirty way to create a new game, ran out of ideas so I just did this.
	public static void restart() {
		window.setVisible(false);
		JFrame window2 = new JFrame("AdAstra");
		window2.setContentPane(new GamePanel());
		window2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window2.setResizable(false);
		window2.pack();
		window2.setLocationRelativeTo(null);
		window2.setVisible(true); 
		
	}
	
}