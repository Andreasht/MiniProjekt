import java.util.*;
import java.text.DecimalFormat;

public class zipfTesting {
	@SuppressWarnings("unused")
	public static void checkZipf(String input) {
		HashMap<String,Double> hm = new HashMap<String,Double>();
		String[] words = input.split("\\b");
			for(String word : words) {
				word = word.toLowerCase();
				if(word.matches("\\pL+")) {
					if(!hm.containsKey(word)) {
						hm.put(word, 1.0);
					} else {
						hm.put(word, hm.get(word)+1);
					}
				}
			}
		hm.remove("");
		DecimalFormat dec = new DecimalFormat("#0.00");
		List<Double> list = new ArrayList<Double>(hm.values());
		Collections.sort(list,Collections.reverseOrder());
		for (Double j : list) {		
			long occu = Math.round(j);
			String freq = dec.format(j/list.get(0));
			System.out.println(occu + " | " + freq);
		}	
	} 
	
	
	public static void main(String[] args) {
		checkZipf(enhancerEngine.enhance(FileScanner.readFromFile("UglyDuckling")));
	}
}


