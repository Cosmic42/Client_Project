import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.*;

import javax.swing.event.*;
import javax.swing.*;

public class GUI extends JFrame {
	
	///Fields, so that GUI Components can be used throughout the class
	private JList<InventoryObject> scrollList;
	private ArrayList<InventoryObject> database;
	private JTabbedPane tabs;
	private JPanel inventoryTab, checkOutTab, modInventoryTab, viewBooks, viewData;
	private DefaultListModel<InventoryObject> filter;
	private JTextField search, viewNum, viewPrice, viewISBN; 
	private GUIHandler handler;

//----------------------------------------------------Program GUI-----------------------------------------------------\\
	public GUI() throws IOException{
		super("Book Inventory");
		
		handler = new GUIHandler();
		
		//Read the database
		FileReader fileReader = new FileReader("Database/database.txt");
		BufferedReader bufferReader = new BufferedReader(fileReader);
		database = new ArrayList<InventoryObject>();
        String line;
		while((line = bufferReader.readLine()) != null)
            database.add(new InventoryObject(line));
      
        bufferReader.close();

        //Initialize all the fields
        this.setLayout(new GridLayout(1, 2));
        filter = new DefaultListModel<InventoryObject>();
      
        for(InventoryObject i:database)
            filter.addElement(i);
        
		scrollList = new JList<InventoryObject>(filter);
		
		tabs = new JTabbedPane();
		inventoryTab = new JPanel(); viewBooks = new JPanel(); viewData = new JPanel(); viewBooks = new JPanel();
		checkOutTab = new JPanel();
		modInventoryTab = new JPanel();
		search = new JTextField(); viewNum = new JTextField(4); viewPrice = new JTextField(4); viewISBN = new JTextField(10);
		setPanels();
		
	}

	//Panel Setup
	public void setPanels() {
		
		//Adding selectionlisteners
		scrollList.addListSelectionListener(handler);
		scrollList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JScrollPane list = new JScrollPane(scrollList, 
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
				
		//Setting layout for the first tab with GridBagLayout
		viewBooks.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.NORTHWEST; 

		c.fill = GridBagConstraints.HORIZONTAL;
		setGrid(c, 0, 1, 0, .01);
		
		//Adding Labels for search and JList
		search.getDocument().addDocumentListener(handler);
		viewBooks.add(search,c);
		setGrid(c, 0, 0, 0, 0);
		viewBooks.add(new JLabel("Search"),c);

		c.fill = GridBagConstraints.BOTH;
		setGrid(c, 0, 2, 1, 1);
		viewBooks.add(list, c);

		//Display Information. The next blocks of code is just adding JLabels and JTextFields. Changing to use SpringLayout
		SpringLayout layout = new SpringLayout();
		viewData.setLayout(layout);
		
		viewData.add(new JLabel("Book Information: "));
		
		viewNum.setEditable(false);
		viewData.add(new JLabel("Total Number: "));
		viewData.add(viewNum);

		viewPrice.setEditable(false);
		viewData.add(new JLabel("Book Price "));
		viewData.add(viewPrice);

		viewISBN.setEditable(false);
		viewData.add(new JLabel(" ISBN: "));
		viewData.add(viewISBN);
		
		inventoryTab.setLayout(new GridLayout(1, 2));		
		inventoryTab.add(viewData);
		
		//Layouts for the other tabs WIP
		checkOutTab.setLayout(new FlowLayout(FlowLayout.CENTER));		
		modInventoryTab.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		tabs.addTab("Inventory", inventoryTab);
		tabs.addTab("Book Check-Out", checkOutTab);
		tabs.addTab("Modify Book information", modInventoryTab);
		
		add(viewBooks);
		add(tabs);
				
	}

	public void setGrid(GridBagConstraints c, int gridx, int gridy, double weightx, double weighty){
		c.weightx = weightx; c.weighty = weighty; c.gridx = gridx; c.gridy = gridy;
	}
		
	//Function searches through the database and adds them to the list model
	public void searchJList(){
        filter.clear();
		scrollList.removeListSelectionListener(handler);
        for(InventoryObject i : database)
			if(i.getName().toUpperCase().contains(search.getText().toUpperCase()))
				filter.addElement(i);
        refresh();
        scrollList.addListSelectionListener(handler);
		scrollList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
	}
	
	public void refresh(){
		viewNum.setText("");
		viewPrice.setText("");
		viewISBN.setText("");

	}
	
//-----------------------------------------Nested Class for Event Listeners-------------------------------------------\\

public class GUIHandler implements ActionListener, DocumentListener, ListSelectionListener{

	public void actionPerformed(ActionEvent event) {
	}

	//Action listeners for the search bar
	public void changedUpdate(DocumentEvent event) {
		searchJList();
	}

	public void insertUpdate(DocumentEvent event) {
		searchJList();
	}

	public void removeUpdate(DocumentEvent event) {
		searchJList();
	}

	//Update the information panel on the right 
	public void valueChanged(ListSelectionEvent event) {
		if(!event.getValueIsAdjusting() && !scrollList.isSelectionEmpty()){
			viewNum.setText(filter.elementAt(((scrollList.getSelectedIndex()))).getNumOut());
			viewPrice.setText(filter.elementAt((scrollList.getSelectedIndex())).getPrice());
			viewISBN.setText(filter.elementAt((scrollList.getSelectedIndex())).getISBN());

		}
	}
 
}

}
