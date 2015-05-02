import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.*;

public class GUI extends JFrame {

//----------------------------------------------------Program GUI-----------------------------------------------------\\
	public GUI() {
		super("Book Inventory");

		JTabbedPane tabs = new JTabbedPane();

		JPanel main = new JPanel();
		main.setLayout(new BorderLayout());

		JPanel buttons = new JPanel();
		buttons.setLayout(new FlowLayout(FlowLayout.CENTER));

		main.add(buttons, BorderLayout.NORTH);
		
		tabs.addTab("asdfasdfasdfasd", main);
		tabs.addTab("Books", buttons);

		add(tabs);

	}
}
