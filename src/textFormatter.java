import java.io.*;
import java.util.regex.Pattern;
@SuppressWarnings("unused")

public class textFormatter {

	public static void format(String input) {
		//first, this part will split up the read file by every "."
		//then it will add a line switch where it finds it.
		String out = "";
		String[] arr = input.split("(?<=\\.)");
		for(String s : arr) {
			if(s.charAt(0) == ' ') {
				s = s.substring(1)+"\n";
			}
			out += s + "\n";
		}
		
<<<<<<< HEAD
		//this part ensures that a space will be input every time to quotation marks are next to each other
=======
		//this part ensures that a space will be input every time two quotation marks are next to eachother
>>>>>>> 45556a215444ca670e8550bdb884176fd1c511a2
		StringBuilder b = new StringBuilder(out);
		for(int i = out.length()-1; i>=0; i--) {
			if ((out.charAt(i) == '\"') && (out.charAt(i+1) == '\"')) {
				b.insert(i+1, " ");
			}
		}
		String fout = b.toString();
		FileScanner.writeToFile(fout,"UglyDuckling.rtf"); 
		
	} 
	
	public static void main(String[] args) {
		new FileScanner();
		String input = FileScanner.readFromFile("UglyDuckling");
		format(input);
		
	}
}
