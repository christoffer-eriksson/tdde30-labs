package se.liu.chrer268.lab1;

import javax.swing.*;

public class Exercise7
{
    // Utökad version av Exercise2
    public static void main(String[] args) {
	final int min = 10;
	final int max = 20;
	switch (JOptionPane.showInputDialog("For or While?")) {
	    case "For":
	    	System.out.println("For: " + sumFor(min, max));
	    	break;
	    case "While":
		System.out.println("While: " + sumWhile(min, max));
		break;
	    default:
		System.out.println("Error");
		break;
	}
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
