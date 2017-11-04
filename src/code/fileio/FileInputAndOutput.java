package code.fileio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

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
	
	
	public static String loadFileToString(String path){
		String s = "";
		Scanner scanner = null;
		try{
			scanner = new Scanner(new File(path));
		} catch(FileNotFoundException e){
			System.out.println("The file was not found: " + path);
		}
		try{
			while(scanner.hasNextLine()) {
				s = s + scanner.nextLine() + "\n";
			}
		} catch(NoSuchElementException e){
			System.out.println("The save file has not been correctly encoded!");
		}
		finally{
			if(scanner!=null){
				scanner.close();
			}
		}
		
		//System.out.println(s);
		return s;
	}

}
