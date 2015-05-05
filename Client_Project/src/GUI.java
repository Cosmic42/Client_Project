import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.*;

public class GUI extends JFrame {

//----------------------------------------------------Program GUI-----------------------------------------------------\\
	public GUI() {
		super("Book Inventory");

		JTabbedPane tabs = new JTabbedPane();

		JPanel inventoryTab = new JPanel();
		inventoryTab.setLayout(new BorderLayout());

		JPanel checkOutTab = new JPanel();
		checkOutTab.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		JPanel modInventoryTab = new JPanel();
		modInventoryTab.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		tabs.addTab("Inventory", inventoryTab);
		tabs.addTab("Book Check-Out", checkOutTab);
		tabs.addTab("Modify Book information", modInventoryTab);

		add(tabs);

	}
}
