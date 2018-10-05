import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

// Denne fil er "back-enden", den skal k�res fra enhancer.java
@SuppressWarnings("unused")
public class enhancerEngine {
	public static String format(String input) {
		StringBuilder b = new StringBuilder(); 																// Formateringsmetoden. Først splitter den ved hvert punktum, og så sætter den et linjeskift ind
		String[] arr = input.split("(?<=[.])");										   						// Split input-stringen efter hvert punktum (bruger en RegEx, ?<= der betyder "kig bag/look behind"
		for (String s : arr) { 																				// Et for each loop
			if (s.charAt(0) == ' ') { 																		// Hvis den første char er et space..
				s = s.substring(1) + "\n"; 																	// Sæt stringen til at være lig med alt efter mellemrummet, plus et linjeskift
			}
			b.append(s + "\n");																				// Sæt output string til at være lig med s og linjeskift
		}
																											// Denne del indsætter et mellemrum hvis der to citationstegn ved siden af hinanden ("Hej!""Hejsa"!)

		for (int i = b.length() - 1; i >= 0; i--) { 														// For-løkke der tæller baglæns, ellers før vi problemer, da længden bliver ændret når vi tilføjer noget. i er length()-1, pga 0-indexing,. (hvis længden er 100, er max index 99)
			if ((b.charAt(i) == '\"') && ((b.charAt(i + 1) == '\"'))) {										// hvis char at i OG char at i+1 = "...
				b.insert(i + 1, " "); 																		// brug StringBuilder til at indsætte et mellemrum
			}
		}
		return b.toString(); 																				// Return den endelige StringBuilder value som en string

	}

	public static String replace(String input) { 															// Replacer-methoden. (opg 2)
		StringBuilder builder = new StringBuilder();														// Samme som i format, NullPointerExc.
		String[][] r = { 																					// Dette er et 2-layered array, der indeholder ordene der skal replaces, og det, ordet skal replaces til.
				{"�lling", "Grisling"}, 
				{"Stor", "Uhyre koloenorm"},
				{"Meget","Helt fantastisk stikhamrende meget"},
				{"Styg","Fucking klam og grim"},
				{"Pip","�f"},
				{"Rap","�f"},
				{"Vand","Mudder"},
		};
		
		String[] lines = input.split("\n");																	// Split inputtet op i linjer (split ved hvert linjeskift)

		for (String line : lines) {																			// for each loop, itererer over hver linje
			String[] words = line.split(" ");																// Split hver linje op i ord, (ved hvert Space)
			for (String word : words) {																		// nyt for each loop, itererer over hvert ord.
				for (int i = 0; i < r.length; i++) {														// nyt for loop..... bruges til at iterere over hvert ord i replacer-arrayet.

					if (word.matches("(.*)" + r[i][0].toLowerCase() + "(.*)")) {							// Hvis ordet matcher det første ord i "i"-subarrayet, (i lowercase). RegEx (.*) betyder ethvert symbol der optr�der mere end 0 gange
						word = word.replace(r[i][0].toLowerCase(), r[i][1].toLowerCase());					// erstat ordet med det nyste ord i subarrayet (dens replacer)
					} else if (word.matches("(.*)" + r[i][0] + "(.*)")) {									// ellers, hvis ordet matcher versionen af ordet som har stort forbogstav..
						word = word.replace(r[i][0], r[i][1]);												// erstat ordet, med versionen der har stort forbogstav
					}
					
				}
				builder.append(word + " ");																	// Link alle ordene sammen i output-stringen
			}
		}
		return builder.toString();																			// return output-stringen
	}

	public static void checkZipf(String input) {															// Denne metode checker frekvenserne, så man kan sammenligne dem
		HashMap<String,Double> hm = new HashMap<String,Double>();											// Dette HashMap indeholder en string og en double, hvilket er ordet og dens occurrence.
		String[] words = input.split("\\b");																// Inputtet bliver så splittet ved hvert ord, regex her er et word boundary.
			for(String word : words) {																		// for each loop, itererer over alle ordene.
				word = word.toLowerCase();																	// sætter ordet til at være lowercase
				if(word.matches("\\pL+")) {																	// hvis ordet matcher et lowercase-ord.. (så tal osv bliver sorteret fra)
					if(!hm.containsKey(word)) {																// checker først om hashmappet allerede indeholder ordet, hvis ikke...
						hm.put(word, 1.0);																	// indsæt ordet med occurrence 1
					} else {																				// hvis det findes i hm allerede
						hm.put(word, hm.get(word)+1);														// sæt ordets occurrence 1 op.
					}
				}
			}
		hm.remove("");																						// fjern whitespace.. ved ikke hvordan ellers
		DecimalFormat dec = new DecimalFormat("#0.00");														// lav en decimalformatter, så der kan afrundes senere. denne afrunder til 2 decimaler
		List<Double> list = new ArrayList<Double>(hm.values());												// put alle hashmappets values ind i en arraylist med doubles
		Collections.sort(list,Collections.reverseOrder());													// sorter alle tallene i arraylisten, omvendt (descending)
		for (Double i : list) {																				// for each double i arraylisten..	
			long occu = Math.round(i);																		// init en variabel til at være lig med ordets occurrence
			String freq = dec.format(i/list.get(0));														// init en string til at være lig med ordets occurrence, divideret med det første (det mest fremkommende) ords occurence, afrundet
			System.out.println(occu + " | " + freq);														// print occurrence og frequence, sepereret med en |
		}	
	}
	
	public static String enhance(String input) {															// Vores primære "runner" method. Det er denne, der skal calles
		String replaced = replace(input);																	// Først, erstat ordene i indputtet
		String enhanced = format(replaced);																	// Formater så den erstatte version
		return enhanced;																					// Return den endelige forbedrede version
	}

	public static void main(String[] args) {
		// Vi skal ikke bruge den her til noget, alt køres fra enhancer.java
	}
}
