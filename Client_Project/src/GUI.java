import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.*;

import javax.swing.event.*;
import javax.swing.*;

public class GUI extends JFrame {
	
	private JList scrollList, scrollRequest, scrollReply;
	private ArrayList<InventoryObject> database;
	private ArrayList<Notification> requestList;
	private ArrayList<Message> replyList;
	private JTabbedPane tabs;
	private JPanel inventoryTab, checkOutTab, modInventoryTab, viewBooks;
	private DefaultListModel filter, requests, replies;
	private JTextField search, viewNum, viewPrice, viewISBN, viewRoom, viewName, viewGrade; 
	private JTextField modNum, modPrice, modISBN, modRoom, modName, modGrade;
	private JTextField addNum, addPrice, addISBN, addRoom, addName, addGrade;
	private JTextField checkOut, requestRoom, viewRequestNum, viewRequestTitle, viewRequestRoom, viewIfBorrow;
	private JTextField ifAccepted, replyCopies, replyTitle, replyIfBorrow;
	private JButton checkOutButton, deleteBook, addBook, plusBook, minusBook, viewRequests, accept, decline, returnRequest;
	private JButton viewMessages;
	private Teacher user;
	private GUIHandler handler;

	/**
	 * The constructor for the GUI that initializes all fields.
	 * Also calls the methods to format each JPanel in the GUI
	 * @throws IOException
	 */
	public GUI(Teacher user) throws IOException{
		super("Book Inventory");
		
		this.user = user;
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
		
		
		
		
		FileReader fileReaderRequest = new FileReader("Database/Notifications.txt");
		BufferedReader bufferReaderRequest = new BufferedReader(fileReaderRequest);
		requestList = new ArrayList<Notification>();
		String lineR;
		while((lineR = bufferReaderRequest.readLine()) != null)
			requestList.add(new Notification(lineR));
		
		bufferReaderRequest.close();
		
		requests = new DefaultListModel();
		
		for(Notification i: requestList)
			requests.addElement(i);
                
		scrollRequest = new JList(requests);
		
		scrollRequest.addListSelectionListener(handler);
		scrollRequest.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		
		
		
		FileReader fileReaderMessage = new FileReader("Database/ReturnMsg.txt");
		BufferedReader bufferReaderMessage = new BufferedReader(fileReaderMessage);
		replyList = new ArrayList<Message>();
		String lineMessage;
		while((lineMessage = bufferReaderMessage.readLine()) != null)
			replyList.add(new Message(lineMessage));
		
		bufferReader.close();
		
		replies = new DefaultListModel();
		
		for(Message i: replyList){
			if(i.getTeacher().equals(user.toString()))
				replies.addElement(i);
		}
		
		scrollReply = new JList(replies);
		
		scrollReply.addListSelectionListener(handler);
		scrollReply.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		
		
		
		tabs = new JTabbedPane();
		inventoryTab = new JPanel(); viewBooks = new JPanel();
		checkOutTab = new JPanel();
		modInventoryTab = new JPanel();
		search = new JTextField(); viewNum = new JTextField(); viewPrice = new JTextField(); 
		viewISBN = new JTextField(); viewRoom = new JTextField(); viewName = new JTextField();
		viewGrade = new JTextField();
		modNum = new JTextField(); modPrice = new JTextField(); checkOut = new JTextField();
		modISBN = new JTextField(); modRoom = new JTextField(); modName = new JTextField();
		modGrade = new JTextField(); requestRoom = new JTextField();
		addNum = new JTextField(); addPrice = new JTextField(); addName = new JTextField();
		addISBN = new JTextField(); addRoom = new JTextField(); addGrade = new JTextField();
		checkOutButton = new JButton("Request"); deleteBook = new JButton("Delete Book");
		addBook = new JButton("Add Book"); plusBook = new JButton("+"); minusBook = new JButton("-");
		viewRequests = new JButton("View Requests"); accept = new JButton("Accept"); decline = new JButton("Decline");
		returnRequest = new JButton("Return"); 
		viewMessages = new JButton("View Replies");
		accept.addActionListener(handler);
		decline.addActionListener(handler);
		ifAccepted = new JTextField(); replyCopies = new JTextField(); replyTitle = new JTextField(); replyIfBorrow = new JTextField();
		
		setInventoryTab();
		setCheckOutTab();
		if(user.isAdmin())
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
		JLabel label5 = new JLabel("Grade: ");

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
		viewGrade.setEditable(false);
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
                .addComponent(label5)
                .addComponent(label2)
                .addComponent(label3)
                .addComponent(label4))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(viewNum)
                .addComponent(viewGrade)
                .addComponent(viewRoom)
                .addComponent(viewPrice)
                .addComponent(viewISBN)));
       
        layout.setVerticalGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup()
                .addComponent(label1)
                .addComponent(viewNum))
            .addGroup(layout.createParallelGroup()
                .addComponent(label5)
                .addComponent(viewGrade))
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
		JLabel label1 = new JLabel("# of Books: ");
		JLabel label2 = new JLabel("To/From Room: ");
		checkOutButton.addActionListener(handler);
		viewMessages.addActionListener(handler);
		
		
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;

		viewName.setEditable(false);
		
		modData.setLayout(new GridBagLayout());
		JLabel panelTitle = new JLabel(" Check out Books");
		panelTitle.setFont((new Font("", Font.PLAIN, 20)));
		setGrid(c, 0, 0, 1, 0);
		modData.add(panelTitle, c);
		setGrid(c, 0, 1, 1, 0);
		modData.add(viewName, c);
		
		JPanel information = new JPanel();
		GroupLayout layout = new GroupLayout(information);
		information.setLayout(layout);

		layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
        		.addComponent(label1)
        		.addComponent(label2))
        	.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                .addComponent(checkOut)
                .addComponent(requestRoom))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
            	.addComponent(checkOutButton)
            	.addComponent(returnRequest)));
        

        layout.setVerticalGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup()
                .addComponent(label1)
                .addComponent(checkOut)
                .addComponent(checkOutButton))
            .addGroup(layout.createParallelGroup()
                .addComponent(label2)
                .addComponent(requestRoom)
                .addComponent(returnRequest)));
 
                
		setGrid(c, 0, 2, 1, 0);
        modData.add(information, c);
        setGrid(c, 0, 3, 1, 0);
		modData.add(viewMessages, c);
        setGrid(c, 0, 4, 1, 0);
		modData.add(new JLabel(""), c);
		checkOutTab.setLayout(new GridLayout(1, 2));		
		checkOutTab.add(modData);
		
		tabs.addTab("Check-Out/Returns", checkOutTab);
				
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
        modGrade.addActionListener(handler);
        modRoom.addActionListener(handler);
        modPrice.addActionListener(handler);
        modISBN.addActionListener(handler);
        deleteBook.addActionListener(handler);
        addBook.addActionListener(handler);
        plusBook.addActionListener(handler);
        minusBook.addActionListener(handler);
        viewRequests.addActionListener(handler);
        
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
		setGrid(c, 0, 4, 1, 0);
		modData.add(viewRequests, c);
		setGrid(c, 0, 5, 1, 1);
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

		addData.setLayout(new GridBagLayout());
		JLabel panelTitle = new JLabel(" Add Book Information");
		panelTitle.setFont((new Font("", Font.PLAIN, 20)));
		setGrid(c, 0, 0, 0, 0);
		addData.add(panelTitle, c);	
		        
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
	
	public void updateJListRequest(){
		requests.clear();
		scrollRequest.removeListSelectionListener(handler);
		for(Notification i : requestList)
			requests.addElement(i);
		scrollRequest.addListSelectionListener(handler);
		scrollRequest.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		updateRequest();
		
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
		viewGrade.setText(""); modGrade.setText(""); addGrade.setText("");
	}
	
	public void refreshRequest(){
		viewRequestRoom.setText(""); viewRequestNum.setText(""); viewRequestTitle.setText(""); viewIfBorrow.setText(""); 
	}
	public void update(){
		if(!scrollList.isSelectionEmpty()){
			viewNum.setText(((InventoryObject) filter.elementAt(((scrollList.getSelectedIndex())))).getNum());
			viewRoom.setText(((InventoryObject) filter.elementAt(((scrollList.getSelectedIndex())))).getRoom());
			viewPrice.setText(((InventoryObject) filter.elementAt((scrollList.getSelectedIndex()))).getPrice());
			viewISBN.setText(((InventoryObject) filter.elementAt((scrollList.getSelectedIndex()))).getISBN());
			viewName.setText(((InventoryObject) filter.elementAt(((scrollList.getSelectedIndex())))).getName());
			viewGrade.setText(((InventoryObject) filter.elementAt(((scrollList.getSelectedIndex())))).getGrade());

			modName.setText(((InventoryObject) filter.elementAt(((scrollList.getSelectedIndex())))).getName());
			modNum.setText(((InventoryObject) filter.elementAt(((scrollList.getSelectedIndex())))).getNum());
			modRoom.setText(((InventoryObject) filter.elementAt(((scrollList.getSelectedIndex())))).getRoom());
			modPrice.setText(((InventoryObject) filter.elementAt((scrollList.getSelectedIndex()))).getPrice());
			modISBN.setText(((InventoryObject) filter.elementAt((scrollList.getSelectedIndex()))).getISBN());
			modGrade.setText(((InventoryObject) filter.elementAt((scrollList.getSelectedIndex()))).getGrade());
		}
	}
		
	public void updateRequest(){
		if(!scrollRequest.isSelectionEmpty()){
			viewRequestNum.setText(((Notification) requests.elementAt(((scrollRequest.getSelectedIndex())))).getStringNumBooks());
			viewRequestRoom.setText(((Notification) requests.elementAt(((scrollRequest.getSelectedIndex())))).getStringRoom());
			viewRequestTitle.setText(((Notification) requests.elementAt(((scrollRequest.getSelectedIndex())))).getTitle());
			viewIfBorrow.setText(((Notification) requests.elementAt(((scrollRequest.getSelectedIndex())))).getIfBorrow());
		}
	}
	
	public void updateReply(){
		if(!scrollReply.isSelectionEmpty()){
			replyCopies.setText(((Message) replies.elementAt(((scrollReply.getSelectedIndex())))).getCopies());
			ifAccepted.setText(((Message) replies.elementAt(((scrollReply.getSelectedIndex())))).getAcceptance());
			replyTitle.setText(((Message) replies.elementAt(((scrollReply.getSelectedIndex())))).getTitle());
			replyIfBorrow.setText(((Message) replies.elementAt(((scrollReply.getSelectedIndex())))).toBorrow());
		}
	}

	public JPanel setRequestGUI() throws IOException{
		JPanel requestPane = new JPanel();
		JPanel informationPane = new JPanel();

		JScrollPane list = new JScrollPane(scrollRequest, 
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		JLabel label1 = new JLabel("Title: ");
		JLabel label2 = new JLabel("# of Book: ");
		JLabel label3 = new JLabel("Room Number: ");
		JLabel label4 = new JLabel("Check-Out/ReturnS: ");

		viewRequestNum = new JTextField();
		viewRequestTitle = new JTextField();
		viewRequestRoom = new JTextField();
		viewIfBorrow = new JTextField();

		viewRequestNum.setEditable(false); viewRequestTitle.setEditable(false); 
		viewRequestRoom.setEditable(false); viewIfBorrow.setEditable(false);
		
		        
		GroupLayout layout = new GroupLayout(informationPane);
		informationPane.setLayout(layout);

		layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                	.addComponent(label1)
                	.addComponent(label2)
                	.addComponent(label3)
                	.addComponent(label4)
                	.addComponent(accept))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(viewRequestTitle)
                    .addComponent(viewRequestNum)
                    .addComponent(viewRequestRoom)
                    .addComponent(viewIfBorrow)
            		.addComponent(decline)));
           
        layout.setVerticalGroup(layout.createSequentialGroup()
        		.addGroup(layout.createParallelGroup()
                	.addComponent(label1)
                	.addComponent(viewRequestTitle))
                .addGroup(layout.createParallelGroup()
                	.addComponent(label2)
                	.addComponent(viewRequestNum))
                .addGroup(layout.createParallelGroup()
                    .addComponent(label3)
                    .addComponent(viewRequestRoom))
                .addGroup(layout.createParallelGroup()
                	.addComponent(label4)
                	.addComponent(viewIfBorrow))
                .addGroup(layout.createParallelGroup()
                    .addComponent(accept)
                    .addComponent(decline)));

		
		requestPane.setLayout(new GridLayout(1,2));
		requestPane.add(list);
		requestPane.add(informationPane);
		return requestPane;
	}
	
	public JPanel setReplyGUI() throws IOException{
		JPanel replyPane = new JPanel();
		JPanel informationPane = new JPanel();
		
		JScrollPane list = new JScrollPane(scrollReply, 
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		JLabel label1 = new JLabel("Borrow/Return: ");
		JLabel label2 = new JLabel("# of Books: ");
		JLabel label3 = new JLabel("Title: ");
		JLabel label4 = new JLabel("Approved/Declined: ");

		ifAccepted.setEditable(false);
		replyCopies.setEditable(false);
		replyTitle.setEditable(false);
		replyIfBorrow.setEditable(false);
		
		GroupLayout layout = new GroupLayout(informationPane);
		informationPane.setLayout(layout);

		layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
       
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                	.addComponent(label3)
                	.addComponent(label2)
                	.addComponent(label1)
                	.addComponent(label4))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(replyTitle)
                    .addComponent(replyCopies)
                    .addComponent(replyIfBorrow)
                    .addComponent(ifAccepted)));
           
        layout.setVerticalGroup(layout.createSequentialGroup()
        		.addGroup(layout.createParallelGroup()
                	.addComponent(label3)
                	.addComponent(replyTitle))
                .addGroup(layout.createParallelGroup()
                	.addComponent(label2)
                	.addComponent(replyCopies))
                .addGroup(layout.createParallelGroup()
                    .addComponent(label1)
                    .addComponent(replyIfBorrow))
                .addGroup(layout.createParallelGroup()
                	.addComponent(label4)
                	.addComponent(ifAccepted)));
        
		replyPane.setLayout(new GridLayout(1,2));
		replyPane.add(list);
		replyPane.add(informationPane);
		return replyPane;
	}

	
//--------------------------------------------------------------------------------------------------------------------------\\	
	
	
	public class GUIHandler implements ActionListener, DocumentListener, ListSelectionListener{
	
		/**
		 * Action Listeners for buttons and text fields.
		 * Includes setting text, handling requests, and
		 * updating database
		 */
		
		public void actionPerformed(ActionEvent event) {
			int choice = 0;
			if(!scrollList.isSelectionEmpty()){
				if(event.getSource() == modName){
					database.get(database.indexOf((InventoryObject) scrollList.getSelectedValue())).setNum(modName.getText());
					updateJList();
				}
				if(event.getSource() == modNum)
					if(modNum.getText().matches("[-+]?\\d*\\.?\\d+")
							&& !modNum.getText().contains(".") 
							&& Integer.parseInt(modNum.getText()) >=0)
						database.get(database.indexOf((InventoryObject) scrollList.getSelectedValue())).setNum(modNum.getText());
					else
						JOptionPane.showMessageDialog(null, "Error: Invalid Number", "Error", JOptionPane.ERROR_MESSAGE);
				
				if(event.getSource() == modRoom)
					database.get(database.indexOf((InventoryObject) scrollList.getSelectedValue())).setRoom(modRoom.getText());
				if(event.getSource() == modPrice)
					database.get(database.indexOf((InventoryObject) scrollList.getSelectedValue())).setPrice(modPrice.getText());
				if(event.getSource() == modISBN)
					database.get(database.indexOf((InventoryObject) scrollList.getSelectedValue())).setISBN(modISBN.getText());
				if(event.getSource() == modGrade)
					database.get(database.indexOf((InventoryObject) scrollList.getSelectedValue())).setGrade(modGrade.getText());
		
				if(event.getSource() == plusBook)
					database.get(database.indexOf((InventoryObject) scrollList.getSelectedValue())).setNum((Integer.parseInt(modNum.getText())+1 + ""));
				
				if(event.getSource() == minusBook && Integer.parseInt(modNum.getText())-1 >= 0)
					database.get(database.indexOf((InventoryObject) scrollList.getSelectedValue())).setNum((Integer.parseInt(modNum.getText())-1 + ""));
		
				if(event.getSource() == deleteBook){
					choice = JOptionPane.showConfirmDialog(null, "Delete Book from Database?\nWarning: Cannot be undone"
							, "Confirm", JOptionPane.YES_NO_OPTION);
					if(choice == 0){
						database.remove(database.indexOf((InventoryObject) scrollList.getSelectedValue()));
						updateJList();
						refresh();
					}
				}
			
				if(event.getSource() == checkOutButton){
					if(checkOut.getText().matches("[-+]?\\d*\\.?\\d+") && !checkOut.getText().contains(".") && Integer.parseInt(checkOut.getText()) > 0 
							&& Integer.parseInt(checkOut.getText()) <= Integer.parseInt(database.get(database.indexOf((InventoryObject) scrollList.getSelectedValue())).getNum())
							&& requestRoom.getText().matches("[-+]?\\d*\\.?\\d+") && !requestRoom.getText().contains(".")){
						choice = JOptionPane.showConfirmDialog(null, "You are about to order " + checkOut.getText() + " books.\n" +
								"Is this correct?", "Confirm", JOptionPane.YES_NO_OPTION);
						if(choice == 0){
							System.out.println("K"); 
						try
						{
						    //String filename= "S:\\SShared\\Stansbury\\Hannah\\Notifications.txt";
						    String filename= "Database/Notifications.txt";
						    FileWriter fw = new FileWriter(filename,true); //the true will append the new data
						    String title = database.get(database.indexOf((InventoryObject) scrollList.getSelectedValue())).getName();
						    fw.write(title + "; " + Integer.parseInt(checkOut.getText()) + "; "+ user + "; "+ requestRoom.getText() +"; "+ "to Borrow\n");//appends the string to the file
						    fw.close();
						}
						catch(IOException ioe)
						{
						    System.err.println("IOException: " + ioe.getMessage());
						}
					}
						else
							System.out.println("get out then");
					}else{
						JOptionPane.showMessageDialog(null, "Error: Not a valid number", "Error", JOptionPane.ERROR_MESSAGE);
					}
					
				}
				
				if(event.getSource() == returnRequest){
					if(checkOut.getText().matches("[-+]?\\d*\\.?\\d+") && !checkOut.getText().contains(".") && Integer.parseInt(checkOut.getText()) > 0 
							&& Integer.parseInt(checkOut.getText()) <= Integer.parseInt(database.get(database.indexOf((InventoryObject) scrollList.getSelectedValue())).getNum())
							&& requestRoom.getText().matches("[-+]?\\d*\\.?\\d+") && !requestRoom.getText().contains(".")){
						choice = JOptionPane.showConfirmDialog(null, "You are about to request a return of " + checkOut.getText() + " books.\n" +
								"Is this correct?", "Confirm", JOptionPane.YES_NO_OPTION);
						if(choice == 0){
							System.out.println("K"); 
						try
						{
						    //String filename= "S:\\SShared\\Stansbury\\Hannah\\Notifications.txt";
						    String filename= "Database/Notifications.txt";
						    FileWriter fw = new FileWriter(filename,true); //the true will append the new data
						    String title = database.get(database.indexOf((InventoryObject) scrollList.getSelectedValue())).getName();
						    fw.write(title + "; " + Integer.parseInt(checkOut.getText()) + "; "+ user + "; "+ requestRoom.getText() +"; "+ "to Return\n");//appends the string to the file
						    fw.close();
						}
						catch(IOException ioe)
						{
						    System.err.println("IOException: " + ioe.getMessage());
						}
					}
						else
							System.out.println("get out then");
					}else{
						JOptionPane.showMessageDialog(null, "Error: Not a valid number", "Error", JOptionPane.ERROR_MESSAGE);
					}
					
				}
			}
			
			if(event.getSource() == addBook){
				choice = JOptionPane.showConfirmDialog(null, addBookForm(), "Enter Book Information:", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
				if(choice == 0 && !addNum.getText().equals("") && addNum.getText().matches("[-+]?\\d*\\.?\\d+") && !addPrice.getText().equals("") &&
						!addGrade.getText().equals("") && !addName.getText().equals("") && 
						!addISBN.getText().equals("") && !addRoom.getText().equals("")){
					database.add(new InventoryObject(addName.getText() + "\t" +
													 addGrade.getText() + "\t" +
													 addRoom.getText() + "\t" +
													 addNum.getText() + "\t" +
													 addPrice.getText() + "\t" + 
													 addISBN.getText() + "\t"));
				}else if(!(choice == JOptionPane.CANCEL_OPTION)){
					JOptionPane.showMessageDialog(null, "Error: At least on field not\nfilled or invalid", "Error", JOptionPane.ERROR_MESSAGE);
				}
				updateJList();
				
				refresh();
			}
	
			if(event.getSource() == viewRequests){
				try {
					JOptionPane.showConfirmDialog(null, setRequestGUI(), "Requests:", JOptionPane.CLOSED_OPTION, JOptionPane.PLAIN_MESSAGE);
				} catch (HeadlessException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			if(event.getSource() == viewMessages){
				try {
					JOptionPane.showConfirmDialog(null, setReplyGUI(), "Replies:", JOptionPane.CLOSED_OPTION, JOptionPane.PLAIN_MESSAGE);
				} catch (HeadlessException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			if(event.getSource() == accept){
				JOptionPane.showMessageDialog(null, "Request Accepted", "Request", JOptionPane.INFORMATION_MESSAGE);
				Notification current = requestList.get(requestList.indexOf((Notification) scrollRequest.getSelectedValue()));
				int requested = current.getNumBooks();
				int foundIndex = -1;
				for(int i = 0; i < database.size(); i++){
					if (current.getTitle().equals(database.get(i).getName()))
						foundIndex = i;
				}
				System.out.println(foundIndex);
				InventoryObject requestedBook = database.get(foundIndex);
				int inventory = Integer.parseInt(requestedBook.getNum());
				int set;
				if (current.isBorrow())
					set = inventory-requested;
				else
					set = inventory+requested;
				if (foundIndex >= 0 && set >= 0){
					requestedBook.setNum(""+set);
					try
					{
					    //String filename= "S:\\SShared\\Stansbury\\Hannah\\Notifications.txt";
					    String filename= "Database/ReturnMsg.txt";
					    FileWriter fw = new FileWriter(filename,true); //the true will append the new data
					    Notification noti = requestList.get(requestList.indexOf((Notification) scrollRequest.getSelectedValue())); 
					    String borrow;
					    if(requestList.get(requestList.indexOf((Notification) scrollRequest.getSelectedValue())).isBorrow())
					    	borrow = "to Borrow";
					    else
					    	borrow = "to Return";
					    fw.write(noti.getTitle() + "; " + noti.getNumBooks() + "; "+ user + "; "+ noti.getRoom() +"; "+ borrow + "; " + "true\n");//appends the string to the file
					    fw.close();
					}
					catch(IOException ioe)
					{
					    System.err.println("IOException: " + ioe.getMessage());
					}
					requestList.remove(requestList.indexOf((Notification) scrollRequest.getSelectedValue()));
					updateJListRequest();
					refreshRequest();
				}
				else
					JOptionPane.showMessageDialog(null, "Error: Requested amount exceeded current inventory", "Error", JOptionPane.ERROR_MESSAGE);
			}
				

			if(event.getSource() == decline){
				JOptionPane.showMessageDialog(null, "Request Declined", "Request", JOptionPane.INFORMATION_MESSAGE);
				try
				{
				    //String filename= "S:\\SShared\\Stansbury\\Hannah\\Notifications.txt";
				    String filename= "Database/ReturnMsg.txt";
				    FileWriter fw = new FileWriter(filename,true); //the true will append the new data
				    Notification noti = requestList.get(requestList.indexOf((Notification) scrollRequest.getSelectedValue())); 
				    String borrow;
				    if(requestList.get(requestList.indexOf((Notification) scrollRequest.getSelectedValue())).isBorrow())
				    	borrow = "to Borrow";
				    else
				    	borrow = "to Return";
				    fw.write(noti.getTitle() + "; " + noti.getNumBooks() + "; "+ user + "; "+ noti.getRoom() +"; "+ borrow + "; " + "false\n");//appends the string to the file
				    fw.close();
				}
				catch(IOException ioe)
				{
				    System.err.println("IOException: " + ioe.getMessage());
				}
				requestList.remove(requestList.indexOf((Notification) scrollRequest.getSelectedValue()));
				updateJListRequest();
				refreshRequest();
			}
			

				
			update();
		}
	
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
			if(event.getSource() == scrollList){
				if(!event.getValueIsAdjusting() && !scrollList.isSelectionEmpty()){
					update();
				}
			}
				
			if(event.getSource() == scrollRequest){
				if(!event.getValueIsAdjusting() && !scrollRequest.isSelectionEmpty()){
					updateRequest();
				}
			}
			
			if(event.getSource() ==  scrollReply){
				if(!event.getValueIsAdjusting() && !scrollReply.isSelectionEmpty()){
					updateReply();
				}
			}	
			
		}
	 
	}
}