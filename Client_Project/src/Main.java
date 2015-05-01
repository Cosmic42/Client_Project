import javax.swing.*;

public class Main {

	public static void main(String[] args){
		
		Interface window = new Interface();
		
		//GUI setup
		window.setSize(800, 600);
		window.setVisible(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setLocationRelativeTo(null);
	
	}
}