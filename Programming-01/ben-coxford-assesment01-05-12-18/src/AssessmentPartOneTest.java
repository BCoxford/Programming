import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class AssessmentPartOneTest {

	public static AssessmentPartOne test;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		test = new AssessmentPartOne();
	}

	@ParameterizedTest
	@DisplayName("Testing biggestOfThree")
	@CsvSource({
					"1,2,3,3",
					"4,2,3,4",
					"1,5,3,5",
					"8,7,7,8",
					"1,5,7,7"
	})
	void testCountVowels(int num1, int num2, int num3, int biggest) {
		assertEquals(biggest, test.biggestOfThree(num1, num2, num3));
	}

	@ParameterizedTest
	@DisplayName("Testing sumNumbersBetween")
	@CsvSource({
					"1,4,5",
					"1,6,14",
					"1,2,-1",
					"-5,8,-1",
					"5,3,-1",
					"3,-1,-1"
	})
	void testSumNumbersBetween(int num1, int num2, int sum) {
		assertEquals(sum, test.sumNumbersBetween(num1, num2));
	}
}
