package umgc.assignments.project3;

/**
 * Programmer: Devon Blank
 * Date: 12/3/2025
 * Purpose: Represents a closed interval [starts, ends] for any type that implements Comparable.
 * This generic, immutable class provides methods to test whether a value lies within the interval,
 * whether another interval is a subinterval, and whether two intervals overlap or are disjoint.
 * In this project it is parameterized as interval<Time> so that Project4 can reason about
 * relationships between time intervals in a type-safe, reusable way.
 */

 /**
 * @param <T> the type of the interval endpoints, must be Comparable
 */
public class Interval<T extends Comparable<T>> {

	//Immutable start and end points of the interval
	private final T starts;
	private final T ends;

	/**
	 * Constructs an interval object with the given start and end
	 * The interval is treated as closed: [start, end]
	 *
	 * @param starts the starting value of the interval
	 * @param ends the ending value of the interval
	 * @throws IllegalArgumentException if start or end is null, or if start is greater than end
	 */

	public Interval(T starts, T ends) {
		if (starts == null || ends == null) {
			throw new IllegalArgumentException("Start and end cannot be null");
		}
		if (starts.compareTo(ends) > 0) {
			throw new IllegalArgumentException("Start of interval cannot be greater than the end");
		}

		this.starts = starts;
		this.ends = ends;
	}

	/**
	 * Returns whether the specified value is within this interval, including the endpoints
	 *
	 * @param value the value to test
	 * @return true if start <= value <= end, false otherwise
	 * @throws IllegalArgumentException if value is null
	 *
	 */
	public boolean within(T value){
		if (value == null){
			throw new IllegalArgumentException("value cannot be null");
		}

		return starts.compareTo(value) <= 0 && value.compareTo(ends) <= 0;
	}

	/**
	 * Returns whether the specified interval is a subinterval of this interval.
	 * That is, whether it lies completely within this interval
	 *
	 * @param other the interval to test as a subinterval
	 * @return true if this.start<= other.starts and other.end <= this.end
	 * @throws IllegalArgumentException if other is null
	 */
	public boolean subinterval(Interval<T> other){
		if (other == null){
			throw new IllegalArgumentException("other cannot be null");
		}
		//Other is a subinterval if it starts no earlier than this.start and ends no later than this.end
		return this.starts.compareTo(other.starts) <= 0 && other.ends.compareTo(this.ends) <= 0;
	}

	/**
	 * Return whether the specified interval overlaps this interval.
	 * Overlap means they share at least one point.
	 *
	 * @param other the interval to test for overlap
	 * @return true if the intervals overlap, false if they are disjoint
	 * @throws IllegalArgumentException if other is null
	 */
	public boolean overlaps(Interval<T> other){
		if (other == null){
			throw new IllegalArgumentException("other cannot be null");
		}

		boolean thisStartsBeforeOrAtOtherEnd = this.starts.compareTo(other.ends) <= 0;
		boolean otherStartBeforeOrAtThisEnd = other.starts.compareTo(this.ends) <= 0;

		return thisStartsBeforeOrAtOtherEnd && otherStartBeforeOrAtThisEnd;
	}

	//Returns the start of the interval.
	public T getStarts(){
		return starts;
	}

	//returns the end of the interval
	public T getEnds(){
		return ends;
	}

	//returns a string representation of the interval in the form [start, end]
	@Override
	public String toString() {
		return "[" + starts + ", " + ends + "]";
	}
}
