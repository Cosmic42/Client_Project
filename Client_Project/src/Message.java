
public class Message {
	private Notification notification;
	public boolean accepted;
	
	public Message(Notification given, boolean ifAccept){
		notification = given;
		accepted = ifAccept;
	}
	
	public Message(String line){
		int index = line.lastIndexOf(";");
		String noti = line.substring(0, index+1);
		String accept = line.substring(index+2);
		notification = new Notification(noti);
		if (accept.equals("true"))
			accepted = true;
		else
			accepted = false;
	}
	
	public String getCopies(){
		return notification.getStringNumBooks();
	}
	
	public String getTitle(){
		return notification.getTitle();
	}
	
	public String getAcceptance(){
		if(accepted)
			return "Accepted";
		else
			return "Declined";
	}
	
	public String getTeacher(){
		return notification.getTeacher();
	}
	
	public String toBorrow(){
		if (notification.isBorrow())
			return "Borrow";
		else
			return "Return";
	}
	
	public String toString(){
		return notification.getTitle() + " - " + notification.getNumBooks() + " copies";
	}
}
