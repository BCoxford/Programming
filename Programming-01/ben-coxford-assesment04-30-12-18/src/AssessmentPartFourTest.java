import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class AssessmentPartFourTest {

	
	public static AssessmentPartFour test;
	
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		test = new AssessmentPartFour();
	}

	@ParameterizedTest
	@DisplayName("Testing loadMorseFromFile")
	@CsvSource({
					"test1.txt,9",
					"test2.txt,18",
					"test3.txt,15",
					"test4.txt,13"
	})	
	void testEnryptedCharacter(String filename, int count) {
		assertEquals(count,test.loadMorseFromFile(filename));
	}

	@ParameterizedTest
	@DisplayName("Testing translateMorse")
	@CsvSource({
					"test1.txt,9,java code",
					"test2.txt,18,programming is fun",
					"test3.txt,15,fingers crossed",
					"test4.txt,13,yippee did it"
	})
	void testEncryptedString(String filename, int count, String message) {
		int cc = test.loadMorseFromFile(filename);
		if (cc==count)
		{
			assertEquals(message, test.translateMorse());
		}
		else
		{
			fail("File failed to load");
		}
	}
}
