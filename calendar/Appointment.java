package se.liu.chrer268.calendar;

public class Appointment
{
    private String subject;
    private SimpleDate date;
    private TimeSpan timeSpan;

    // Constructor for appointment with subject, date and timespan.
    public Appointment(final String subject, final SimpleDate date,
		       				final TimeSpan timeSpan) {
	this.subject = subject;
	this.date = date;
	this.timeSpan = timeSpan;
    }

    public String getSubject() {
	return subject;
    }

    public SimpleDate getDate() {
	return date;
    }

    public TimeSpan getTimeSpan() {
	return timeSpan;
    }

    // Returns the appointment in a good looking way.
    public String toString() {
	return getDate() + "| " + getSubject() + ": " + getTimeSpan();
    }
}
