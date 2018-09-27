import java.io.*;
import java.util.regex.Pattern;

@SuppressWarnings("unused")
//this file is the back-end enhancer, run it from the enhancer.java class
public class enhancerEngine {

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
		return b.toString();														//return the final text as a string
		
	}

	public static String replace(String input) {									//this method replaces the chosen words (opg 2)
		String out = "";
		String l = "";
		String[] r = {"TEST"};							//array to hold the replacers
		String[] lines = input.split("\n");											//split up the input into words
		for (String line : lines) {
			String[] words = line.split(" ");
			
			for (String word : words) {
				int i = (int) (Math.random() * r.length);								//init a random int defined on the length of the word array
				if (word.matches("(.*)ælling(.*)")) {									//if a word matches the regex... (regex here matches "meget", including any character(s) that is NOT a word)
					word = word.replace("ælling","grisling");									//replace "meget" with a random word from the replacer array
				} 
				l += word + " ";
			}
			
			out += l + " ";
		}		
		
		return out;																//join all the words together
	}	
																			//return the final replaced text																
	
	
	public static String enhance(String input) {									//this method does the work, calls the methods
		String fin = format(input);													//first, define a string as the formatted input
		String enhanced = replace(fin);												//then, define a string as the replace method, called on the formatted input
		return enhanced;															//then return the completed, joined, enhanced text.
	}

	public static void main(String[] args) {
		//nothing to do here... everything runs from the enhancer.java class.
	}
}
