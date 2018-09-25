
public class textFormatter {

	public static void format(String input) {
		
		
		/* String out = "";
	    for(int i = 0; i < input.length(); i++) {
	    	out += input.charAt(i);
	    	if (input.charAt(i) == '.') {
	        out += '\n';
	      }
	    } */
	
		String[] arr = input.split("\n");
		String out = "";
		for(String s : arr) {
			StringBuilder b = new StringBuilder(s);
			if (s.length() > 20) {
				 b.insert(19, '\n');
			}
			out += b.toString();
		} 
		
		FileScanner.writeToFile(out,"UglyDuckling.rtf");
	}
	
	public static void main(String[] args) {
		new FileScanner();
		String input = FileScanner.readFromFile("UglyDuckling");
		format(input);
	}
}
