import java.util.*;

public class zipfTesting {
	@SuppressWarnings("unused")
	public static void checkZipf(String input) {
		String out = "";
		HashMap<String,Integer> hm = new HashMap<String,Integer>();
		String[] lines = input.split("\n");
		for(String l : lines) {
			String[] words = l.split(" ");
			for(String word : words) {
				word = word.toLowerCase();
				if(word.matches("(\\w*\\W+)")) {
					word = word.replaceAll("\\W", "");
				}
				if(!hm.containsKey(word)) {
					hm.put(word, 1);
				} else {
					hm.put(word, hm.get(word)+1);
				}
			}
		}
		hm.remove("");
		ArrayList<Integer> occ = new ArrayList<Integer>(hm.size());
		int i = 0;
		for (int v : hm.values()) {
			 occ.add(v);
			i++;
		}
		for (int xd : occ) {
			System.out.println(xd);
		}
		
	} 
	
	
	public static void main(String[] args) {
		checkZipf(enhancerEngine.enhance(FileScanner.readFromFile("lorem")));
	}
}
