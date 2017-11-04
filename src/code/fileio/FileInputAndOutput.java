package code.fileio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

public class FileInputAndOutput {
	
	public static Boolean saveStateToFile(String state, String path) {
		PrintStream s = null;
		Boolean result = false;
		try {
			s = new PrintStream(path);
			s.format(state);
			result = true;
		} catch (FileNotFoundException e) {
			System.out.println("File wasn't found and can't be created: "+path);
		}
		finally {
			s.close();
		}
		return result;
	}
	
	

}
