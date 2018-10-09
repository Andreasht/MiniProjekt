import java.text.DecimalFormat;																				// Importerer alle de n�dvendige classes
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

// Denne fil er "back-enden", den skal k�res fra enhancer.java
public class enhancerEngine {
	public static String format(String input) {																// Formateringsmetoden. F�rst splitter den ved hvert punktum, og s� s�tter den et linjeskift ind
		StringBuilder b = new StringBuilder(); 																// Initiere et StringBuilder objekt, der skal bruges senere
		String[] arr = input.split("(?<=[.])");										   						// Split input-stringen efter hvert punktum (bruger en RegEx, ?<= der betyder "kig bag/look behind"
		for (String s : arr) { 																				// Et for each loop
			if (s.charAt(0) == ' ') { 																		// Hvis den f�rste char er et space..
				s = s.substring(1) + "\n"; 																	// S�t stringen til at v�re lig med alt efter mellemrummet, plus et linjeskift
			}
			b.append(s + "\n");																				// S�t output string til at v�re lig med s og linjeskift
		}
																											// Denne del inds�tter et mellemrum hvis der to citationstegn ved siden af hinanden ("Hej!""Hejsa"!)

		for (int i = b.length() - 1; i >= 0; i--) { 														// For-l�kke der t�ller bagl�ns, ellers f�r vi problemer, da l�ngden bliver �ndret n�r vi tilf�jer noget. i er length()-1, pga 0-indexing (hvis l�ngden er 100, er max index 99)
			if ((b.charAt(i) == '\"') && ((b.charAt(i + 1) == '\"'))) {										// hvis char at i OG char at i+1 = "...
				b.insert(i + 1, " "); 																		// brug StringBuilder til at inds�tte et mellemrum
			}
		}
		return b.toString(); 																				// Return den endelige StringBuilder value som en string

	}

	public static String replace(String input) { 															// Replacer-methoden. (opg 2)
		StringBuilder builder = new StringBuilder();														// Ny StringBuilder, bruges igen senere
		String[][] r = { 																					// Dette er et 2-layered array, der indeholder ordene der skal replaces, og det, ordet skal replaces til.
				{"�lling", "Grisling"}, 
				{"Stor", "Uhyre koloenorm"},
				{"Meget","Helt fantastisk stikhamrende meget"},
				{"Styg","Fucking klam og grim"},
				{"Pip","�f"},
				{"Rap","�f"},
				{"Vand","Mudder"},
				{"Fader","Daddy"},
				{"Moder","Mutti"},
				{"Unge","M�gunge"}
			//	{ord,replacerOrd}
		};
		
		String[] lines = input.split("\n");																	// Split inputtet op i linjer (split ved hvert linjeskift)

		for (String line : lines) {																			// for each loop, itererer over hver linje
			String[] words = line.split(" ");																// Split hver linje op i ord, (ved hvert Space)
			for (String word : words) {																		// nyt for each loop, itererer over hvert ord.
				for (int i = 0; i < r.length; i++) {														// nyt for loop..... bruges til at iterere over hvert ord i replacer-arrayet.

					if (word.matches("(.*)" + r[i][0].toLowerCase() + "(.*)")) {							// Hvis ordet matcher det f�rste ord i "i"-subarrayet, (i lowercase). RegEx (.*) betyder ethvert symbol der optr�der 0 eller flere gange
						word = word.replace(r[i][0].toLowerCase(), r[i][1].toLowerCase());					// erstat ordet med det n�ste ord i subarrayet (dens replacer)
					} else if (word.matches("(.*)" + r[i][0] + "(.*)")) {									// ellers, hvis ordet matcher versionen af ordet som har stort forbogstav..
						word = word.replace(r[i][0], r[i][1]);												// erstat ordet, med versionen der har stort forbogstav
					}
					
				}
				builder.append(word + " ");																	// Link alle ordene sammen i output-stringen, ved hj�lp af StringBuilderen
			}
		}
		return builder.toString();																			// return output-stringen (StringBuilderen, lavet til en normal String)
	}

	public static void checkZipf(String input) {															// Denne metode checker frekvenserne, s� man kan sammenligne dem
		HashMap<String,Double> hm = new HashMap<String,Double>();											// Dette HashMap indeholder en string som key og en double som value, hvilket er ordet og dens occurrence.
		String[] words = input.split("\\b");																// Inputtet bliver s� splittet ved hvert ord, regex her er et word boundary.
			for(String word : words) {																		// for each loop, itererer over alle ordene.
				word = word.toLowerCase();																	// s�tter ordet til at v�re lowercase
				if(word.matches("\\pL+")) {																	// hvis ordet matcher et lowercase-ord.. (s� tal osv bliver sorteret fra)
					if(!hm.containsKey(word)) {																// checker f�rst om hashmappet allerede indeholder ordet, hvis ikke...
						hm.put(word, 1.0);																	// inds�t ordet med occurrence 1
					} else {																				// hvis det findes i hm allerede
						hm.put(word, hm.get(word)+1);														// s�t ordets occurrence 1 op.
					}
				}
			}																			
		DecimalFormat dec = new DecimalFormat("#0.00");														// lav en decimalformatter, s� der kan afrundes senere. denne afrunder til 2 decimaler
		List<Double> list = new ArrayList<Double>(hm.values());												// put alle hashmappets values ind i en arraylist med doubles
		Collections.sort(list,Collections.reverseOrder());													// sorter alle tallene i arraylisten, omvendt (descending)
		for (Double i : list) {																				// for each double i arraylisten..	
			long occu = Math.round(i);																		// init en variabel til at v�re lig med ordets occurrence, afrundet
			String freq = dec.format(i/list.get(0));														// init en string til at v�re lig med ordets occurrence, divideret med det første (det mest fremkommende) ords occurence, afrundet
			System.out.println(occu + " | " + freq);														// print occurrence og frequence, sepereret med en |
		}	

	}
	
	public static String enhance(String input) {															// Vores prim�re "runner" method. Det er denne, der skal calles
		String replaced = replace(input);																	// F�rst, erstat ordene i indputtet
		String enhanced = format(replaced);																	// Formater s� den erstattede version
		return enhanced;																					// Return den endelige forbedrede version
	}

	public static void main(String[] args) {
		// Vi skal ikke bruge den her til noget, alt k�res fra enhancer.java
	}
}
