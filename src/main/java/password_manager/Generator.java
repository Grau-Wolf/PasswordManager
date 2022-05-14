package password_manager;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;
import java.util.stream.Stream;

public class Generator {
	
	public String generateDictionaryPassword(int length){
		String line = "";
		
		for(int i = 0; i < length; i++){
			String newLine = generateWord();
			StringBuilder myString = new StringBuilder(newLine);
			myString.setCharAt(0, Character.toUpperCase(newLine.charAt(0)));
			line += myString.toString();
		}
		
		return line;
	}
	
	public String generateAdjectiveNounPassword(int length){
		String line = "";
		if(length == 3){
			line = generateAdjective() + generateAdjective() + generateNoun();
		}
		else if(length == 2){
			line = generateAdjective() + generateNoun();
		}
		else{
			line = generateNoun();
		}
		
		
		return line;
	}
	
	/**
     * 
     * @param length, length of the password generated
     * @return password with random alphanumerics & symbols
     */
    public String generatePassword(int length){
    	String password = "";
    	
    	//appends random alphanumeric or symbol
    	for(int i = 0; i < length; i++){
    		password += generateCharacter();
    	}
    	
    	return password;
    }
    
    /**
     * 
     * @param length, length of the password generated
     * @return alphanumeric password
     */
    public String generateAlphanumericPassword(int length){
    	Random r = new Random();
    	String password = "";
    	
    	for(int i = 0; i < length; i++){
    		if(r.nextInt(6) == 0){
    			password += generateNumber();
    		}
    		else{
    			password += generateAlphabeticCharacter();
    		}
    	}
    	return password;
    }
    
    /**
     * 
     * @param length, length of password
     * @return alphabetic password
     */
    public String generateAlphabeticPassword(int length){
    	String password = "";
    	
    	for(int i = 0; i < length; i++){
    		password += generateAlphabeticCharacter();
    	}
    	
    	return password;
    }
    
    /**
     * @param length, length of password
     * @return numeric password
     */
    public String generateNumericPassword(int length){
    	String password = "";
    	
    	for(int i = 0; i < length; i++){
    		password += generateNumber();
    	}
    	
    	return password;
    }
    
    /**
     * @param length, length of password
     * @return password with only symbols
     */
    public String generateSymbolPassword(int length){
    	String password = "";
    	
    	for(int i = 0; i < length; i++){
    		password += generateSymbol();
    	}
    	return password;
    }
    
    /**
     * 
     * @param length, length of password
     * @return password with Numbers & Symbols
     */
    public String generateNumberSymbolPassword(int length){
    	String password = "";
    	Random r = new Random();
    	
    	for(int i = 0; i < length; i++){
    		if(r.nextInt(2) == 0){
    			password += generateNumber();
    		}
    		else{
    			password += generateSymbol();
    		}
    	}
    	
    	return password;
    }
    
    public String generateAlphabetSymbolPassword(int length){
    	String password = "";
    	Random r = new Random();
    	
    	for(int i = 0; i < length; i++){
    		int random = r.nextInt(4);
    		if(random == 0){
    			password += generateSymbol();
    		}
    		else{
    			password += generateAlphabeticCharacter();
    		}
    	}
    	
    	return password;
    }
    
    //returns random character
    public char generateCharacter(){
    	Random r = new Random();
    	char generatedCharacter;
    	
    	//used to allow us to choose from ascii 33 to 126
    	int asciiBounds = 93;
    	int startingASCII = 33;
    	
    	generatedCharacter = (char)(r.nextInt(asciiBounds) + startingASCII);
    	
    	return generatedCharacter;
    }
    //returns random alphabetic character
    public char generateAlphabeticCharacter(){
    	Random r = new Random();
    	int alphabetLength = 26;
    	char generatedAlphabeticCharacter = (char)(r.nextInt(alphabetLength) + 65);
    	if(r.nextInt(2) == 0){
    		generatedAlphabeticCharacter = Character.toLowerCase(generatedAlphabeticCharacter);
    	}
    	return generatedAlphabeticCharacter;
    }
    
    //returns random number
    public int generateNumber(){
    	Random r = new Random();
    	int numbers = 10;
    	int generatedNumber = r.nextInt(numbers);
    	return generatedNumber;
    }

    
    //returns random symbol
    public char generateSymbol(){
    	Random r = new Random();
    	String setOfSymbols = "!@#$%^&*()-_=+[]{}\\|\"\':;.>,<`~";
    	char generatedSymbol = setOfSymbols.charAt(r.nextInt(setOfSymbols.length()));
    	return generatedSymbol;
    }
    
    public String generateWord() {
		int fileLength = 194228;
		Random r = new Random();
		int n = r.nextInt(fileLength); // The line number
		String line = "";
		try (Stream<String> lines = Files.lines(Paths.get("WordFiles/Dictionary.txt"))) {
			line = lines.skip(n).findFirst().get();
		}
		catch(IOException e){
			System.out.println(e);
		}
		Character.toUpperCase(line.charAt(0));
		return line;
	}
	
	public String generateAdjective(){
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
	
	public String generateNoun(){
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
