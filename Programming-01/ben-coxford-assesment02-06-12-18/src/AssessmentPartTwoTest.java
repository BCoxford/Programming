import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class AssessmentPartTwoTest {
	public static AssessmentPartTwo test;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		
		test = new AssessmentPartTwo();
		
	}

	@ParameterizedTest
	@DisplayName("Testing scrabbleScore")
	@CsvSource({
					"rabbit,10",
					"speaker,13",
					"exactly,19",
					"xylophone,24",
					"application,17"
	})	
	void testScrabbleScore(String theWord, int expectedScore) {
		assertEquals(expectedScore, test.scrabbleScore(theWord));		
	}

	@ParameterizedTest
	@DisplayName("Testing passwordValidator")
	@CsvSource({
					"qw11Ij87,true",
					"a834j,false",
					"a88drTcdmn45tdgjhj,false",
					"pmmfj6793,false",
					"PASSWORD,false",
					"lo98passwdiI,false",
					"oi!Fcv98ij,true"
	})	
	void testPasswordValidator(String thePassword, Boolean expectedResult) {
		assertEquals(expectedResult, test.passwordValidator(thePassword));
	}

}
