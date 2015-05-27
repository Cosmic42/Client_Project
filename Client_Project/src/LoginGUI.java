import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.*;
import javax.swing.*;

public class LoginGUI extends JFrame {
	
	private ArrayList<Teacher> users;
	private JPanel login;
	private JTextField username;
	private JPasswordField password; 
	private JButton loginButton;
	private Teacher user;
	private GUIHandler handler;

	/**
	 * The constructor for the login GUI that initializes the fields.
	 * @throws IOException
	 */
	public LoginGUI() throws IOException{
		super("Book Inventory");
		
		FileReader fileReader = new FileReader("Database/users.txt");
		BufferedReader bufferReader = new BufferedReader(fileReader);
		users = new ArrayList<Teacher>();
        String line;
		while((line = bufferReader.readLine()) != null)
            users.add(new Teacher(line.split("\t")[0], line.split("\t")[1], line.split("\t")[2]));
      
        bufferReader.close();
        
		handler = new GUIHandler();
		login = new JPanel();
		username = new JTextField(); password = new JPasswordField();
		loginButton = new JButton("Login");
		username.addActionListener(handler);
		password.addActionListener(handler);
		loginButton.addActionListener(handler);

		loginScreen();
		add(login);
	}


	
	public void loginScreen(){
		JLabel label1 = new JLabel("Username: ");
		JLabel label2 = new JLabel("Password: ");
		JLabel label3 = new JLabel("");

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		        

		GroupLayout layout = new GroupLayout(login);
		login.setLayout(layout);

		layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                	.addComponent(label1)
                	.addComponent(label2)
                	.addComponent(label3))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(username)
                    .addComponent(password)
                    .addComponent(loginButton)));
            
            layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup()
                	.addComponent(label1)
                	.addComponent(username))
                .addGroup(layout.createParallelGroup()
                	.addComponent(label2)
                	.addComponent(password))
                .addGroup(layout.createParallelGroup()
                    .addComponent(label3)
                  	.addComponent(loginButton)));
	}
	
	public boolean checkUser(String username, String password){
		for(Teacher teacher : users)
			if(teacher.getUsername().toLowerCase().equals(username.toLowerCase()) && teacher.getPassword().equals(password)){
				user = teacher;
				return true;
			}
		return false;
	}
	
	public void setInventoryGUI() throws IOException{
		this.dispose();
		
		GUI inventoryGUI = new GUI(user);
		ImageIcon logo = new ImageIcon("src/NS_Icon.png");
		
		inventoryGUI.setIconImage(logo.getImage());
		inventoryGUI.setSize(800, 500);
		inventoryGUI.setVisible(true);
		inventoryGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		inventoryGUI.setResizable(false);
		inventoryGUI.setLocationRelativeTo(null);


	}

public class GUIHandler implements ActionListener{

	/**
	 * Action Listeners for buttons and text fields.
	 */
	
	public void actionPerformed(ActionEvent event){
		if(checkUser(username.getText(), new String(password.getPassword())))
			try {
				setInventoryGUI();
			} catch (IOException e) {
				e.printStackTrace();
			}
		else
			JOptionPane.showMessageDialog(null, "Error: Invalid Login", "Error", JOptionPane.ERROR_MESSAGE);

	}
 
}
}