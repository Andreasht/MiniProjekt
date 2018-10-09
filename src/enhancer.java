// Denne fil er "front-enden", det er her alt skal køres fra
// Alt den rå kode ligger i enhancerEngine.java
public class enhancer {
	public static void main(String[] args) {
		String input = FileScanner.readFromFile("UglyDuckling"); 					// Få inputtet fra readFromFile methoden
		String enhancedFinal = enhancerEngine.enhance(input);						// set enhancedFinal stringen til at være lig med den enhancede version af inputtet
		System.out.println(enhancedFinal);											// print den enhancede verison i konsollen (for debugging)
		enhancerEngine.checkZipf(enhancedFinal);									// kalder her checkZipf metoden på teksten
		FileScanner.writeToFile(enhancedFinal,"UglyDuckling.rtf"); 					// skriv den enhancede version til en fil
	}
}
