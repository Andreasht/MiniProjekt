import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import java.io.Writer;

public class FileScanner {
	public static String readFromFile() {
		String file = "";
		try {
			InputStream is = new FileInputStream("src/UglyDuckling.txt");
			int BUFFER_SIZE = 10000;
			BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"),BUFFER_SIZE);
			String str;
			while ((str = br.readLine()) != null) {
				file += str;
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return file;
	}

	public static void writeToFile(String s) {

		try {
			Writer writer = new OutputStreamWriter(new FileOutputStream("TheEnhancedUglyDuckling.txt"), "UTF-16");
			BufferedWriter fout = new BufferedWriter(writer);
			fout.write(s);
			fout.newLine();
			fout.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		String str = readFromFile();
		System.out.println(str);
		writeToFile(str);

	}
}
