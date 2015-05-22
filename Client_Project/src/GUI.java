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
	private JPanel inventoryTab, checkOutTab, modInventoryTab, viewBooks;
	private DefaultListModel filter;
	private JTextField search, viewNum, viewPrice, viewISBN, viewRoom, viewName; 
	private JTextField modNum, modPrice, modISBN, modRoom, modName, modGrade;
	private JTextField addNum, addPrice, addISBN, addRoom, addName, addGrade;
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
		inventoryTab = new JPanel(); viewBooks = new JPanel();
		checkOutTab = new JPanel();
		modInventoryTab = new JPanel();
		search = new JTextField(); viewNum = new JTextField(); viewPrice = new JTextField(); 
		viewISBN = new JTextField(); viewRoom = new JTextField(); viewName = new JTextField();
		modNum = new JTextField(); modPrice = new JTextField(); checkOut = new JTextField();
		modISBN = new JTextField(); modRoom = new JTextField(); modName = new JTextField();
		modGrade = new JTextField();
		addNum = new JTextField(); addPrice = new JTextField(); addName = new JTextField();
		addISBN = new JTextField(); addRoom = new JTextField(); addGrade = new JTextField();
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
		JLabel label1 = new JLabel("Title: ");
		JLabel label2 = new JLabel("# of Books: ");
		JLabel label3 = new JLabel("Stored in Room: ");
		JLabel label4 = new JLabel("Price: ");
		JLabel label5 = new JLabel("ISBN: ");
		JLabel label6 = new JLabel("Grade: ");

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		setGrid(c, 0, 2, 1, 1);

		//Display Information. The next blocks of code is just adding JLabels and JTextFields. Uses GridBagLayout and GroupLayout		
		modData.setLayout(new GridBagLayout());
		JLabel panelTitle = new JLabel(" Modify Book Information");
		panelTitle.setFont((new Font("", Font.PLAIN, 20)));
		setGrid(c, 0, 0, 0, 0);
		modData.add(panelTitle, c);
		
		modName.addActionListener(handler);
        modNum.addActionListener(handler);
        modRoom.addActionListener(handler);
        modPrice.addActionListener(handler);
        modISBN.addActionListener(handler);
        deleteBook.addActionListener(handler);
        addBook.addActionListener(handler);
        plusBook.addActionListener(handler);
        minusBook.addActionListener(handler);
        
		//GroupLayout to set the information
		JPanel information = new JPanel();
		GroupLayout layout = new GroupLayout(information);
		information.setLayout(layout);

		layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
            	.addComponent(label1)
            	.addComponent(label6)
            	.addComponent(label2)
                .addComponent(label3)
                .addComponent(label4)
                .addComponent(label5))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(modName)
                .addComponent(modGrade)
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
            	.addComponent(modName))
            .addGroup(layout.createParallelGroup()
            	.addComponent(label6)
            	.addComponent(modGrade))
            .addGroup(layout.createParallelGroup()
                .addComponent(label2)
                .addComponent(modNum)
                .addComponent(plusBook)
                .addComponent(minusBook))
            .addGroup(layout.createParallelGroup()
                .addComponent(label3)
                .addComponent(modRoom))
            .addGroup(layout.createParallelGroup()
                .addComponent(label4)
                .addComponent(modPrice))
            .addGroup(layout.createParallelGroup()
                 .addComponent(label5)
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

	
	public JPanel addBookForm(){
		JPanel addData = new JPanel();
		JLabel label1 = new JLabel("Title: ");
		JLabel label2 = new JLabel("Grade: ");
		JLabel label3 = new JLabel("# of Books: ");
		JLabel label4 = new JLabel("Stored in Room: ");
		JLabel label5 = new JLabel("Price: ");
		JLabel label6 = new JLabel("ISBN: ");

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;

		//Display Information. The next blocks of code is just adding JLabels and JTextFields. Uses GridBagLayout and GroupLayout		
		addData.setLayout(new GridBagLayout());
		JLabel panelTitle = new JLabel(" Add Book Information                                    ");
		panelTitle.setFont((new Font("", Font.PLAIN, 20)));
		setGrid(c, 0, 0, 0, 0);
		addData.add(panelTitle, c);
		        
		//GroupLayout to set the information
		JPanel information = new JPanel();
		GroupLayout layout = new GroupLayout(information);
		information.setLayout(layout);

		layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
//        formLayout(layout, label1, label2, label3, label4, label5, label6,
//				 addName, addGrade, addNum, addRoom, addPrice, addISBN);
        
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                	.addComponent(label1)
                	.addComponent(label6)
                	.addComponent(label2)
                    .addComponent(label3)
                    .addComponent(label4)
                    .addComponent(label5))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(addName)
                    .addComponent(addGrade)
                	.addComponent(addNum)
                    .addComponent(addRoom)
                    .addComponent(addPrice)
                    .addComponent(addISBN)));
            
            layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup()
                	.addComponent(label1)
                	.addComponent(addName))
                .addGroup(layout.createParallelGroup()
                	.addComponent(label6)
                	.addComponent(addGrade))
                .addGroup(layout.createParallelGroup()
                    .addComponent(label2)
                    .addComponent(addNum))
                .addGroup(layout.createParallelGroup()
                    .addComponent(label3)
                    .addComponent(addRoom))
                .addGroup(layout.createParallelGroup()
                    .addComponent(label4)
                    .addComponent(addPrice))
                .addGroup(layout.createParallelGroup()
                     .addComponent(label5)
                     .addComponent(addISBN)));

		setGrid(c, 0, 1, 1, 0);
		addData.add(information, c);
		setGrid(c, 0, 2, 1, 0);
		return addData;
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
        scrollList.addListSelectionListener(handler);
		scrollList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		refresh();
	}
	public void updateJList(){
        int index = scrollList.getSelectedIndex();
		filter.clear();
		scrollList.removeListSelectionListener(handler);
        for(InventoryObject i : database)
			if(i.getName().toUpperCase().contains(search.getText().toUpperCase()))
				filter.addElement(i);
        scrollList.addListSelectionListener(handler);
		scrollList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollList.setSelectedIndex(index);
		update();
	}
	
	/**
	 * Clears all text fields
	 */
	public void refresh(){
		viewNum.setText(""); modNum.setText(""); addNum.setText("");
		viewRoom.setText(""); modRoom.setText(""); addRoom.setText("");
		viewPrice.setText(""); modPrice.setText(""); addPrice.setText("");
		viewISBN.setText(""); modISBN.setText(""); addISBN.setText("");
		viewName.setText(""); modName.setText(""); addName.setText("");
		modGrade.setText(""); addGrade.setText("");
	}
	public void update(){
		if(!scrollList.isSelectionEmpty()){
			viewNum.setText(((InventoryObject) filter.elementAt(((scrollList.getSelectedIndex())))).getNum());
			viewRoom.setText(((InventoryObject) filter.elementAt(((scrollList.getSelectedIndex())))).getRoom());
			viewPrice.setText(((InventoryObject) filter.elementAt((scrollList.getSelectedIndex()))).getPrice());
			viewISBN.setText(((InventoryObject) filter.elementAt((scrollList.getSelectedIndex()))).getISBN());
			viewName.setText(((InventoryObject) filter.elementAt(((scrollList.getSelectedIndex())))).getName());

			modName.setText(((InventoryObject) filter.elementAt(((scrollList.getSelectedIndex())))).getName());
			modNum.setText(((InventoryObject) filter.elementAt(((scrollList.getSelectedIndex())))).getNum());
			modRoom.setText(((InventoryObject) filter.elementAt(((scrollList.getSelectedIndex())))).getRoom());
			modPrice.setText(((InventoryObject) filter.elementAt((scrollList.getSelectedIndex()))).getPrice());
			modISBN.setText(((InventoryObject) filter.elementAt((scrollList.getSelectedIndex()))).getISBN());
			modGrade.setText(((InventoryObject) filter.elementAt((scrollList.getSelectedIndex()))).getGrade());

		}
	}
	

public class GUIHandler implements ActionListener, DocumentListener, ListSelectionListener{

	/**
	 * Action Listeners for buttons and text fields.
	 * Includes setting text, handling requests, and
	 * updating database
	 */
	
	public void actionPerformed(ActionEvent event) {
		int choice = 0;
		if(event.getSource() == modName){
			database.get(scrollList.getSelectedIndex()).setName(modName.getText());
			updateJList();
		}
		if(event.getSource() == modNum)
			database.get(scrollList.getSelectedIndex()).setNum(modNum.getText());
		if(event.getSource() == modRoom)
			database.get(scrollList.getSelectedIndex()).setRoom(modRoom.getText());
		if(event.getSource() == modPrice)
			database.get(scrollList.getSelectedIndex()).setPrice(modPrice.getText());
		if(event.getSource() == modISBN)
			database.get(scrollList.getSelectedIndex()).setISBN(modISBN.getText());
		if(event.getSource() == modGrade)
			database.get(scrollList.getSelectedIndex()).setGrade(modGrade.getText());

		if(event.getSource() == plusBook && !scrollList.isSelectionEmpty())
			database.get(scrollList.getSelectedIndex()).setNum((Integer.parseInt(modNum.getText())+1 + ""));
		
		if(event.getSource() == minusBook && !scrollList.isSelectionEmpty())
			database.get(scrollList.getSelectedIndex()).setNum((Integer.parseInt(modNum.getText())-1 + ""));

		if(event.getSource() == deleteBook && !scrollList.isSelectionEmpty()){
			choice = JOptionPane.showConfirmDialog(null, "Delete Book from Database?\nWarning: Cannot be undone"
					, "Confirm", JOptionPane.YES_NO_OPTION);
			if(choice == 0){
				database.remove(scrollList.getSelectedIndex());
				updateJList();
				refresh();
			}
		}
		if(event.getSource() == addBook){
			choice = JOptionPane.showConfirmDialog(null, addBookForm(), "Enter Book Information:", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
			if(choice == 0 && !addNum.getText().equals("") && !addPrice.getText().equals("") &&
					!addGrade.getText().equals("") && !addName.getText().equals("") && 
					!addISBN.getText().equals("") && !addRoom.getText().equals("")){
				database.add(new InventoryObject(addName.getText() + "\t" +
												 addGrade.getText() + "\t" +
												 addRoom.getText() + "\t" +
												 addNum.getText() + "\t" +
												 addPrice.getText() + "\t" + 
												 addISBN.getText() + "\t"));
			}else if(!(choice == JOptionPane.CANCEL_OPTION)){
				JOptionPane.showMessageDialog(null, "Error: At least on field not\nfilled", "Error", JOptionPane.ERROR_MESSAGE);
			}
			updateJList();
			
			refresh();
		}

		if(event.getSource() == checkOutButton){
			if(checkOut.getText().matches("[-+]?\\d*\\.?\\d+") && !checkOut.getText().contains(".") && Integer.parseInt(checkOut.getText()) != 0 
					&& Integer.parseInt(checkOut.getText()) <= Integer.parseInt(database.get(scrollList.getSelectedIndex()).getNum())
					&& !scrollList.isSelectionEmpty()){
				choice = JOptionPane.showConfirmDialog(null, "You are about to order " + checkOut.getText() + " books.\n" +
						"Is this correct?", "Confirm", JOptionPane.YES_NO_OPTION);
				if(choice == 0)
					System.out.println("K");
				else
					System.out.println("get out then");
			}else{
				JOptionPane.showMessageDialog(null, "Error: No books selected \n" +
						"or no valid number entered", "Error", JOptionPane.ERROR_MESSAGE);
			}
			
		}
			
		update();
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
			update();
		}
	}
 
}
}