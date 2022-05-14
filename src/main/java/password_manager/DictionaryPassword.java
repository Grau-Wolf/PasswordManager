package password_manager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;
import java.util.stream.Stream;

public class DictionaryPassword {
	public String generateDictionaryPassword(int length){
		String line = "";
		
		for(int i = 0; i < length; i++){
			String newLine = grabWord();
			StringBuilder myString = new StringBuilder(newLine);
			myString.setCharAt(0, Character.toUpperCase(newLine.charAt(0)));
			line += myString.toString();
		}
		
		return line;
	}
	
	public String generateAdjectiveNounPassword(){
		String line = "";
		
		line = grabAdjective() + grabNoun();
		
		return line;
	}
	
	public String grabWord() {
		int fileLength = 194262;
		Random r = new Random();
		int n = r.nextInt(fileLength); // The line number
		String line = "";
		try (Stream<String> lines = Files.lines(Paths.get("WordFiles/Dictionary.txt"))) {
			line = lines.skip(n).findFirst().get();
			System.out.println(line);
		}
		catch(IOException e){
			System.out.println(e);
		}
		Character.toUpperCase(line.charAt(0));
		return line;
	}
	
	public String grabAdjective(){
		int fileLength = 1347;
		Random r = new Random();
		int n = r.nextInt(fileLength); // The line number
		String line = "";
		try (Stream<String> lines = Files.lines(Paths.get("WordFiles/Adjectives.txt"))) {
			line = lines.skip(n).findFirst().get();
		}
		catch(IOException e){
			System.out.println(e);
		}
		StringBuilder myString = new StringBuilder(line);
		myString.setCharAt(0, Character.toUpperCase(line.charAt(0)));
		return myString.toString();
	}
	
	public String grabNoun(){
		int fileLength = 1524;
		Random r = new Random();
		int n = r.nextInt(fileLength); // The line number
		String line = "";
		try (Stream<String> lines = Files.lines(Paths.get("WordFiles/Nouns.txt"))) {
			line = lines.skip(n).findFirst().get();
		}
		catch(IOException e){
			System.out.println(e);
		}
		StringBuilder myString = new StringBuilder(line);
		myString.setCharAt(0, Character.toUpperCase(line.charAt(0)));
		return myString.toString();
	}
	
}
