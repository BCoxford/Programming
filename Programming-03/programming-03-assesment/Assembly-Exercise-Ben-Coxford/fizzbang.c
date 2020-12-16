#include <stdio.h>

int main(void) { //entry point for the program.
	int bang=0; //number of bangs
	int fizz=0; //number of fizzs
	for (int i=1; i<=20; i++) { //Loop from 1-20
		if(i%15==0) { //If remainder is zero (divisible by 15)
			printf("FizzBang\n");
			bang++; //Increment bang by 1
			fizz++; //Increment fizz by 1
		}
		else if(i%3==0) { //If remainder is zero (divisible by 3)
			printf("Fizz\n"); //Print fizz new line
			fizz++; //Increment fizz by 1
		}
		else if(i%5==0) { //If remainder is zero (divisible by 5)
			printf("Bang\n"); //Print bang new line
			bang++; //Increment bang by 1
		}
		else { //Print the number
			printf("%d\n", i);
		}
	}
	printf("%d\n", bang); //Print the number of bangs
	printf("%d\n", fizz); //Print the number of fizzs
	return 0;
}

