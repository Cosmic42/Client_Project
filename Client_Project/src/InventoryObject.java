
public class InventoryObject {
	private String name;
	private String grade;
	private String room;
	private String num;
	private String price;
	private String ISBN;
	
	public InventoryObject(String line){
		String[] data = line.split("\t");
		name = data[0];
		grade = data[1];
		room = data[2];
		num = data[3];
		price = data[4];
		ISBN = data[5];
	}
	public String toString(){
		return name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getRoom() {
		return room;
	}
	public void setRoom(String room) {
		this.room = room;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getISBN() {
		return ISBN;
	}
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}
}
