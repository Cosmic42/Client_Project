import java.util.ArrayList;


public class Teacher{
	
	private String name, username, password;
	private boolean admin;
	
	public boolean isAdmin(){
		return admin;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public Teacher(String name, String username, String password, boolean admin){
		this.name = name;
		this.username = username;
		this.password = password;
		this.admin = admin;
	}
	public String toString(){
		return name;
	}
}
