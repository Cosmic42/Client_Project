import java.util.ArrayList;
import java.util.Arrays;


public class Notification {

	private int numBooks;
	private String teacher, title;
	private int room;
	boolean borrow;
	
	public Notification(String title, int copies, String lastName, int roomNum, boolean takeOut){
		this.title = title;
		numBooks = copies;
		teacher = lastName;
		room = roomNum;
		borrow = takeOut;
	}
	
	public Notification(String line){
		String[] info = line.split("; ");
		title = info[0];
		numBooks = numOfBook(info);
		teacher = info[2];
		room = roomNum(info);
		if (info[4].equals("to Borrow"))
			borrow = true;
		else
			borrow = false;
	}

	public int getNumBooks() {
		return numBooks;
		
	}
	
	public String getStringNumBooks(){
		return ""+numBooks;
	}

	public void setNumBooks(int numBooks) {
		this.numBooks = numBooks;
	}

	public String getTeacher() {
		return teacher;
	}

	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getRoom() {
		return room;
	}
	
	public String getStringRoom(){
		return ""+room;
	}
	
	public String getIfBorrow(){
		if (borrow)
			return "Request for Check-Out";
		else
			return "Request for Return";
	}

	public void setRoom(int room) {
		this.room = room;
	}

	public boolean isBorrow() {
		return borrow;
	}

	public void setBorrow(boolean borrow) {
		this.borrow = borrow;
	}
	
	  private int numOfBook(String[] arr){
		   String temp = arr[1];
		   int r = Integer.parseInt(temp);
		   return r;
	   }
	   
	   private int roomNum(String[] arr){
		   String temp = arr[3];
		   return Integer.parseInt(temp);
	   }
	
	public String toString(){
		if (borrow)
			return (title + " - " + teacher);
		else
			return (title + " - " + teacher);
	}
	

}
	
	