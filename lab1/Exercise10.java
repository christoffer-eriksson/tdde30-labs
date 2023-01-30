package se.liu.chrer268.lab1;

public class Exercise10
{
    public static void main(String[] args) {
	int number = 16777217;
	// float < double
	double decimal = number;
	System.out.println("number: " + number);
	System.out.println("decimal: " + decimal);

	int integerAgain = (int) decimal;
	System.out.println("integerAgain: " + integerAgain);

	int big = 2147483647;
	// int < long
	long bigger = (long) big + 1;
	System.out.println("big: " + big);
	System.out.println("bigger: " + bigger);
    }
}
