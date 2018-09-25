
public class textFormatter {

	public static void format(String input) {
		String[] stc = input.split("(?<=\\.)");
		String temp = "";
		FileScanner.writeToFile(temp,"UglyDuckling.rtf");
	}
	public static void main(String[] args) {
		new FileScanner();
		String input = FileScanner.readFromFile("UglyDuckling");
		format(input);
	}
}
