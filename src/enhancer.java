// Denne fil er "front-enden", det er her alt skal k�res fra
// Alt den r� kode ligger i engine.java
public class enhancer {
	public static void main(String[] args) {
		String input = FileScanner.readFromFile("UglyDuckling"); 					// F� inputtet fra readFromFile methoden
		String enhancedFinal = enhancerEngine.enhance(input);						// set enhancedFinal stringen til at v�re lig med den enhancede version af inputtet
		System.out.println(enhancedFinal);											// print den enhancede verison (for testing)
		FileScanner.writeToFile(enhancedFinal,"UglyDuckling.rtf"); 					// skriv den enhancede version til en fil
	}
}
