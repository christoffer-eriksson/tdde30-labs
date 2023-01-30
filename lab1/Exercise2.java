package se.liu.chrer268.lab1;

import javax.swing.*;

public class Exercise2
{
    public static void main(String[] args) {
	final int min = 10;
	final int max = 20;
	System.out.println((sumWhile(min, max)));
    }

    public static int sumFor(int min, int max) {
	int summa = 0;
	for (int i = min; i <= max; i++) {
	    summa += i;
	}
	return summa;
    }

    public static int sumWhile(int min, int max) {
	int summa = 0;
	while (min <= max) {
	    summa += min;
	    min++;
	}
	return summa;
    }
}
