package se.liu.chrer268.lab3;

import se.liu.chrer268.lab1.Person;

public class Queue extends ListManipulator
{

    public void enqueue(final Person person) {
	elements.add(person);
    }

    public Person dequeue() {
	return elements.remove(0);
    }
}
