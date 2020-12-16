
public class AssessmentPartOne {

	public int biggestOfThree(int num1, int num2, int num3)
	{
		// 01 - A Gentle Start
		// Debug this method so it passes the unit test.
		// Add comments beside any changes you make
		
		if (num1>num2) //Changed the 'less than' sign to a 'greater than' sign.
		{
			if (num3>num1)
			{
				return num3;
			}
			else
			{
				return num1;
			}
		}
		else 
		{
			if (num3>num2)
			{
				return num3;						
			}
			else
			{
				return num2; //Added missing semi-colon.
			}
		}
	}
	
	public int sumNumbersBetween(int start, int end)
	{
		// 02 - Adding Across A Range
		// Complete this method so that it adds together all
		// the integers between start and end, but not including 
		// start or end
		// This method should only deal with 0 and positive integers
		// This method should return -1 if it cannot calculate the result
		
		// You should comment your code explaining what each part does
		
		int sumOfSquares = 0; //Defines and initialises the total value.
		
		if (start < end-1) { //Validates whether there are any numbers between the start and end and whether the start value is less than the end value.
			if (!(start < 0) || (end < 0)) { //Validates that both the start and end values are not negative numbers.
				
				//Adds up all values from the start to the end exclusive of themselves.
				for(int i=start+1;i<end;i++) {
					sumOfSquares = sumOfSquares + i;
				}
				
			}
			else { //If the start and end value is a negative number.
				sumOfSquares = -1; //Sets the sumOfSquares to -1; indicating that the calculation cannot be carried out.  
			}
		}
		else { //If there are no values between the start and end or if the start value is less than the end value.
			sumOfSquares = -1; //Sets the sumOfSquares to -1; indicating that the calculation cannot be carried out.
		}
		
		
		return sumOfSquares; //Returns the value.
	}
	
}
