
public class AssessmentPartThree {

	// The simplest form of encryption is the rotation cipher (also known as Caeser's Cipher)
	// An offset value is chosen to encrypt a message. Each letter is replaced by the
	// letter that that number of places away from it.
	// So if an offset value of 1 is chosen, each letter is replaced by the one after it
	// - so a becomes b, b becomes c, etc
	// If a value of -2 is chosen a becomes y, b becomes z and so on
	
	 public char enryptedCharacter(char theChar, int theOffset)
	 {
		 // 05 - encryptedCharacter
		 // Complete this method so that it returns the encrypted character for
		 // theChar when and offset of theOffset is used
		 // So if theChar='m' and theOffset is 3 the method will return 'p'
		 // Lower case characters remain lower case, upper case remain upper case
		 // Any other characters are returned unchanged
		 
		 //Checks whether the character is a digit or a whitespace. If so then the digit/whitespace is returned as there is no encrypted character for these.
		 if (Character.isDigit(theChar) || Character.isWhitespace(theChar)) {
			 return theChar;
		 }
		 
		 //Initialises a character array with all the alphabetical characters.
		 char[] letters = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
		 
		 if (Character.toUpperCase(theChar) == theChar) { //Tests if the current character is upper case.
			 //If so then the letters array is converted to uppercase characters.
			 for (int i=0; i<letters.length;i++) {
				 letters[i] = Character.toUpperCase(letters[i]);
			 }
			 
		 }
		 
		 //Searches the letters array, finding the index of the current character in the alphabet.
		 //If no character is found, the index is left as -1; indicating that the character cannot be encrypted using this algorithm.
		 int index = -1;
		 for (int i=0; i<letters.length; i++) {
			 if (theChar == letters[i]) {
				 index = i;
			 }
		 }
		 
		 //Checks if the character can be ciphered.
		 if (index != -1) {
			 
			 /*If the index of the ciphered character is less or equal to the length of the letters array
			 and it is greater or equal to zero, then the index of the ciphered character is simply the sum of the index and theOffset.
			 */
			 if (((index+theOffset) <= letters.length) && (((index + theOffset) >= 0))) {
				 index = index + theOffset;
			 }
			 else if ((index + theOffset) < 0) { //Otherwise if the index of ciphered character is less than zero, the index of the ciphered character is 
				 index = letters.length + index + theOffset; //the sum of the length of the alphabet array, the index and theOffset.
			 }
			 else { //Otherwise if the calculated index of the ciphered character is greater than length of the alphabet array, the index of the ciphered character is
				 index = (index + theOffset) - letters.length; //subtracting the length of the alphabet  from the sum of the index and theOffset. 
			 }
			 
		 }
		 
		 if (index == -1) { //If the ciphered character cannot be calculated.
			 return theChar; //Return the original character.
		 }
		 else { //If the ciphered character can be calculated. 
			return letters[index];  //Return the character at the new index.
		 }
	 }
	 
	 public String encryptedString(String theMessage, int theOffset)
	 {
		 // 06 - encryptedMessage
		 // Complete this method so that it uses encryptedCharacter to 
		 // return the encrypted version of theMessage using theOffset
		 String message = ""; //Initialises variable for the cipher text.
		 
		 for (int i=0; i<theMessage.length();i++) {	//Iterates through the plaintext string.		 
			 char current = theMessage.charAt(i); //Gets the character at the current index.
			 char newChar = enryptedCharacter(current, theOffset); //Calls the encryptedCharacter function to calculate and return the encrypted character.
			 message = message + newChar; //Adds the new encrypted character to the cipher text string.	 
		 }
		 
		 return message; //Returns Message
	 }
	
}
