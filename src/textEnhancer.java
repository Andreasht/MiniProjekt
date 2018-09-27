import java.io.*;
import java.util.regex.Pattern;

@SuppressWarnings("unused")

public class textEnhancer {

	public static String format(String input) {
																					// first, this part will split up the read file by every "."
																					// then it will add a line switch where it finds it.
		String out = ""; 															// init the string to not get a nullpointerexception
		String[] arr = input.split("(?<=[.])");										// split the input string after every period (?<= means look behind)
		for (String s : arr) { 														// your avg for-each loop
			if (s.charAt(0) == ' ') {											 	// if the first character is a space..
				s = s.substring(1) + "\n"; 											// set the string to equal everything from the second character + a newline
			}
			out += s + "\n"; 														// set output string to equal out + s and a newline
		}
																					// this part ensures that a space will be input every time two quotation marks
																					// are next to each other
		StringBuilder b = new StringBuilder(out); 									// create StringBuilder object
		for (int i = out.length() - 1; i >= 0; i--) { 								// for loop, counts downwards, because else the indices will be messed, bc we add something to the string. i equals length-1 bc of 0-indexing (if length is 100, max index is 99)																	
			if ((out.charAt(i) == '\"') && (out.charAt(i + 1) == '\"')) { 			// if the char at index i AND the char at i + 1 = "
				b.insert(i + 1, " "); 												// use stringbuilder to insert a space
			}
		}
		return b.toString();
		
	}

	public static String replace(String input) {
		String out = "";
		String[] r = { "TEST2", "TEST3", "TEST4", "TEST5" };
		String[] arr = input.split(" ");

		for (String s : arr) {
			int i = (int) (Math.random() * r.length);
			if (s.matches("(.*)meget(.*)")) {
				s = s.replace("meget", r[i]);
			}
			out += s + " ";
		}
		System.out.println(out);
		return out;
	}
	
	public static String enhance(String input) {
		String fin = format(input);
		String enhanced = replace(fin);
		return enhanced;
	}

	public static void main(String[] args) {
		String input = FileScanner.readFromFile("UglyDuckling"); 					// set the filescanner.readfromfile method return value
		String enhancedFinal = enhance(input);
		System.out.println(enhancedFinal);
		// FileScanner.writeToFile(f,"UglyDuckling.rtf"); //write the enhanced string to
		// a file
	}
}
