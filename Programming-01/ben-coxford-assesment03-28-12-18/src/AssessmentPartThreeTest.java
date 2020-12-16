import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class AssessmentPartThreeTest {

	public static AssessmentPartThree test;
	
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		test = new AssessmentPartThree();
	}

	@ParameterizedTest
	@DisplayName("Testing encryptedCharacter")
	@CsvSource({
					"a,5,f",
					"q,-3,n",
					"G,6,M",
					"5,-3,5",
					"T,-7,M"
	})	
	void testEnryptedCharacter(char theChar, int theOffset, char encChar) {
		assertEquals(encChar,test.enryptedCharacter(theChar, theOffset));
	}

	@ParameterizedTest
	@DisplayName("Testing encryptedString")
	@CsvSource({
					"hello,5,mjqqt",
					"Java Coding,-3,Gxsx Zlafkd",
					"dandelion,8,livlmtqwv",
					"ktixevzout,-6,encryption"					
	})
	void testEncryptedString(String theMessage, int theOffset, String encMessage) {
		assertEquals(encMessage, test.encryptedString(theMessage, theOffset));
	}

}
