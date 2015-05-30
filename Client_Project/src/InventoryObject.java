/**
 * This class represents a book in the inventory.
 * Includes the books name, grade, room, number, price,
 * and ISBN.
 * @author Cosmic42 (Wally)
 *
 */
public class InventoryObject {
	private String name;
	private String grade;
	private String room;
	private String num;
	private String price;
	private String ISBN;
	/**
	 * 
	 * @param line
	 */
	public InventoryObject(String line){
		String[] data = line.split("\t");
		name = data[0];
		grade = data[1];
		room = data[2];
		num = data[3];
		price = data[4];
		ISBN = data[5];
	}
	/**
	 * Returns the name as the conversion from object to string
	 * 
	 */
	public String toString(){
		return name;
	}
	/**
	 * 
	 * @return the name of the book
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * 
	 * @param name	Name of the book
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 
	 * @return the grade level of the book.
	 */
	public String getGrade() {
		return grade;
	}
	/**
	 * 
	 * @param grade	Grade of the book
	 */
	public void setGrade(String grade) {
		this.grade = grade;
	}
	/**
	 * Returns the room # of the book
	 * @return
	 */
	public String getRoom() {
		return room;
	}
	/**
	 * 
	 * @param room
	 */
	public void setRoom(String room) {
		this.room = room;
	}
	/**
	 * 
	 * @return the total number of books.
	 */
	public String getNum() {
		return num;
	}
	/**
	 * 
	 * @param num
	 */
	public void setNum(String num) {
		this.num = num;
	}
	/**
	 * 
	 * @return the price of the book.
	 */
	public String getPrice() {
		return price;
	}
	/**
	 * 
	 * @param price
	 */
	public void setPrice(String price) {
		this.price = price;
	}
	/**
	 * 
	 * @return the ISBN # of the books.
	 */
	public String getISBN() {
		return ISBN;
	}
	/**
	 * 
	 * @param iSBN
	 */
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}
	/**
	 * 
	 * @param other
	 * @return
	 */
	public boolean equals(InventoryObject other){
		String otherTitle = other.getName();
		String current = this.getName();
		if(current.equals(otherTitle))
			return true;
		else
			return false;
	}
}