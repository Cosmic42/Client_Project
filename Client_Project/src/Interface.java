import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.*;

public class Interface extends JFrame {

//----------------------------------------------------Program GUI-----------------------------------------------------\\
	public Interface() {
		super("Book Inventory");

		setLayout(new GridLayout(1, 2));

		JPanel main = new JPanel();
		main.setLayout(new BorderLayout());

		JPanel buttons = new JPanel();
		buttons.setLayout(new FlowLayout(FlowLayout.CENTER));

		main.add(buttons, BorderLayout.NORTH);

		add(main);

	}
}
