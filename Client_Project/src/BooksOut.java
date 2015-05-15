import java.util.ArrayList;
import java.util.Arrays;

public class BooksOut {
	private String teacher;
	private int numout;
	
	public BooksOut(String t, int n){
		teacher=t;
		numout=n;		
	}
	
	public ArrayList getBooksOut(){
		ArrayList teacherOut = new ArrayList(Arrays.asList(teacher,numout));
		return teacherOut;
	}
	
}