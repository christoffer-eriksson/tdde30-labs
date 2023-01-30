package se.liu.chrer268.lab3;

import se.liu.chrer268.lab1.Person;

public class Stack extends ListManipulator
{

    public void push(final Person person) {
	elements.add(person);
    }

    public Person pop() {
	int index = elements.size() - 1;
	return elements.remove(index);
    }
}
