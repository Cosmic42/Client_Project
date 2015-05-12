import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;



//import javax.imageio.IIOException;
import javax.swing.*;

public class GUI extends JFrame {
	
	private JList scrollList;
	private ArrayList<InventoryObject> database;
	private JTabbedPane tabs;
	private JPanel inventoryTab, checkOutTab, modInventoryTab, viewBooks, viewData;
	
//----------------------------------------------------Program GUI-----------------------------------------------------\\
	public GUI() throws IOException{
		super("Book Inventory");

		FileReader fileReader = new FileReader("Database/database.txt");
		BufferedReader bufferReader = new BufferedReader(fileReader);
		database = new ArrayList<InventoryObject>();
        String line;
		while((line = bufferReader.readLine()) != null)
            database.add(new InventoryObject(line));
      
        bufferReader.close();

		scrollList = new JList(database.toArray());
		
		tabs = new JTabbedPane();
		inventoryTab = new JPanel(); viewBooks = new JPanel(); viewData = new JPanel(); viewBooks = new JPanel();
		checkOutTab = new JPanel();
		modInventoryTab = new JPanel();
		
		setPanels();
						
	}

	public void setPanels() {
		JScrollPane list = new JScrollPane(scrollList, 
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		//list.setPreferredSize(new Dimension(390, 375));
		
		viewBooks.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.NORTHWEST; 

		c.fill = GridBagConstraints.HORIZONTAL;
		setGrid(c, 0, 1, 0, .01);
		viewBooks.add((new JTextArea("asdf")),c);
		setGrid(c, 0, 0, 0, 0);
		viewBooks.add(new JLabel("Search"),c);

		c.fill = GridBagConstraints.BOTH;
		setGrid(c, 0, 2, 1, 1);
		viewBooks.add(list, c);

		inventoryTab.setLayout(new GridLayout(1, 2));
		inventoryTab.add(viewBooks);
		
		viewData.add(new JButton("Herro"));
		
		inventoryTab.add(viewData);
		
		checkOutTab.setLayout(new FlowLayout(FlowLayout.CENTER));		
		modInventoryTab.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		tabs.addTab("Inventory", inventoryTab);
		tabs.addTab("Book Check-Out", checkOutTab);
		tabs.addTab("Modify Book information", modInventoryTab);

		add(tabs);

	}

	public void setGrid(GridBagConstraints c, int gridx, int gridy, double weightx, double weighty){
		c.weightx = weightx; c.weighty = weighty; c.gridx = gridx; c.gridy = gridy;
	}
	
}
