package se.liu.chrer268.lab1;

import javax.swing.*;

public class Exercise9
{
    public static void main(String[] args) {
	String value = JOptionPane.showInputDialog("Please input a value");
	double x = Double.parseDouble(value);
	System.out.println("Roten ur " + x + " är " + findRoot(x));
    }

    public static double findRoot(double x) {
	double guess = x;
	for (int i = 1; i <= 10; i++) {
	    guess -= (guess * guess - x) / (2 * guess);
	}
	return guess;
    }
}
