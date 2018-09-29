import java.io.*;
import java.util.regex.Pattern;

@SuppressWarnings("unused")
// Denne fil er "back-enden", den skal k�res fra enhancer.java
public class enhancerEngine {

	public static String format(String input) {
																											// Formateringsmetoden. F�rst splitter den ved hvert punktum, og s� s�tter den et linjeskift ind.
		String out = ""; 																					// Initliaze output string, ellers f�r man en NullPointerExc.
		String[] arr = input.split("(?<=[.])");										   						// Split input-stringen efter hvert punktum (bruger en RegEx, ?<= der betyder "kig bag/look behind"
		for (String s : arr) { 																				// Et for each loop
			if (s.charAt(0) == ' ') { 																		// Hvis den f�rste char er et space..
				s = s.substring(1) + "\n"; 																	// S�t stringen til at v�re lig med alt efter mellemrummet, plus et linjeskift
			}
			out += s + "\n"; 																				// S�t output string til at v�re lig med s og linjeskift
		}
																											// Denne del inds�tter et mellemrum hvis der to citationstegn ved siden af hinanden ("Hej!""Hejsa"!)																			
		StringBuilder b = new StringBuilder(out); 															// Lav StringBuilder objektet, der skal g�re arbejdet nemt
		for (int i = out.length() - 1; i >= 0; i--) { 														// For-l�kke der t�ller bagl�ns, ellers f�r vi problemer, da l�ngden bliver �ndret n�r vi tilf�jer noget. i er length()-1, pga 0-indexing,. (hvis l�ngden er 100, er max index 99)																								
			if ((out.charAt(i) == '\"') && (out.charAt(i + 1) == '\"')) {									// hvis char at i OG char at i+1 = "...														
				b.insert(i + 1, " "); 																		// brug StringBuilder til at inds�tte et mellemrum
			}
		}
		return b.toString(); 																				// Return den endelige StringBuilder value som en string

	}

	public static String replace(String input) { 															// Replacer-methoden. (opg 2)
		String out = "";																					// Samme som i format, NullPointerExc.
		String[][] r = { { "�lling", "Grisling" }, { "And", "Gris" } };										// Dette er et 2-layered array, der indeholder ordene der skal replaces, og det, ordet skal replaces til.
		String[] lines = input.split("\n");																	// Split inputtet op i linjer (split ved hvert linjeskift)

		for (String line : lines) {																			// for each loop, itererer over hver linje
			String[] words = line.split(" ");																// Split hver linje op i ord, (ved hvert Space)
			for (String word : words) {																		// nyt for each loop, itererer over hvert ord.
				for (int i = 0; i < r.length; i++) {														// nyt for loop..... bruges til at iterere over hvert ord i replacer-arrayet.

					if (word.matches("(.*)" + r[i][0].toLowerCase() + "(.*)")) {							// Hvis ordet matcher det f�rste ord i "i"-subarrayet, (i lowercase). RegEx (.*) betyder ethvert symbol der optr�der mere end 0 gange
						word = word.replace(r[i][0].toLowerCase(), r[i][1].toLowerCase());					// erstat ordet med det n�ste ord i subarrayet (dens replacer)
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
		// hj�lp
	}
	
	public static String enhance(String input) {															// Vores prim�re "runner" method. Det er denne, der skal calles
		String replaced = replace(input);																	// F�rst, erstat ordene i indputtet
		String enhanced = format(replaced);																	// Formater s� den erstatte version
		return enhanced;																					// Return den endelige forbedrede version
	}

	public static void main(String[] args) {
		// Vi skal ikke bruge den her til noget, alt k�res fra enhancer.java
	}
}
