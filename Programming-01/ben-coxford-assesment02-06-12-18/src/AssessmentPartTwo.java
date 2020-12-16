import java.util.HashMap;
import java.util.Map;

public class AssessmentPartTwo {
	
	public int scrabbleScore(String aWord)
	{
		// 03 -Scrabble Score
		// Complete this method so that it returns the scrabble score for the word in aWord
		// In scrabble each letter is worth a number of points and each word is worth the
		// sum of the scores of the letters in it. For this assignment we will ignore
		// double/treble letter/word bonuses.
		// The English language points per letter can be found at
		// https://en.wikipedia.org/wiki/Scrabble_letter_distributions
		// You will need to come up with a way of connecting each letter to its score and
		// a way of identifying each letter in the word.
		
		//Defines and initialises two arrays to store the letters and their corresponding integer value.
		char[] letters = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
		int[] points = {1,3,3,2,1,4,2,4,1,8,5,1,3,1,1,3,10,1,1,1,1,4,4,8,4,10};
		
		//Defines a new hash map with the key set to be a character and the value set top be an integer.
		HashMap<Character, Integer> sPoints = new HashMap<Character, Integer>();
		
		//Iterates to the length of the alphabetical array. 
		for(int i=0;i<letters.length;i++) {
			sPoints.put(letters[i], points[i]); //Adds the character and corresponding integer value to the hash map.
		}
		
		int score = 0; //Defines and initialises the score value.
		for(int i=0; i< aWord.length();i++) { //Iterates the from zero to the length of the word.
			//The line below gets the score of a letter using the character at the current index. It then adds this onto the previous value of 'score'.
			score = score + sPoints.get(aWord.charAt(i)); 
		}
		
		return score; //Returns the value of score.
	}
	
	public Boolean passwordValidator(String password)
	{
		// 04 - Password Validator
		// Complete this method to validate that the String password
		// is a valid password
		// A password is valid if it is
		// - between 8 and 16 characters long (inclusive)
		// - made up of letters (upper or lower), numbers, and the following characters !�$%
		// - has at least one lower case letter, one upper case letter and a number
		// - does not contain the phrases 'password' or 'passwd'
		
		int upCase = 0; //Initialises a variable to count the amount of uppercase letters.
		int loCase = 0; //Initialises a variable to count the amount of lowercase letters.
		int numCount = 0; //Initialises the variable to count the amount of numbers.
		
		//Regex expression used to test whether the password matches the given characters.
		String validEnts = "^[a-zA-Z0-9!�$%]+$";
		String[] invalidPhrase = {"password", "passwd"}; //Array of all passwords that cannot be used.
		
		//Initialises a boolean variable, attempting to match the password and the regex. If it matches the expression then the value is set to true. 
		boolean validEntry =  password.matches(validEnts); //This variable is used to determine whether the password is valid or not. (True = Yes. False = No.)

		//At this point, each conditional statement will test whether the password is invalid.
		//If so it sets the value to false; indicating the password is invalid.
		
		//Tests whether the password length is above or below the given values.
		if (!((password.length() >= 8) && (password.length() <= 16))) {
			validEntry = false; //Sets the boolean to false, indicating the password is not valid.
		}

		/*Iterates through the invalid phrases array and if the password contains an item 
		  in the array, it sets the boolean value to false.
		*/
		for(int i=0;i<invalidPhrase.length;i++) {
			if (password.contains(invalidPhrase[i])) { //Tests whether the current element in the invalidPhrases array is contained anywhere within the password string.
				validEntry = false;
			}
		}
		
		//Iterates through the password character by character and counts up the amount of uppercase and lowercase characters and numerical characters. 
		for(int x=0;x<password.length();x++) {
			char c = password.charAt(x); //Gets the character at the current index of the array.
			if (String.valueOf(c).matches("[0-9]+")) { //Tests whether the character is a numerical character using a regex expression.
				numCount++; //Increments the number count by one.
			}
			else { //If not a numerical character.
				if (c == Character.toUpperCase(c)) { //Checks whether the value is an uppercase character of itself.
					upCase++; //Increments the number of uppercase characters by one.
				}
				else if (c == Character.toLowerCase(c)) { //Checks whether the value is a lowercase character of itself.
					loCase++; //Increments the number of lowercase characters by one.
				}
			}
		}
		
		//Tests whether there is less than one or more uppercase, lowercase and numerical characters. 
		if ((upCase < 1) || (loCase < 1) || (numCount < 1)) {
			validEntry = false; //Sets boolean to false.
		}
		
		return validEntry; 
	}

}
