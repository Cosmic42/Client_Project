import java.util.ArrayList;


public class Teacher{
	
	public String name, username, password;
	
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

	public Teacher(String name, String username, String password){
		this.name = name;
		this.username = username;
		this.password = password;
	}
	public String toString(){
		return name;
	}
}
