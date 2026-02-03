package umgc.assignments.project3;

/**
 * Programmer: Devon Blank
 * Date: 12/3/2025
 * Purpose: This class represents a single time of day in 'HH:MM AM/PM' format
 * This class encapsulates hours, minutes, and meridian (AM/PM) as an immutable object.
 * It validates inputs, throws InvalidTime on bad values, and implements Comparable<Time>
 * so Time objects can be ordered chronologically. Project4 and Interval<Time> use this class
 * to perform all time parsing, validation, comparison, and display.
 *
 */

public class Time implements Comparable<Time> {

	//Instance variables are immutable (no setters)
	private final int hours;    //1-12
	private final int minutes;  //0-59
	private final String meridian; // AM or PM

	/**
	 * Constructs a Time object using numeric hours, minutes, and meridian.
	 *
	 * @param hours    the hour value 1-12
	 * @param minutes  the minute value 0-59
	 * @param meridian the meridian must be "AM" or "PM"
	 * @throws InvalidTime if any value is out of range or meridian is invalid
	 */

	public Time(int hours, int minutes, String meridian) throws InvalidTime {
		if (meridian == null) {
			throw new InvalidTime("Meridian must specify AM or PM");
		}

		String normalizedMeridian = meridian.trim().toUpperCase();

		validateTime(hours, minutes, normalizedMeridian);

		this.hours = hours;
		this.minutes = minutes;
		this.meridian = normalizedMeridian;
	}

	/**
	 * Constructs a Time object from a string in the format "HH:MM AM"
	 *
	 * @param timeString the time string
	 * @throws InvalidTime if the format is wrong or any component is invalid
	 */

	public Time(String timeString) throws InvalidTime {
		if (timeString == null) {
			throw new InvalidTime("Time string cannot be null");
		}


	String trimmed = timeString.trim();
	//Expecting "HH:MM AM" (with a space between time and meridian)
	String[] parts = trimmed.split("\\s+");
	if(parts.length !=2) {
		throw new InvalidTime("Time must be in the format 'HH:MM AM'");
	}

	String timePart = parts[0];    //"HH:MM"
	String meridianPart = parts[1];// "AM" or "PM"

	String[] hm = timePart.split(":");
	if(hm.length !=2) {
		throw new InvalidTime("Time must be in the format HH:MM AM");
	}

	int parsedHours;
	int parsedMinutes;

	try {
		parsedHours = Integer.parseInt(hm[0]);
		parsedMinutes = Integer.parseInt(hm[1]);
	}catch(
	NumberFormatException e) {
		throw new InvalidTime("Hours and minutes must be numeric");
	}

	String normalizedMeridian = meridianPart.trim().toUpperCase();

	validateTime(parsedHours, parsedMinutes, normalizedMeridian);

	this.hours =parsedHours;
	this.minutes =parsedMinutes;
	this.meridian =normalizedMeridian;

}

/**
 * validates the hour, minute, and meridian values.
 *
 * @throws InvalidTime if any component is invalid
 */
private static void validateTime(int hours, int minutes, String meridian) throws InvalidTime {
	if (hours < 1 || hours > 12){
		throw new InvalidTime("Hours must be between 1 and 12");
	}
	if (minutes < 0 || minutes > 59){
		throw new InvalidTime("Minutes must be between 0 and 59");
	}
	if (!meridian.equals("AM") && !meridian.equals("PM")) {
		throw new InvalidTime("Meridian must be either 'AM' or 'PM'");
	}
}

/**
 * Compares this Time to another Time chronologically
 *
 * @param other the other Time object
 * @return a negative integer, zero, or a positive integer as this Time is earlier than, equal to,
 * or later than the specified time.
 */
@Override
public int compareTo(Time other){
	int thisMinutes = toMinutesSinceMidnight();
	int otherMinutes = other.toMinutesSinceMidnight();

	return Integer.compare(thisMinutes, otherMinutes);
}

/**
 * converts this time to minutes since midnight to simplify comparison
 */
private int toMinutesSinceMidnight() {
	int hour24 = hours % 12;
	if ("PM".equals(meridian)){
		hour24 += 12;
	}
	if (hour24 == 24){
		hour24 = 12;
	}
	return hour24 * 60 + minutes;
}

/**
 * return the string representation of the time in the format "HH:MM AM"
 */
@Override
public String toString(){
	//HH:MM with leading zeros, and meridian (AM/PM)
	return String.format("%02d:%02d %s", hours, minutes, meridian);
	}
}