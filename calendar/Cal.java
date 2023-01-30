package se.liu.chrer268.calendar;

import java.util.ArrayList;
import java.util.List;

public class Cal
{
    private List<Appointment> appointments = new ArrayList<>();

    public void show() {
        for (Appointment appointment : appointments) {
            System.out.println(appointment);
        }
    }

    public void book(int year, String month, int day, int startHour,
                        int startMinute, int endHour, int endMinute,
                                                        String subject) {

        if (year < 1970) {
            throw new
                IllegalArgumentException("Pleas input a year after 1970.");
        }
        if (startHour < 0 || startHour > 23
                || endHour < 0 || endHour > 23) {
            throw new
                IllegalArgumentException("Please input hours between 00-23.");
        }
        if (startMinute < 0 || startMinute > 59
                || endMinute < 0 || endMinute > 59) {
            throw new
                IllegalArgumentException("Please input minutes between 00-59");
        }
        if (startHour >= endHour && startMinute > endMinute) {
            throw new
                IllegalArgumentException("Starttime can't be after endtime");
        }
        if (Month.getMonthNumber(month) == -1) {
            throw new IllegalArgumentException("Please input a valid month");
        }
        if (day > Month.getMonthDays(month) || day < 0) {
            throw new IllegalArgumentException("Pleas input a valid day");
        }
        Month acquiredMonth = new Month(month, Month.getMonthNumber(month),
                                                    Month.getMonthDays(month));
        SimpleDate givenDate = new SimpleDate(year, acquiredMonth, day);
        TimePoint start = new TimePoint(startHour, startMinute);
        TimePoint end = new TimePoint(endHour, endMinute);
        TimeSpan timeSpan = new TimeSpan(start, end);
        Appointment booked = new Appointment(subject, givenDate, timeSpan);
        appointments.add(booked);
    }

    public static void main(String[] args) {
        Cal testCalendar = new Cal();
        testCalendar.book(2000, "january", 1, 00, 00, 00, 01, "New Millenial");
        testCalendar.book(2001, "september", 15, 15, 30, 15, 56, "Cookie");
        testCalendar.book(2005, "january", 4, 10, 00, 11, 00, "Chill");
        testCalendar.book(2006, "december", 24, 16, 30, 17, 30, "Eat");
        testCalendar.book(2008, "april", 10, 22, 45, 23, 59, "Sleep");
        testCalendar.book(2009, "february", 27, 01, 00, 02, 00, "Repeat");
        testCalendar.book(2010, "january", 1, 00, 00, 00, 01, "School");
        testCalendar.book(2011, "september", 15, 15, 30, 15, 56, "Host");
        testCalendar.book(2012, "january", 1, 00, 00, 00, 01, "Dance");
        testCalendar.book(2013, "september", 15, 15, 30, 15, 56, "Run");
        testCalendar.show();
    }
}
