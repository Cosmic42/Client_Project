import javax.swing.*;

public class Main {

	public static void main(String[] args){
		
		Interface window = new Interface();
		
		ImageIcon logo = new ImageIcon("src/NS_Icon.png");
		
		//GUI setup
		window.setIconImage(logo.getImage());
		window.setSize(800, 600);
		window.setVisible(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setLocationRelativeTo(null);
	
	}
}