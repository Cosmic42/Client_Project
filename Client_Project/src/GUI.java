import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.*;

import javax.swing.event.*;
import javax.swing.*;

public class GUI extends JFrame {
	

	private JList scrollList;
	private ArrayList<InventoryObject> database;
	private JTabbedPane tabs;
	private JPanel inventoryTab, checkOutTab, modInventoryTab, viewBooks, confirmPanel;
	private DefaultListModel filter;
	private JTextField search, viewNum, viewPrice, viewISBN, viewRoom, viewName; 
	private JTextField modNum, modPrice, modISBN, modRoom;
	private JTextField checkOut;
	private JButton checkOutButton, deleteBook, addBook, plusBook, minusBook;
	
	private GUIHandler handler;

	/**
	 * The constructor for the GUI that initializes all fields.
	 * Also calls the methods to format each JPanel in the GUI
	 * @throws IOException
	 */
	public GUI() throws IOException{
		super("Book Inventory");
		
		handler = new GUIHandler();
		
		FileReader fileReader = new FileReader("Database/database.txt");
		BufferedReader bufferReader = new BufferedReader(fileReader);
		database = new ArrayList<InventoryObject>();
        String line;
		while((line = bufferReader.readLine()) != null)
            database.add(new InventoryObject(line));
      
        bufferReader.close();

        this.setLayout(new GridLayout(1, 2));
        filter = new DefaultListModel();
      
        for(InventoryObject i:database)
            filter.addElement(i);
        
		scrollList = new JList(filter);
		
		tabs = new JTabbedPane();
		inventoryTab = new JPanel(); viewBooks = new JPanel(); viewBooks = new JPanel();
		checkOutTab = new JPanel();
		modInventoryTab = new JPanel();
		search = new JTextField(); viewNum = new JTextField(4); viewPrice = new JTextField(4); 
		viewISBN = new JTextField(10); viewRoom = new JTextField(4); viewName = new JTextField();
		modNum = new JTextField(4); modPrice = new JTextField(4); checkOut = new JTextField(4);
		modISBN = new JTextField(10); modRoom = new JTextField(4);
		checkOutButton = new JButton("Request"); deleteBook = new JButton("Delete Book");
		addBook = new JButton("Add Book"); plusBook = new JButton("+"); minusBook = new JButton("-");
		
		setInventoryTab();
		setCheckOutTab();
		setModifyTab();
		add(viewBooks);
		add(tabs);

	}

//----------------------------------------------------Panel Setup-----------------------------------------------------\\
	/**
	 * Sets up the tab for the Inventory viewing panel
	 * Utilizes both GridBagLayout and GroupLayout for formatting
	 * the GUI
	 */
	public void setInventoryTab() {		
		JPanel viewData = new JPanel();
		JLabel label1 = new JLabel("# of Books: ");
		JLabel label2 = new JLabel("Stored in Room: ");
		JLabel label3 = new JLabel("Price: ");
		JLabel label4 = new JLabel("ISBN: ");

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
		
		search.getDocument().addDocumentListener(handler);
		viewBooks.add(search,c);
		setGrid(c, 0, 0, 0, 0);
		viewBooks.add(new JLabel("Search"),c);

		c.fill = GridBagConstraints.BOTH;
		setGrid(c, 0, 2, 1, 1);
		viewBooks.add(list, c);

		viewData.setLayout(new GridBagLayout());
		JLabel panelTitle = new JLabel(" Book Information");
		panelTitle.setFont((new Font("", Font.PLAIN, 20)));
		setGrid(c, 0, 0, 0, 0);
		viewData.add(panelTitle, c);
		
		viewNum.setEditable(false);
		viewRoom.setEditable(false);
		viewPrice.setEditable(false);
		viewISBN.setEditable(false);

		JPanel information = new JPanel();
		GroupLayout layout = new GroupLayout(information);
		information.setLayout(layout);

		layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                .addComponent(label1)
                .addComponent(label2)
                .addComponent(label3)
                .addComponent(label4))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(viewNum)
                .addComponent(viewRoom)
                .addComponent(viewPrice)
                .addComponent(viewISBN)));
       
        layout.setVerticalGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup()
                .addComponent(label1)
                .addComponent(viewNum))
            .addGroup(layout.createParallelGroup()
                .addComponent(label2)
                .addComponent(viewRoom))
            .addGroup(layout.createParallelGroup()
                .addComponent(label3)
                .addComponent(viewPrice))
            .addGroup(layout.createParallelGroup()
                    .addComponent(label4)
                    .addComponent(viewISBN)));

		setGrid(c, 0, 1, 1, 0);
        viewData.add(information, c);
		setGrid(c, 0, 2, 0, 1);
		viewData.add(new JLabel(""), c);
		inventoryTab.setLayout(new GridLayout(1, 2));		
		inventoryTab.add(viewData);
		
		tabs.addTab("Inventory", inventoryTab);
						
	}
	/**
	 * Sets up the Check-out tab in the GUI
	 * Uses GridBagLayout and GroupLayout
	 */
	public void setCheckOutTab(){
		JPanel modData = new JPanel();
		JLabel label1 = new JLabel("# of Book: ");
		checkOutButton.addActionListener(handler);
		
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;

		viewName.setEditable(false);
		
		modData.setLayout(new GridBagLayout());
		JLabel panelTitle = new JLabel(" Check out Books");
		panelTitle.setFont((new Font("", Font.PLAIN, 20)));
		setGrid(c, 0, 0, 0, 0);
		modData.add(panelTitle, c);
		setGrid(c, 0, 1, 0, 0);
		modData.add(viewName, c);
		
		JPanel information = new JPanel();
		GroupLayout layout = new GroupLayout(information);
		information.setLayout(layout);

		layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addComponent(label1)
                .addComponent(checkOut)
                .addComponent(checkOutButton));
        	
        layout.setVerticalGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup()
                .addComponent(label1)
                .addComponent(checkOut)
                .addComponent(checkOutButton)));
                
		setGrid(c, 0, 2, 1, 0);
        modData.add(information, c);
		setGrid(c, 0, 3, 0, 1);
		modData.add(new JLabel(""), c);
		checkOutTab.setLayout(new GridLayout(1, 2));		
		checkOutTab.add(modData);
		
		tabs.addTab("Check-Out", checkOutTab);
				
	}
	/**
	 * Sets up the Modifying inventory tab
	 * Utilizes GridBagLayout and GroupLayout
	 * Essentially a clone of setInventoryTab,
	 * however it uses different fields
	 */
	public void setModifyTab(){
		JPanel modData = new JPanel();
		JLabel label1 = new JLabel("# of Books: ");
		JLabel label2 = new JLabel("Stored in Room: ");
		JLabel label3 = new JLabel("Price: ");
		JLabel label4 = new JLabel("ISBN: ");
		
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		setGrid(c, 0, 2, 1, 1);

		//Display Information. The next blocks of code is just adding JLabels and JTextFields. Uses GridBagLayout and GroupLayout		
		modData.setLayout(new GridBagLayout());
		JLabel panelTitle = new JLabel(" Modify Book Information");
		panelTitle.setFont((new Font("", Font.PLAIN, 20)));
		setGrid(c, 0, 0, 0, 0);
		modData.add(panelTitle, c);
		
        modNum.addActionListener(handler);
        modRoom.addActionListener(handler);
        modPrice.addActionListener(handler);
        modISBN.addActionListener(handler);
        deleteBook.addActionListener(handler);
        
		//GroupLayout to set the information
		JPanel information = new JPanel();
		GroupLayout layout = new GroupLayout(information);
		information.setLayout(layout);

		layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                .addComponent(label1)
                .addComponent(label2)
                .addComponent(label3)
                .addComponent(label4))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(modNum)
                .addComponent(modRoom)
                .addComponent(modPrice)
                .addComponent(modISBN))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(plusBook))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(minusBook)));
        
        layout.setVerticalGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup()
                .addComponent(label1)
                .addComponent(modNum)
                .addComponent(plusBook)
                .addComponent(minusBook))
            .addGroup(layout.createParallelGroup()
                .addComponent(label2)
                .addComponent(modRoom))
            .addGroup(layout.createParallelGroup()
                .addComponent(label3)
                .addComponent(modPrice))
            .addGroup(layout.createParallelGroup()
                 .addComponent(label4)
                 .addComponent(modISBN)));

		setGrid(c, 0, 1, 1, 0);
        modData.add(information, c);
		setGrid(c, 0, 2, 1, 0);
		modData.add(addBook, c);
		setGrid(c, 0, 3, 1, 0);
		modData.add(deleteBook, c);
		setGrid(c, 0, 4, 1, 1);
		modData.add(new JLabel(""), c);
		modInventoryTab.setLayout(new GridLayout(1, 2));		
		modInventoryTab.add(modData);
		
		tabs.addTab("Modify Inventory", modInventoryTab);
				
	}

	/**
	 * Sets out the constraints for the GridBagLayout
	 * with the given parameters
	 * @param c
	 * @param gridx
	 * @param gridy
	 * @param weightx
	 * @param weighty
	 */
	public void setGrid(GridBagConstraints c, int gridx, int gridy, double weightx, double weighty){
		c.weightx = weightx; c.weighty = weighty; c.gridx = gridx; c.gridy = gridy;
	}
		
	/**
	 * Searches through the JList to find matches
	 * Updates the list on the GUI
	 */
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
	
	/**
	 * Clears all text fields
	 */
	public void refresh(){
		viewNum.setText(""); modNum.setText("");
		viewRoom.setText(""); modRoom.setText("");
		viewPrice.setText(""); modPrice.setText("");
		viewISBN.setText(""); modISBN.setText("");

	}
	

public class GUIHandler implements ActionListener, DocumentListener, ListSelectionListener{

	/**
	 * Action Listeners for buttons and text fields.
	 * Includes setting text, handling requests, and
	 * updating database
	 */
	
	public void actionPerformed(ActionEvent event) {
		int choice = 0;
		if(event.getSource() == modNum)
			database.get(scrollList.getSelectedIndex()).setNum(modNum.getText());
		if(event.getSource() == modRoom)
			database.get(scrollList.getSelectedIndex()).setNum(modRoom.getText());
		if(event.getSource() == modPrice)
			database.get(scrollList.getSelectedIndex()).setNum(modPrice.getText());
		if(event.getSource() == modISBN)
			database.get(scrollList.getSelectedIndex()).setNum(modISBN.getText());
		
		if(event.getSource() == deleteBook){
			if(!scrollList.isSelectionEmpty()){
				choice = JOptionPane.showConfirmDialog(null, "Delete Book from Database?\nWarning: Cannot be undone"
						, "Confirm", JOptionPane.YES_NO_OPTION);
				if(choice == 0){
					database.remove(scrollList.getSelectedIndex());
					searchJList();
				}
			}
		}
		
		if(event.getSource() == checkOutButton){
			if(checkOut.getText().matches("[-+]?\\d*\\.?\\d+") && !scrollList.isSelectionEmpty()){
				choice = JOptionPane.showConfirmDialog(null, "You are about to order " + checkOut.getText() + " books.\nIs this correct?"
						, "Confirm", JOptionPane.YES_NO_OPTION);
				if(choice == 0)
					System.out.println("K");
				else
					System.out.println("get out then");
			}else{
				JOptionPane.showMessageDialog(null, "Error: No books selected \n" +
						"or no valid number entered", "Error", JOptionPane.ERROR_MESSAGE);
			}
			
		}
			
		viewNum.setText(modNum.getText());
		viewRoom.setText(modRoom.getText());
		viewPrice.setText(modPrice.getText());
		viewISBN.setText(modISBN.getText());

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

	/**
	 * On select of the JList, set the
	 * database to the selected element
	 */
	public void valueChanged(ListSelectionEvent event) {
		if(!event.getValueIsAdjusting() && !scrollList.isSelectionEmpty()){
			viewNum.setText(((InventoryObject) filter.elementAt(((scrollList.getSelectedIndex())))).getNum());
			viewRoom.setText(((InventoryObject) filter.elementAt(((scrollList.getSelectedIndex())))).getRoom());
			viewPrice.setText(((InventoryObject) filter.elementAt((scrollList.getSelectedIndex()))).getPrice());
			viewISBN.setText(((InventoryObject) filter.elementAt((scrollList.getSelectedIndex()))).getISBN());
			viewName.setText(((InventoryObject) filter.elementAt(((scrollList.getSelectedIndex())))).getName());

			modNum.setText(((InventoryObject) filter.elementAt(((scrollList.getSelectedIndex())))).getNum());
			modRoom.setText(((InventoryObject) filter.elementAt(((scrollList.getSelectedIndex())))).getRoom());
			modPrice.setText(((InventoryObject) filter.elementAt((scrollList.getSelectedIndex()))).getPrice());
			modISBN.setText(((InventoryObject) filter.elementAt((scrollList.getSelectedIndex()))).getISBN());

		}
	}
 
}

}
