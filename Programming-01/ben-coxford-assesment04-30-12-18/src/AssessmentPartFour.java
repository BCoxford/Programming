import java.util.ArrayList;
import java.util.HashMap; //Imports hash maps.
import java.io.*; //Imports all classes from java.io package.
import java.util.Scanner; //Imports scanner to read text file.
import java.util.List;

public class AssessmentPartFour {
	
	List<String> morseCode = new ArrayList<String>(); //ArrayList for  morse code message
	
	public int loadMorseFromFile(String filename)
	{
		File mFile = new File(filename); //Opens the file.
		
		morseCode.clear(); //Empties or clears the global array morseCode.
		
		Scanner sc; //Defines the scanner.
		
		try { //Tries to open the file.
			sc = new Scanner(mFile); //Initialises the scanner with the file.
			String nLine;
			while (sc.hasNextLine()) { //Repeats every time there is another line.
				nLine = sc.nextLine();
				morseCode.add(nLine); //Adds the next line to the morseCode array.
			}
			sc.close(); //Closes the file.
		} catch (FileNotFoundException e) { //If the file cannot be found the exception is caught.
			System.out.println(e.getLocalizedMessage()); //Prints out the localised message for the error.
		}
		
		
		return morseCode.size(); //Returns the size of the morseCode array.
		
		
	}
	
	public char morseToEnglish(String findCode) { //Method to convert morse code to a character.
		//Defines and initialises two arrays to store the letters and their corresponding morse code value.
		char[] letters = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','1','2','3','4','5','6','7','8','9','0'};
		String[] code = {".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--..",".----","..---","...---","...--","....-",".....","-....","--...","---..","----.","-----"};

		//Defines and initialises a HashMap.
		HashMap<String, Character> keys = new HashMap<String, Character>();
 
		for(int i=0;i<letters.length;i++) { //For length of the letters array.
			keys.put(code[i], letters[i]); //Add to the hash map each code and their corresponding character value. (Pairs)
		}
		
		return keys.get(findCode); //Gets the character from the HashMap, using the morse code as the key.
	}
	
	public String translateMorse()
	{
		String message = ""; //Initialises the final message.
		for (int i=0; i<morseCode.size();i++) { //Iterates to length of the morse code message.
			if(morseCode.get(i).equals("/")) { //If the code is equal to a / then the whitespace is added to the message.
				message = message + " ";
			}
			else {
				//Calls the method to return the English character and adds it to the message.
				char toAdd = morseToEnglish(morseCode.get(i));
				message = message + Character.toString(toAdd);
			}
		}

		return message; //Returns the final message.
	}
	
	
	

}
