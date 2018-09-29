import java.io.*;
import java.util.regex.Pattern;

@SuppressWarnings("unused")
// Denne fil er "back-enden", den skal køres fra enhancer.java
public class enhancerEngine {

	public static String format(String input) {
																											// Formateringsmetoden. Først splitter den ved hvert punktum, og så sætter den et linjeskift ind.
		String out = ""; 																					// Initliaze output string, ellers får man en NullPointerExc.
		String[] arr = input.split("(?<=[.])");										   						// Split input-stringen efter hvert punktum (bruger en RegEx, ?<= der betyder "kig bag/look behind"
		for (String s : arr) { 																				// Et for each loop
			if (s.charAt(0) == ' ') { 																		// Hvis den første char er et space..
				s = s.substring(1) + "\n"; 																	// Sæt stringen til at være lig med alt efter mellemrummet, plus et linjeskift
			}
			out += s + "\n"; 																				// Sæt output string til at være lig med s og linjeskift
		}
																											// Denne del indsætter et mellemrum hvis der to citationstegn ved siden af hinanden ("Hej!""Hejsa"!)																			
		StringBuilder b = new StringBuilder(out); 															// Lav StringBuilder objektet, der skal gøre arbejdet nemt
		for (int i = out.length() - 1; i >= 0; i--) { 														// For-løkke der tæller baglæns, ellers får vi problemer, da længden bliver ændret når vi tilføjer noget. i er length()-1, pga 0-indexing,. (hvis længden er 100, er max index 99)																								
			if ((out.charAt(i) == '\"') && (out.charAt(i + 1) == '\"')) {									// hvis char at i OG char at i+1 = "...														
				b.insert(i + 1, " "); 																		// brug StringBuilder til at indsætte et mellemrum
			}
		}
		return b.toString(); 																				// Return den endelige StringBuilder value som en string

	}

	public static String replace(String input) { 															// Replacer-methoden. (opg 2)
		String out = "";																					// Samme som i format, NullPointerExc.
		String[][] r = { { "Ælling", "Grisling" }, { "And", "Gris" } };										// Dette er et 2-layered array, der indeholder ordene der skal replaces, og det, ordet skal replaces til.
		String[] lines = input.split("\n");																	// Split inputtet op i linjer (split ved hvert linjeskift)

		for (String line : lines) {																			// for each loop, itererer over hver linje
			String[] words = line.split(" ");																// Split hver linje op i ord, (ved hvert Space)
			for (String word : words) {																		// nyt for each loop, itererer over hvert ord.
				for (int i = 0; i < r.length; i++) {														// nyt for loop..... bruges til at iterere over hvert ord i replacer-arrayet.

					if (word.matches("(.*)" + r[i][0].toLowerCase() + "(.*)")) {							// Hvis ordet matcher det første ord i "i"-subarrayet, (i lowercase). RegEx (.*) betyder ethvert symbol der optræder mere end 0 gange
						word = word.replace(r[i][0].toLowerCase(), r[i][1].toLowerCase());					// erstat ordet med det næste ord i subarrayet (dens replacer)
					} else if (word.matches("(.*)" + r[i][0] + "(.*)")) {								 	// ellers, hvis ordet matcher versionen af ordet som har stort forbogstav..
						word = word.replace(r[i][0], r[i][1]);												// erstat ordet, med versionen der har stort forbogstav
					}
					
				}
				out += word + " ";																			// Link alle ordene sammen i output-stringen
			}
		}
		return out;																							// return output-stringen												
	}

	public static void checkZipf(String input) {
		// hjælp
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
