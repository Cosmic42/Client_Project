
public class InventoryObject {
	private String name;
	private String grade;
	private String room;
	private String numOut;
	private String price;
	private String ISBN;
	
	public InventoryObject(String line){
		String[] data = line.split("\t");
		name = data[0];
		grade = data[1];
		room = data[2];
		numOut = data[3];
		price = data[4];
		ISBN = data[5];
		System.out.println(name + "\t" + grade + "\t" + room + "\t" + numOut + "\t" + price + "\t" + ISBN);
	}
	public String toString(){
		return name + "\t" + grade + "\t" + room + "\t" + numOut + "\t" + price + "\t" + ISBN;
	}
}
