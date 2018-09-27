//this file is the "enhancer-frontend", where i will get the file to read, and then enhance that.
//everything back-end takes place in enhancerEngine.java
public class enhancer {
	public static void main(String[] args) {
		String input = FileScanner.readFromFile("UglyDuckling"); 					//set the filescanner.readfromfile method return value
		String enhancedFinal = enhancerEngine.enhance(input);						//set the string enhancedFinal to equal the return value of the enhance method on the input
		System.out.println(enhancedFinal);
		FileScanner.writeToFile(enhancedFinal,"UglyDuckling.rtf"); 							//write the enhanced final
	}
}
