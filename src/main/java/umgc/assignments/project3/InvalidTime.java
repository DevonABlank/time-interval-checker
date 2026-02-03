package umgc.assignments.project3;

/**
 * Programmer: Devon Blank
 * Date: 12/3/2025
 * Purpose: This class defines a checked exception that represents invalid time input.
 * This exception is thrown by the Time class whenever a time value is incorrect or out of range.
 * This class allows Project4 to distinguish time format errors from other program errors and display
 * meaningful messages to the user.
 */

public class InvalidTime extends Exception {

	//Message giving feedback
	private String message;

	/**
	 * Constructs an InvalidTime exception with the specified detail message
	 * @param message the detail message explaining why the time is not valid
	 */
	public InvalidTime(String message) {
		super(message);
		this.message = message;
	}

	/**
	 * Returns the detail message string of this InvalidTime exception.
	 * @return the detail message
	 */
	@Override
	public String getMessage() {
		return message;
	}
}
