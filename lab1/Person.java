package se.liu.chrer268.lab1;

import java.time.LocalDate;
import java.time.Period;

public class Person {
    private String name;
    private LocalDate birthday;

    public Person(String name, LocalDate birthday) {
	this.name = name;
	this.birthday = birthday;
    }

    private int getAge() {
	Period age = Period.between(birthday, LocalDate.now());
	return age.getYears();
    }

    @Override public String toString() {
	return name + ": " + getAge();
    }

    public static void main(String[] args) {
	Person sven = new Person("Sven", LocalDate.of(1963, 4, 25));
	Person karl = new Person("Karl", LocalDate.of(1950, 1, 3));
	Person erik = new Person("Erik", LocalDate.of(2020, 12, 25));
	System.out.println(sven);
	System.out.println(karl);
	System.out.println(erik);
    }
}
