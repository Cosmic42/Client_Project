import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) throws IOException{
		
		GUI window = new GUI();
		
		ImageIcon logo = new ImageIcon("src/NS_Icon.png");
		
		//GUI setup
		window.setIconImage(logo.getImage());
		window.setSize(800, 500);
		window.setVisible(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setLocationRelativeTo(null);
		        
	}
}